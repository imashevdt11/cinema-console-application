package views;

import models.Admin;
import models.Manager;
import models.User;
import models.Visitor;

public abstract class UserMenuView {

    static {
        System.out.println("Hi!");
    }

    public static void printAccountTypeMenu() {
        System.out.println("""
                ENTER THE NUMBER OF YOUR ACCOUNT TYPE
                
                1 - ADMINISTRATOR
                2 - VISITOR
                3 - MANAGER
                
                4 - SIGN UP (IF YOU HAVEN'T YET)
                
                0 - SHUT DOWN THE PROGRAM
                """);
    }

    public static UserMenuView getUserMenuViewObject(User user) {
        return switch (user) {
            case Admin ignored -> new AdminMenuView();
            case Visitor ignored -> new VisitorMenuView();
            case Manager ignored -> new ManagerMenuView();
            case null, default -> null;
        };
    }

    public abstract void printFunctionOptionsMenu();
}
