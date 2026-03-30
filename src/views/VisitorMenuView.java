package views;

public class VisitorMenuView extends UserMenuView {

    @Override
    public void printFunctionOptionsMenu() {
        System.out.print("""
            \nENTER THE NUMBER OF MENU'S OPTION
            
            1 - SCHEDULE
            2 - REVIEWS
            3 - BALANCE

            4 - BOUGHT TICKETS
            5 - FIND TICKET
            6 - BUY TICKET
            
            7 - REPLENISH THE BALANCE
            8 - DELETE ACCOUNT
            9 - ADD REVIEW

            10 - LOG OUT OF ACCOUNT
            0 - SHUT DOWN THE PROGRAMME:\040""");
    }
}
