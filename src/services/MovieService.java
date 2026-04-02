package services;

import configurations.DatabaseConfiguration;
import util.InputScanner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieService {

    public static void addMovie() {
        System.out.print("\nMOVIE'S NAME: ");
        String movieName = InputScanner.getScanner().nextLine();
        boolean isMovieExists = false;

        try {
            PreparedStatement prepareStatement = DatabaseConfiguration.connection.prepareStatement(
                    """
                    SELECT moviename
                    FROM movies
                    WHERE moviename = ?;
                    """);

            prepareStatement.setString(1, movieName);

            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) isMovieExists = true;

            if (isMovieExists) {
                System.out.println("\nTHIS MOVIE IS ALREADY IN DATABASE");
            } else {
                System.out.print("DURATION(MINUTES) (MUST BE GREATER THAN 60 AND LESS THAN 240): ");
                String duration = InputScanner.getScanner().nextLine();
                System.out.print("COUNTRY (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
                String country = InputScanner.getScanner().nextLine();
                System.out.print("GENRE (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
                String genre = InputScanner.getScanner().nextLine();
                System.out.print("PRODUCTION YEAR (MUST BE FOUR DIGITS): ");
                String productionYear = InputScanner.getScanner().nextLine();
                System.out.print("PRODUCER (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
                String producer = InputScanner.getScanner().nextLine();

                if (Integer.parseInt(productionYear) > 2022 || Integer.parseInt(productionYear) < 1900) {
                    System.out.print("""
                            \nYOU ENTERED PRODUCTION YEAR INCORRECTLY
                            
                            DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");

                    String choice = InputScanner.getScanner().nextLine();
                    if (choice.equals("1")) {
                        addMovie();
                    }
                } else if (country.length() > 20 || genre.length() > 20 || producer.length() > 20) {
                    System.out.print("""
                            \nYOU HAVE ENTERED THE NUMBER OF CHARACTERS EXCEEDING THE SPECIFIED LIMIT
                            
                            DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");

                    String choice = InputScanner.getScanner().nextLine();
                    if (choice.equals("1")) {
                        addMovie();
                    }
                } else if (Integer.parseInt(duration) > 240 || Integer.parseInt(duration) < 60) {
                    System.out.print(""" 
                            \nYOU HAVE ENTERED AN INVALID NUMBER OF MINUTES
                            
                            DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");

                    String choice = InputScanner.getScanner().nextLine();
                    if (choice.equals("1")) {
                        addMovie();
                    }
                } else {
                    DatabaseConfiguration.statement.executeUpdate("INSERT into movies " +
                            "VALUES(NULL, '" + movieName + "', " + duration + ", '" +
                            country + "', '" + genre + "', " + productionYear + ", '" + producer + "');");
                    System.out.println("\nTHE MOVIE HAS BEEN ADDED TO THE DATABASE");
                }
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.printf("ERROR!!! Problems: {%s}", e.getMessage());
        }
    }

    public static void getMovies() {
        try {
            ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT * FROM movies;");
            System.out.printf("\n%-35s%-30s%-20s%-20s%-30s%-20s%n", "MOVIE", "DURATION(MINUTES)", "COUNTRY", "GENRE", "PRODUCER", "PRODUCTION YEAR");
            System.out.println();
            while (resultSet.next()) {
                System.out.printf("%-35s", resultSet.getString(2));
                System.out.printf("%-30s", resultSet.getString(3));
                System.out.printf("%-20s", resultSet.getString(4));
                System.out.printf("%-20s", resultSet.getString(5));
                System.out.printf("%-30s", resultSet.getString(7));
                System.out.printf("%-20s", resultSet.getString(6));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.printf("ERROR!!! SQL problems: {%s}", e.getMessage());
        }
    }
}
