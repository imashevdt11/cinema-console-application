package models;

import enums.ErrorType;
import services.AssignmentService;
import services.MovieService;
import services.ReviewService;
import util.InputScanner;
import views.GeneralView;

public class Manager extends User {

    public Manager() {}

    public void chooseMenuOption() {
        String choice = InputScanner.getScanner().nextLine();

        switch (choice) {
            case "1" -> AssignmentService.getAssignments();
//            case "2" -> SessionService.getSchedule();
            case "3" -> ReviewService.getReviews();
            case "4" -> MovieService.getMovies();
//            case "5" -> Admin.removeAdmin();
            case "6" -> AssignmentService.createAssignment(this);
//            case "7" -> Admin.addAdmin();
//            case "8" -> Visitor.findVisitor();
//            case "9" -> Admin.findAdmin();
//            case "10" -> Visitor.getUsersStatistic();
//            case "11" -> TicketService.getIncomeStatement();
//            case "12" -> UserMenuView.openMainMenu();
            case "0" -> GeneralView.printGoodbyeMessage();
            default -> GeneralView.printErrorMessage(ErrorType.INVALID_MENU_OPTION);
        }
    }
}
