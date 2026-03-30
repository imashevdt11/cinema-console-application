package controllers;

import interfaces.TypeChoosingListener;
import models.entities.User;
import views.UserMenuView;

import java.util.Scanner;

public class UserController implements TypeChoosingListener {

    private User model;
    private UserMenuView view;
    private Scanner scanner;

    public UserController(User model, UserMenuView view, Scanner scanner) {
        this.model = model;
        this.view = view;
        this.scanner = scanner;
    }

    public void start() {
        view.printFunctionOptionsMenu();
        model.chooseMenuOption(scanner);
    }

    public User getModel() {
        return model;
    }

    public void setModel(User model) {
        this.model = model;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public UserMenuView getView() {
        return view;
    }

    public void setView(UserMenuView view) {
        this.view = view;
    }
}
