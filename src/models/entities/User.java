package models.entities;

import models.enums.UserType;
import models.interfaces.*;

import java.util.Scanner;

public abstract class User implements Assignment, Movie, Review, Session, Ticket {

    private static String firstName;
    private static String lastName;
    private static String password;

    static void setFirstName(String firstName) {
        User.firstName = firstName;
    }
    static void setLastName(String lastName) {
        User.lastName = lastName;
    }
    static void setPassword(String password) {
        User.password = password;
    }
    public static String getFirstName() {
        return firstName;
    }
    public static String getLastName() {
        return lastName;
    }
    public static String getPassword() {
        return password;
    }

    public static User getUserObject(Scanner scanner) {
        UserType userType = UserType.fromValue(scanner.nextLine());
        try {
            switch (userType) {
                case UserType.ADMIN -> {
                    return new Admin();
                }
                case UserType.VISITOR -> {
                    return new Visitor();
                }
                case UserType.MANAGER -> {
                    return new Manager();
                }
                default -> {
                    System.out.print("""
                            \nINVALID ACCOUNT TYPE NUMBER

                            DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");

                    if (scanner.nextLine().equals("1")) getUserObject(scanner);
                    else System.out.println("\nGOODBYE! HAVE A NICE DAY!\n");
                }
            }
        } catch (Exception e) {
            System.out.printf("Oi-Oi! Exception %s\n", e.getMessage() );
        }
        return null;
    }

    public abstract void chooseMenuOption(Scanner scanner);
}
