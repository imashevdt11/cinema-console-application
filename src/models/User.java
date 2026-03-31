package models;

import constants.UserSqlQueries;
import enums.ErrorType;
import enums.UserType;
import repository.UserRepository;
import util.InputScanner;
import views.GeneralView;

public abstract class User {

    private String firstName;
    private String lastName;
    private String password;

    public User() {}

    public static User getUserObject() {
        UserType userType = UserType.fromValue(InputScanner.getScanner().nextLine());

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
                GeneralView.printErrorMessage(ErrorType.INVALID_USER_TYPE);
                GeneralView.printTryAgainOptions();

                if (InputScanner.getScanner().nextLine().equals("1")) {
                    getUserObject();
                } else {
                    GeneralView.printGoodbyeMessage();
                }
            }
        }
        return null;
    }

    public abstract void chooseMenuOption();

    public boolean login() {
        enterUserLoginData();
        String loginQuery = resolveLoginQuery(this);

        if (!UserRepository.isUserExists(this, loginQuery)) {
            GeneralView.printErrorMessage(ErrorType.USER_NOT_FOUND);
            GeneralView.printTryAgainOptions();

            String choice = InputScanner.getScanner().nextLine();

            if (choice.equals("1")) {
                login();
            } else {
                GeneralView.printGoodbyeMessage();
                return false;
            }
        }
        return true;
    }

    private void enterUserLoginData() {
        System.out.print("\nFIRST NAME: ");
        String firstName = InputScanner.getScanner().nextLine();
        setFirstName(firstName);

        System.out.print("LAST NAME: ");
        String lastName = InputScanner.getScanner().nextLine();
        setLastName(lastName);

        System.out.print("PASSWORD: ");
        String password = InputScanner.getScanner().nextLine();
        setPassword(password);
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
}
