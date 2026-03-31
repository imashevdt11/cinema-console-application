package models;

import enums.ErrorType;
import util.InputScanner;
import views.GeneralView;

public class Visitor extends User {

    public Visitor() {}

    public void chooseMenuOption() {
        String choice = InputScanner.getScanner().nextLine();

        switch (choice) {
//            case "1" -> SessionService.getSchedule();
//            case "2" -> ReviewService.getReviews();
//            case "3" -> getBalance();
//            case "4" -> TicketService.getBoughtTickets();
//            case "5" -> TicketService.findTicket();
//            case "6" -> TicketService.buyTicket();
//            case "7" -> replenishTheBalance();
//            case "8" -> deleteAccount();
//            case "9" -> ReviewService.addReview();
//            case "10" -> UserMenuView.openMainMenu();
            case "0" -> GeneralView.printGoodbyeMessage();
            default -> GeneralView.printErrorMessage(ErrorType.INVALID_MENU_OPTION);
        }
    }
}
