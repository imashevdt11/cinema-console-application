import controllers.UserController;
import models.User;
import views.UserMenuView;

public class CinemaConsoleApplication {
    public static void main(String[] args) {
        UserMenuView.printAccountTypeMenu();
        User model = User.getUserObject();
        UserMenuView view = UserMenuView.getUserMenuViewObject(model);
        UserController controller = new UserController(model, view);

        controller.start();
    }
}