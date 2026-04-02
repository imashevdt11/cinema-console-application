package models;

import enums.ErrorType;
import services.AssignmentService;
import services.MovieService;
import services.ReviewService;
import util.InputScanner;
import views.GeneralView;

public class Admin extends User {

    public Admin() {}

    public void chooseMenuOption() {
        String choice = InputScanner.getScanner().nextLine();

        switch (choice) {
            case "1" -> AssignmentService.getAssignments();
//            case "2" ->  SessionService.getSchedule();
            case "3" -> ReviewService.getReviews();
            case "4" -> MovieService.getMovies();
            case "5" -> AssignmentService.completeAssignment(this);
            case "6" -> ReviewService.replyReview(this);
//            case "7" -> Visitor.findVisitor();
//            case "8" -> SessionService.addSession();
            case "9" -> MovieService.addMovie();
//            case "10" -> UserMenuView.openMainMenu();
            case "0" -> GeneralView.printGoodbyeMessage();
            default -> GeneralView.printErrorMessage(ErrorType.INVALID_MENU_OPTION);
        }
    }
}
