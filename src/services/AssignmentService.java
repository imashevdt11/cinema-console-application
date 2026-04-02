package services;

import configurations.DatabaseConfiguration;
import enums.ErrorType;
import models.Admin;
import models.Manager;
import repository.AssignmentRepository;
import repository.UserRepository;
import util.InputScanner;
import views.GeneralView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignmentService {

    public static void completeAssignment(Admin admin) {

        System.out.print("\nENTER THE ASSIGNMENT ID: ");
        String assignmentID = InputScanner.getScanner().nextLine();

        boolean isAssignmentExists = AssignmentRepository.isAssignmentExistsById(assignmentID);

        if (isAssignmentExists) {
            boolean isAssignmentCompleted = AssignmentRepository.isAssignmentCompleted(assignmentID);

            if (!isAssignmentCompleted) {
                String adminID = UserRepository.getAdminId(admin.getFirstName(), admin.getLastName());

                if (adminID != null) {
                    AssignmentRepository.setAssignmentStatusToDone(assignmentID, adminID);
                    UserRepository.increaseNumberOfCompletedAssignmentsForAdmin(
                            admin.getFirstName(), admin.getLastName());

                    GeneralView.printDataSuccessfullySavedMessage();
                }
            } else {
                GeneralView.printErrorMessage(ErrorType.ASSIGNMENT_ALREADY_COMPLETED);
            }
        } else {
            GeneralView.printErrorMessage(ErrorType.ASSIGNMENT_NOT_FOUND);
        }
    }

    public static void createAssignment(Manager manager) {

        System.out.print("\nENTER THE ASSIGNMENT (MUST CONTAIN LESS THAN 80 CHARACTERS): ");

        String assignment = InputScanner.getScanner().nextLine();

        if (assignment.length() > 80) {
            System.out.print("""
                    \nYOU HAVE ENTERED THE NUMBER OF CHARACTERS EXCEEDING THE SPECIFIED LIMIT

                    DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
            String choice = InputScanner.getScanner().nextLine();

            if (choice.equals("1")) createAssignment(manager);
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

    public static void getAssignments() {

        System.out.print("DO YOU WANT TO GET LIST OF COMPLETED ASSIGNMENTS OR NOT COMPLETED? (1 - COMPLETED / 2 - NOT COMPLETED): ");

        String choice = InputScanner.getScanner().nextLine();

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
