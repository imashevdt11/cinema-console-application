package models.entities;

import configurations.MyConnection;
import models.interfaces.*;
import views.UserMenuView;

import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager extends User {

    public void chooseMenuOption(Scanner scanner) {

        try {
            System.out.println("\n———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
            System.out.print("""
                    \nENTER THE NUMBER OF MENU'S OPTION
                    
                    1 - ASSIGNMENTS
                    2 - SCHEDULE
                    3 - REVIEWS
                    4 - MOVIES
                    
                    5 - REMOVE ADMINISTRATOR
                    6 - REQUEST ASSIGNMENT
                    7 - ADD ADMINISTRATOR
                    
                    SEARCH
                    8 - VISITOR
                    9 - ADMIN
                    
                    REPORTS
                    10 - STATISTICS OF REGISTERED/UNREGISTERED USERS
                    11 - TICKETS REVENUE
                    
                    12 - LOG OUT OF ACCOUNT
                    0 - SHUT DOWN THE PROGRAMME:\040""");
            String choice = scanner.nextLine();
            System.out.println("\n———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");

            switch (choice) {

                case "1" -> {
//                    Assignment.getAssignments();
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
//                case "6" -> Assignment.requestAssignment();
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
                    chooseMenuOption(scanner);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    public static void mLogIn() throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nFIRST NAME: ");
        String firstName = scanner.nextLine();
        System.out.print("LAST NAME: ");
        String lastName = scanner.nextLine();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine();

        Manager.setFirstName(firstName);
        Manager.setLastName(lastName);
        Manager.setPassword(password);

        boolean isUserExists = false;
        PreparedStatement preparedStatement = MyConnection.connection.prepareStatement("SELECT firstName, lastName, password, status " +
                "FROM managers WHERE firstName = ? AND lastName = ? AND password = ? AND status = 'current'");
        {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) isUserExists = true;
            }
        }
        if (isUserExists) choose();
        else {
            System.out.print("""
                    \nNO DATA FOUND
                    
                    DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
            String choice = scanner.nextLine();

            if (choice.equals("1")) mLogIn();
//            else UserMenuView.openMainMenu();
        }
    }
     */
}
