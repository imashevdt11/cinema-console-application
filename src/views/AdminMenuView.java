package views;

public class AdminMenuView extends UserMenuView {

    @Override
    public void printFunctionOptionsMenu() {
        System.out.print("""
            \nENTER THE NUMBER OF MENU'S OPTION

            1 - ASSIGNMENTS
            2 - SCHEDULE
            3 - REVIEWS
            4 - MOVIES
            
            5 - COMPLETE ASSIGNMENT
            6 - REPLY TO A REVIEW
            7 - FIND VISITOR
            8 - ADD SESSION
            9 - ADD MOVIE

            10 - LOG OUT OF ACCOUNT
            0 - SHUT DOWN THE PROGRAMME:\040""");
    }
}
