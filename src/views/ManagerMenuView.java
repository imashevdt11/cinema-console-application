package views;

public class ManagerMenuView extends UserMenuView  {

    @Override
    public void printFunctionOptionsMenu() {
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
    }
}
