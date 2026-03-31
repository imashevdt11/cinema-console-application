package controllers;

import models.User;
import views.UserMenuView;

public class UserController {

    private User model;
    private UserMenuView view;

    public UserController(User model, UserMenuView view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        boolean loginResult = model.login();

        if (loginResult) {
            view.printFunctionOptionsMenu();
            model.chooseMenuOption();
        }
    }
}
