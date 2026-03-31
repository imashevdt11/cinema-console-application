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
}
