package models;

import configurations.DatabaseConfiguration;
import constants.UserSqlQueries;
import services.AssignmentService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Manager extends User {

    public Manager() {}

    public void chooseMenuOption(Scanner scanner) {

        try {
            String choice = scanner.nextLine();

            switch (choice) {

                case "1" -> {
                    AssignmentService.getAssignments(scanner);
                    chooseMenuOption(scanner);
                }
                case "2" -> {
//                    Session.getSchedule();
                    chooseMenuOption(scanner);
                }
                case "3" -> {
//                    Review.getReviews();
                    chooseMenuOption(scanner);
                }
                case "4" -> {
//                    Movie.getMovies();
                    chooseMenuOption(scanner);
                }

//                case "5" -> Admin.removeAdmin();
                case "6" -> {
                    AssignmentService.createAssignment(scanner, this);
                    chooseMenuOption(scanner);
                }
//                case "7" -> Admin.addAdmin();

                case "8" -> {
//                    Visitor.findVisitor();
                    chooseMenuOption(scanner);
                }
//                case "9" -> Admin.findAdmin();

//                case "10" -> Visitor.getUsersStatistic();
//                case "11" -> Ticket.getIncomeStatement();

//                case "12" -> UserMenuView.openMainMenu(); // TODO: implement option to back to main menu
                case "0" -> System.out.print("\nGOODBYE! HAVE A NICE DAY!\n");
                default -> {
                    System.out.println("\nTHE ENTERED MENU NUMBER IS INVALID");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
                    DatabaseConfiguration.connection.prepareStatement(UserSqlQueries.MANAGERS_LOGIN_QUERY);

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
            } else{
                System.out.println("GOODBYE! HAVE A NICE DAY!\n");
            }
//            else UserMenuView.openMainMenu();
        }
    }
}
