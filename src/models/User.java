package models;

import constants.UserSqlQueries;
import enums.UserType;
import interfaces.Movie;
import interfaces.Review;
import interfaces.Session;
import interfaces.Ticket;
import repository.UserRepository;

import java.util.Scanner;

public abstract class User implements Movie, Review, Session, Ticket {

    private String firstName;
    private String lastName;
    private String password;

    public User() {}

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

    public boolean login(Scanner scanner) {

        System.out.print("\nFIRST NAME: ");
        String firstName = scanner.nextLine();

        System.out.print("LAST NAME: ");
        String lastName = scanner.nextLine();

        System.out.print("PASSWORD: ");
        String password = scanner.nextLine();

        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);

        String loginQuery = resolveLoginQuery(this);

        if (!UserRepository.isUserExists(this, loginQuery)) {
            System.out.print("""
                    \nNO DATA FOUND
                    
                    DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                login(scanner);
            } else {
                System.out.println("GOODBYE! HAVE A NICE DAY!\n");
                return false;
            }
        }
        return true;
//            else UserMenuView.openMainMenu();
    }

    private String resolveLoginQuery(User user) {
        String loginQuery = null;
        if (user instanceof Admin) {
            loginQuery = UserSqlQueries.ADMINS_LOGIN_QUERY;
        } else if (user instanceof Manager) {
            loginQuery = UserSqlQueries.MANAGERS_LOGIN_QUERY;
        } else if (user instanceof Visitor) {
            loginQuery = UserSqlQueries.VISITORS_LOGIN_QUERY;
        }
        return loginQuery;
    }
}
