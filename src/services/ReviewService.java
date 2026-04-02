package services;

import configurations.DatabaseConfiguration;
import models.Admin;
import models.Visitor;
import util.InputScanner;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewService {

    public static void addReview(Visitor visitor) {
        try {
            System.out.print("\nENTER THE REVIEW (MUST CONTAIN LESS THAN 80 CHARACTERS): ");
            String review = InputScanner.getScanner().nextLine();
            if (review.length() > 80) {
                System.out.println("""
                        \nYOU HAVE ENTERED THE NUMBER OF CHARACTERS EXCEEDING THE SPECIFIED LIMIT
                        
                        DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
                String choice = InputScanner.getScanner().nextLine();
                if (choice.equals("1")) {
                    addReview(visitor);
                }
            }
            ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("" +
                    "SELECT * FROM visitors WHERE password = '" + visitor.getPassword() + "';");
            String visitorResult = null;
            if (resultSet.next()) visitorResult = resultSet.getString(1);
            String visitorID = visitorResult;
            DatabaseConfiguration.statement.executeUpdate("INSERT INTO reviews(visitorID, review, dateOfReview) " +
                    "VALUES(" + visitorID + ", '" + review + "', now());");
            System.out.println("\nREVIEW HAS BEEN ADDED");
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }
    }

    public static void getReviews() {
        try {
            ResultSet resultSet = DatabaseConfiguration.statement.executeQuery(
                    "SELECT v.firstname, v.lastname, r.reviewID, r.review, r.dateOfReview FROM reviews r, visitors v " +
                            "WHERE v.visitorID = r.visitorID ORDER BY r.dateOfReview");
            System.out.printf("\n%-10s%-45s%-80s%-30s%n", "REVIEW ID", "VISITOR", "REVIEW", "DATE OF REVIEW");
            System.out.println();
            while (resultSet.next()) {
                System.out.printf("%-10s", resultSet.getString(3));
                System.out.printf("%-45s", resultSet.getString(1) + " " + resultSet.getString(2));
                System.out.printf("%-80s", resultSet.getString(4));
                System.out.printf("%-30s", resultSet.getString(5));
                System.out.println();
            }
            System.out.print("\nDO YOU WANT TO GET REPLY FOR REVIEW? (1 - YES / 0 - NO): ");
            String choice = InputScanner.getScanner().nextLine();
            if (choice.equals("1")) {
                System.out.print("ENTER THE REVIEW ID: ");
                choice = InputScanner.getScanner().nextLine();
                ResultSet resultSet2 = DatabaseConfiguration.statement.executeQuery(
                        "SELECT a.firstName, a.lastName, r.reply FROM reviews r, admins a " +
                                "WHERE r.adminID = a.adminID AND reviewID = " + choice + ";");
                System.out.printf("\n%-45s%-80s%n", "ADMIN", "REPLY");
                while (resultSet2.next()) {
                    System.out.printf("%-45s", resultSet2.getString(1) + " " + resultSet2.getString(2));
                    System.out.printf("%-80s", resultSet2.getString(3));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }
    }

    public static void replyReview(Admin admin) {
        try {
            System.out.print("\nENTER THE REVIEW ID: ");
            String reviewID = InputScanner.getScanner().nextLine();
            System.out.print("REPLY (MUST CONTAIN LESS THAN 80 CHARACTERS): ");
            String reply = InputScanner.getScanner().nextLine();
            if (reply.length() > 80) {
                System.out.println("""
                        \nYOU HAVE ENTERED THE NUMBER OF CHARACTERS EXCEEDING THE SPECIFIED LIMIT
                        
                        DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
                String choice = InputScanner.getScanner().nextLine();
                if (choice.equals("1")) {
                    replyReview(admin);
                }
            }
            ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT * FROM admins " +
                    "WHERE firstName = '" + admin.getFirstName() + "' AND lastName = '" + admin.getLastName() + "';");
            String adminID = null;
            while (resultSet.next()) {
                adminID = resultSet.getString(1);
            }
            DatabaseConfiguration.statement.executeUpdate("UPDATE reviews SET reply = '" + reply + "' WHERE reviewID =  " + reviewID + ";");
            DatabaseConfiguration.statement.executeUpdate("UPDATE reviews SET adminID = " + adminID + " WHERE reviewID =  " + reviewID + ";");
            System.out.println("\nDATA SAVED");
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }
    }
}
