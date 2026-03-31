package services;

import configurations.DatabaseConfiguration;
import models.Admin;
import models.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AssignmentService {

    public static void completeAssignment(Scanner scanner, Admin admin) {

        System.out.print("\nENTER THE ASSIGNMENT ID: ");
        String assignmentID = scanner.nextLine();
        boolean isAssignmentExists = false;

        try {
            PreparedStatement preparedStatement = DatabaseConfiguration.connection.prepareStatement("SELECT assignmentID " +
                    "FROM assignments WHERE assignmentID = ?;");

            preparedStatement.setString(1, assignmentID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) isAssignmentExists = true;
            }

            if (isAssignmentExists) {
                boolean isAssignmentComplete = false;
                PreparedStatement preparedStatement2 = DatabaseConfiguration.connection.prepareStatement("SELECT * FROM assignments " +
                        "WHERE assignmentID = ? AND status = 'not done';");
                {
                    preparedStatement2.setString(1, assignmentID);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) isAssignmentComplete = true;
                    }
                }
                ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT * FROM admins " +
                        "WHERE firstName = '" + admin.getFirstName() + "' AND lastName = '" + admin.getLastName() + "';");
                String adminID = null;
                while (resultSet.next()) {
                    adminID = resultSet.getString(1);
                }
                if (isAssignmentComplete) {
                    DatabaseConfiguration.statement.executeUpdate("UPDATE assignments SET status = 'done' WHERE assignmentID = " + assignmentID + ";");
                    DatabaseConfiguration.statement.executeUpdate("UPDATE assignments SET dateOfCompletion = now() WHERE assignmentID = " + assignmentID + ";");
                    DatabaseConfiguration.statement.executeUpdate("UPDATE admins SET numberOfCompletedAssignments = (numberOfCompletedAssignments + 1) " +
                            "WHERE password = '" + admin.getPassword() + "';");
                    DatabaseConfiguration.statement.executeUpdate("UPDATE assignments SET adminID = " + adminID + " WHERE assignmentID = " + assignmentID + ";");
                    System.out.println("\nDATA SAVED");
                } else {
                    System.out.println("THIS ASSIGNMENT HAS ALREADY BEEN COMPLETED");
                }
            } else {
                System.out.println("THE ASSIGNMENT WITH THE SPECIFIED ID IS NOT IN THE DATABASE");
             }
        } catch (SQLException e) {
            System.out.println("qwerqwe");
        }
    }

    public static void createAssignment(Scanner scanner, Manager manager) {

        System.out.print("\nENTER THE ASSIGNMENT (MUST CONTAIN LESS THAN 80 CHARACTERS): ");

        String assignment = scanner.nextLine();

        if (assignment.length() > 80) {
            System.out.print("""
                    \nYOU HAVE ENTERED THE NUMBER OF CHARACTERS EXCEEDING THE SPECIFIED LIMIT

                    DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
            String choice = scanner.nextLine();

            if (choice.equals("1")) createAssignment(scanner, manager);
        }
        try {
            ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT * FROM managers WHERE password = '" + manager.getPassword() + "';");

            String managerData = null;
            if (resultSet.next()) managerData = resultSet.getString(1);
            String managerID = managerData;

            DatabaseConfiguration.statement.executeUpdate("INSERT INTO assignments(managerID, assignment) VALUES(" + managerID + ", '" + assignment + "');");
            System.out.println("\nTHE ASSIGNMENT HAS BEEN CREATED");
        } catch (SQLException e) {
            System.out.println("JIKFRAF");
        }
    }

    public static void getAssignments(Scanner scanner) {

        System.out.print("DO YOU WANT TO GET LIST OF COMPLETED ASSIGNMENTS OR NOT COMPLETED? (1 - COMPLETED / 2 - NOT COMPLETED): ");

        String choice = scanner.nextLine();

        try {
            if (choice.equals("1")) {
                ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT s.assignment, s.dateOfCompletion, a.firstName, a.lastName " +
                        "FROM assignments s, admins a WHERE a.adminID = s.adminID;");
                System.out.printf("\n%-80s%-25s%-40s%n", "ASSIGNMENT", "DATE OF COMPLETION", "ADMIN");
                System.out.println();
                while (resultSet.next()) {
                    System.out.printf("%-80s", resultSet.getString(1));
                    System.out.printf("%-25s", resultSet.getString(2));
                    System.out.printf(resultSet.getString(3) + " " + resultSet.getString(4));
                    System.out.println();
                }
            } else if (choice.equals("2")) {
                ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT assignmentID, assignment, dateOfAppointment " +
                        "FROM assignments s WHERE status = 'not done';");
                System.out.printf("\n%-20s%-90s%-30s%n", "ASSIGNMENT ID", "ASSIGNMENT", "DATE OF APPOINTMENT");
                System.out.println();
                while (resultSet.next()) {
                    System.out.printf("%-20s", resultSet.getString(1));
                    System.out.printf("%-90s", resultSet.getString(2));
                    System.out.printf("%-30s", resultSet.getString(3));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println("JIKFRAF");
        }
    }
}
