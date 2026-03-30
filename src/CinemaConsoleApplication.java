import controllers.UserController;
import models.entities.User;
import views.UserMenuView;

import java.util.Scanner;

public class CinemaConsoleApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserMenuView.printAccountTypeMenu();
        User model = User.getUserObject(scanner);
        UserMenuView view = UserMenuView.getUserMenuViewObject(model);
        UserController controller = new UserController(model, view, scanner);

        controller.start();
    }
}