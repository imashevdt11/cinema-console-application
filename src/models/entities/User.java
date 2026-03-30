package models.entities;

import models.enums.UserType;
import models.interfaces.*;

import java.util.Scanner;

public abstract class User implements Assignment, Movie, Review, Session, Ticket {

    private String firstName;
    private String lastName;
    private String password;

    public User() {}

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public abstract void login(Scanner scanner);
}
