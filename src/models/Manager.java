package models;

import services.AssignmentService;

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
}
