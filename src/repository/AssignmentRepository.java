package repository;

import configurations.DatabaseConfiguration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignmentRepository {

    public static boolean isAssignmentCompleted(String assignmentID) {
        boolean isAssignmentCompleted = false;
        try {
            PreparedStatement preparedStatement =
                DatabaseConfiguration.connection.prepareStatement(
                    """
                    SELECT *
                    FROM assignments
                    WHERE assignmentID = ?
                        AND status = 'done';
                    """);
            preparedStatement.setString(1, assignmentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            isAssignmentCompleted = resultSet.next();
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }
        return isAssignmentCompleted;
    }

    public static boolean isAssignmentExistsById(String assignmentID) {
        boolean isAssignmentExists = false;
        try {
            PreparedStatement preparedStatement =
                DatabaseConfiguration.connection.prepareStatement(
                    """
                    SELECT assignmentID
                    FROM assignments
                    WHERE assignmentID = ?;
                    """);
            preparedStatement.setString(1, assignmentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            isAssignmentExists = resultSet.next();
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }
        return isAssignmentExists;
    }

    public static void setAssignmentStatusToDone(String assignmentId, String adminId) {
        try {
            PreparedStatement preparedStatement =
                DatabaseConfiguration.connection.prepareStatement(
                    """
                    UPDATE assignments
                    SET status = 'done',
                        dateOfCompletion = now(),
                        adminId = ?
                    WHERE assignmentID = ?;
                    """);
            preparedStatement.setString(1, assignmentId);
            preparedStatement.setString(2, adminId);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }
    }
}