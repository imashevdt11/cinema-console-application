package models;

import interfaces.*;

public abstract class User implements Assignment, Movie, Review, Session, Ticket {

    private static String firstName;
    private static String lastName;
    private static String password;

    static void setFirstName(String firstName) {
        User.firstName = firstName;
    }
    static void setLastName(String lastName) {
        User.lastName = lastName;
    }
    static void setPassword(String password) {
        User.password = password;
    }
    public static String getFirstName() {
        return firstName;
    }
    public static String getLastName() {
        return lastName;
    }
    public static String getPassword() {
        return password;
    }
}


