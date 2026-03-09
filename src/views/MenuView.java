package views;

import models.Admin;
import models.Manager;
import models.Visitor;
import util.InputScanner;

public class MenuView {

    private final static String[] MAIN_MENU_OPTIONS = {"0", "1", "2", "3", "4"};

    static {
        System.out.println("Hi!");
    }

    public static void openMainMenu() {
        printMainMenu();
        String chosenOption = chooseMainMenuOption();

        try {
            switch (chosenOption) {
                case "1" -> Admin.aLogIn();
                case "2" -> Visitor.vLogIn();
                case "3" -> Manager.mLogIn();

                case "4" -> Visitor.signUp();

                case "0" -> System.out.println("\nGOODBYE! HAVE A NICE DAY!\n");
                case null, default -> {
                    System.out.print("""
                            \nINVALID ACCOUNT TYPE NUMBER
                            
                            DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
                    chosenOption = InputScanner.getStringScanner().nextLine();

                    if (chosenOption.equals("1")) printMainMenu();
                    else System.out.println("\nGOODBYE! HAVE A NICE DAY!\n");
                }
            }
        } catch (Exception e) {
            System.out.printf("Oi-Oi! Exception %s\n", e.getMessage() );
        }
    }

    private static void printMainMenu() {

        System.out.println("""
                ENTER THE NUMBER OF YOUR ACCOUNT TYPE
                
                1 - ADMINISTRATOR
                2 - VISITOR
                3 - MANAGER
                
                4 - SIGN UP (IF YOU HAVEN'T YET)
                
                0 - SHUT DOWN THE PROGRAM
                """);
    }

    private static String chooseMainMenuOption() {
        String chosenOption = InputScanner.getStringScanner().nextLine();

        return isOptionExists(chosenOption) ? chosenOption : null;
    }

    private static boolean isOptionExists(String chosenOption) {
        boolean isOptionExists = false;

        for (String option: MAIN_MENU_OPTIONS) {
            if (chosenOption.equals(option)) {
                isOptionExists = true;
                break;
            }
        }
        return isOptionExists;
    }
}
