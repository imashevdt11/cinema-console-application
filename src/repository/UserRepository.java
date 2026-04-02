package repository;

import configurations.DatabaseConfiguration;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public static boolean isUserExists(User user, String loginQuery) {
        boolean isUserExists = false;
        try {
            PreparedStatement preparedStatement = DatabaseConfiguration.connection.prepareStatement(loginQuery);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            isUserExists = resultSet.next();
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }
        return isUserExists;
    }

    public static String getAdminId(String firstName, String lastName) {
        String adminID = null;
        try {
            PreparedStatement preparedStatement =
                DatabaseConfiguration.connection.prepareStatement(
                    """
                    SELECT * FROM admins
                    WHERE firstName = ?
                        AND lastName = ?;
                    """);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                adminID = resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }
        return adminID;
    }

    public static void increaseNumberOfCompletedAssignmentsForAdmin(String firstName, String lastName) {
        try {
            PreparedStatement preparedStatement =
                DatabaseConfiguration.connection.prepareStatement(
                    """
                   UPDATE admins
                   SET numberOfCompletedAssignments = numberOfCompletedAssignments + 1
                   WHERE firstName = ?
                       AND lastName = ?;
                   """);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }
    }
}
