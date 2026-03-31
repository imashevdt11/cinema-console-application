package models;

import configurations.DatabaseConfiguration;
import constants.UserSqlQueries;
import services.AssignmentService;

import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends User {

    public Admin() {}

    public void chooseMenuOption(Scanner scanner) {

        String choice = scanner.nextLine();

        switch (choice) {

            case "1" -> {
                AssignmentService.getAssignments(scanner);
                chooseMenuOption(scanner);
            }
            case "2" -> {
//                Session.getSchedule();
                chooseMenuOption(scanner);
            }
            case "3" -> {
//                Review.getReviews();
                chooseMenuOption(scanner);
            }
            case "4" -> {
//                Movie.getMovies();
                chooseMenuOption(scanner);
            }

            case "5" -> AssignmentService.completeAssignment(scanner, this);
//            case "6" -> Review.replyReview();
            case "7" -> {
//                Visitor.findVisitor();
                chooseMenuOption(scanner);
            }
//            case "8" -> Session.addSession();
//            case "9" -> Movie.addMovie();

//            case "10" -> UserMenuView.openMainMenu();
            case "0" -> System.out.print("\nGOODBYE! HAVE A NICE DAY!\n");
            default -> {
                System.out.println("\nTHE ENTERED MENU NUMBER IS INVALID");
            }
        }
    }

    @Override
    public void login(Scanner scanner) {

        System.out.print("\nFIRST NAME: ");
        String firstName = scanner.nextLine();

        System.out.print("LAST NAME: ");
        String lastName = scanner.nextLine();

        System.out.print("PASSWORD: ");
        String password = scanner.nextLine();

        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);

        boolean isUserExists = false;
        try {
            PreparedStatement preparedStatement =
                    DatabaseConfiguration.connection.prepareStatement(UserSqlQueries.ADMINS_LOGIN_QUERY);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) isUserExists = true;
            }
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }

        if (!isUserExists) {
            System.out.print("""
                    \nNO DATA FOUND
                    
                    DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                login(scanner);
            } else {
                System.out.println("GOODBYE! HAVE A NICE DAY!\n");
            }
        }
//            else UserMenuView.openMainMenu();
    }

    /*
    static void removeAdmin() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nFIRST NAME: ");
        String firstName = scanner.nextLine();
        System.out.print("LAST NAME: ");
        String lastName = scanner.nextLine();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine();

        DatabaseConfiguration.statement.executeUpdate("UPDATE admins SET status = 'former' " +
                "WHERE firstname = '" + firstName + "' AND lastname = '" + lastName + "' AND password = '" + password + "';");
        DatabaseConfiguration.statement.executeUpdate("UPDATE admins SET dateofdismissal = now() " +
                "WHERE firstname = '" + firstName + "' AND lastname = '" + lastName + "' AND password = '" + password + "';");

        System.out.println("\n" + firstName + " " + lastName + "'s DATA CHANGED");
        chooseMenuOption(scanner);
    }
     */

    /*
    static void findAdmin() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nFIRST NAME: ");
        String firstName = scanner.nextLine();
        System.out.print("SECOND NAME: ");
        String lastName = scanner.nextLine();

        ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT * FROM admins " +
                "WHERE firstName = '" + firstName + "' AND lastName = '" +  lastName + "';");

        System.out.printf("\n%-25s%-25s%-25s%-15s%-35s%n", "FIRST NAME ", "LAST NAME", "PHONE NUMBER", "STATUS", "NUMBER OF COMPLETE ASSIGNMENTS");
        System.out.println();
        while (resultSet.next()) {
            System.out.printf("%-25s", resultSet.getString(2));
            System.out.printf("%-25s", resultSet.getString(3));
            System.out.printf("%-25s", resultSet.getString(4));
            System.out.printf("%-15s", resultSet.getString(6));
            System.out.printf("%-35s", resultSet.getString(7));
            System.out.println();
        }
        chooseMenuOption(scanner);
    }
     */

    /*
    static void addAdmin() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nFIRST NAME (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
        String firstName = scanner.nextLine();
        System.out.print("LAST NAME (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
        String lastName = scanner.nextLine();
        System.out.print("PHONE NUMBER (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
        String phoneNumber = scanner.nextLine();
        System.out.print("PASSWORD (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
        String password = scanner.nextLine();
        if (firstName.length() > 20 || lastName.length() > 20 || phoneNumber.length() > 20 || password.length() > 20) {
            System.out.print("""
                    \nYOU HAVE ENTERED THE NUMBER OF CHARACTERS EXCEEDING THE SPECIFIED LIMIT
                    
                    DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
            String choice = scanner.nextLine();
            if (choice.equals("1")) addAdmin();
            else chooseMenuOption();
        } else {
            Admin.setFirstName(firstName);
            Admin.setLastName(lastName);
            Admin.setPassword(password);
            boolean isUserExists = false;
            PreparedStatement preparedStatement = DatabaseConfiguration.connection.prepareStatement(
                    "SELECT password FROM admins WHERE password = ?"); {
                preparedStatement.setString(1, password);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) { if (resultSet.next()) isUserExists = true; }
            if (isUserExists) {
                System.out.println("""
                        \nADMIN WITH ENTERED DATA ALREADY EXISTS
                        
                        DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
                String choice = scanner.nextLine();
                if (choice.equals("1")) addAdmin();
                else Manager.mMenu();
            } else {
                DatabaseConfiguration.statement.executeUpdate("INSERT INTO admins(firstname, lastname, phoneNumber, password) " +
                        "VALUES ('" + firstName + "', '" + lastName + "', '" + phoneNumber + "', '" + password + "');");
                System.out.println("\n" + firstName + " " + lastName + "'s DATA IS STORED IN THE DATABASE");
                Manager.mMenu();
            }
        }
    }
     */
}

