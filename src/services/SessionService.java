package services;

public class SessionService {

    /*
    public static void addSession(Admin admin) {
        try {
            int availableTickets = 0;
            int expensiveTickets = 0;
            int standardTickets = 0;
            int economyTickets = 0;
            int movieDuration = 0;
            int numberOfRows = 0;
            int numberOfPlaces = 0;
            String endTime;
            System.out.println("""
                    Before you start entering data, I would like to remind you of the following points:
                    
                    - there are only 24 hours in a day
                    - there are only 12 months in a year
                    - there are only 60 minutes in an hour
                    - you can add sessions only for this, 2022, year
                    - there are only 28 days in February 2022, 30 days in even months and 31 days in odd months");
                    
                    Please, keep this in mind and enter the correct data to save your time.
                    """);

            System.out.println("\nENTER THE SESSION'S START TIME. ENTER THE DATE IN NUMERICAL FORMAT.");
            System.out.print("HOUR: ");
            String hour = InputScanner.getScanner().nextLine();
            System.out.print("MINUTE: ");
            String minute = InputScanner.getScanner().nextLine();
            System.out.print("MONTH: ");
            String month = InputScanner.getScanner().nextLine();
            System.out.print("DAY: ");
            String day = InputScanner.getScanner().nextLine();
//            if ((Integer.parseInt(hour) < 0 || (Integer.parseInt(hour) > 23) || (Integer.parseInt(minute) < 0 || Integer.parseInt(minute) > 59)
//                    || (Integer.parseInt(month) <= 0 || Integer.parseInt(month) > 12) || (Integer.parseInt(month) == 2 && Integer.parseInt(day) > 28) ||
//                    (Integer.parseInt(month) % 2 == 0 && Integer.parseInt(day) > 30) || (Integer.parseInt(month) % 2 != 0 && Integer.parseInt(day) > 31)))
            String startTime = "2022-" + month + "-" + day + " " + hour + ":" + minute + ":00";
            ResultSet resultSetAdmin = DatabaseConfiguration.statement.executeQuery("SELECT * FROM admins WHERE password = '" + admin.getPassword() + "';");
            String adminID = null;
            if (resultSetAdmin.next()) adminID = resultSetAdmin.getString(1);
            System.out.print("MOVIE'S NAME: ");
            String movieName = InputScanner.getScanner().nextLine();
            ResultSet resultSetMovie = DatabaseConfiguration.statement.executeQuery("SELECT * FROM movies WHERE moviename = '" + movieName + "';");
            String movieID = null;
            if (resultSetMovie.next()) {
                movieID = resultSetMovie.getString(1);
                movieDuration = resultSetMovie.getInt(3);
            } else {
                System.out.println("\nMOVIE IS NOT IN THE DATABASE");
            }
            System.out.print("HALL'S NAME: ");
            String hallName = InputScanner.getScanner().nextLine();
            ResultSet resultSetHall = DatabaseConfiguration.statement.executeQuery("SELECT * FROM halls WHERE hallName = '" + hallName + "';");
            String hallID = null;
            if (resultSetHall.next()) {
                hallID = resultSetHall.getString(1);
                availableTickets = resultSetHall.getInt(4) * resultSetHall.getInt(5);
                economyTickets = availableTickets / 4;
                expensiveTickets = availableTickets / 4;
                standardTickets = availableTickets / 2;
                numberOfRows = resultSetHall.getInt(4);
                numberOfPlaces = resultSetHall.getInt(5);
            } else {
                System.out.println("\nHALL IS NOT IN THE DATABASE");
            }
            int convertToHours = movieDuration / 60;
            int leftMinutes = movieDuration % 60;
            if (leftMinutes + minute > 60) {
                convertToHours += ((leftMinutes + minute) / 60);
                minute = (leftMinutes + minute) - 60;
            } else minute += leftMinutes;
            endTime = "2022-" + month + "-" + day + " " + (hour + convertToHours) + ":" + minute + ":00";
            boolean isSessionExists = false;
            PreparedStatement prepareStatement = DatabaseConfiguration.connection.prepareStatement("SELECT * FROM sessions " +
                    "WHERE (startTime BETWEEN '" + startTime + "' AND '" + endTime + "' " +
                    "OR endTime BETWEEN '" + startTime + "' AND '" + endTime + "') AND hallID = '" + hallID + "';");
            {
                try (ResultSet resultSet = prepareStatement.executeQuery()) {
                    if (resultSet.next()) {
                        isSessionExists = true;
                    }
                }
            }
            if (isSessionExists) {
                System.out.println("\nADDING A SESSION IS NOT POSSIBLE. ANOTHER SESSION HAS ALREADY BEENA SCHEDULED FOR A GIVEN TIME IN THIS HALL");
                Admin.aMenu();
            }
            DatabaseConfiguration.statement.executeUpdate("INSERT INTO sessions " +
                    "VALUES(null, " + hallID + ", " + movieID + ", " + adminID + ", '" + startTime + "', '" + endTime + "', " +
                    economyTickets + ", " + standardTickets + ", " + expensiveTickets + ", " + availableTickets + ", 0)");
            ResultSet resultSetSession = DatabaseConfiguration.statement.executeQuery("SELECT * FROM sessions WHERE startTime = '" + startTime + "';");
            String sessionID = null;
            if (resultSetSession.next()) sessionID = resultSetSession.getString(1);
            System.out.print("\nECONOMY TICKET PRICE: ");
            String economyTicketPrice = scanner.nextLine();
            System.out.print("STANDARD TICKET PRICE: ");
            String standardTicketPrice = scanner.nextLine();
            System.out.print("EXPENSIVE TICKET PRICE: ");
            String expensiveTicketPrice = scanner.nextLine();
            for (int j = 1; j <= numberOfRows; j++) {
                if (j <= numberOfRows / 4) {
                    for (int i = 1; i <= numberOfPlaces; i++) {
                        DatabaseConfiguration.statement.executeUpdate("INSERT INTO tickets(sessionID, ticketPrice, type, rowW, place) " +
                                "VALUES(" + sessionID + ", " + economyTicketPrice + ", 'economy', " + j + ", " + i + ");");
                    }
                } else if (j > (numberOfRows / 4) && j <= (numberOfRows / 2) + (numberOfRows / 4)) {
                    for (int i = 1; i <= numberOfPlaces; i++) {
                        DatabaseConfiguration.statement.executeUpdate("INSERT INTO tickets(sessionID, ticketPrice, type, rowW, place) " +
                                "VALUES(" + sessionID + ", " + standardTicketPrice + ", 'standard', " + j + ", " + i + ");");
                    }
                } else if (j > (numberOfRows / 2) + (numberOfRows / 4)) {
                    for (int i = 1; i <= numberOfPlaces; i++) {
                        DatabaseConfiguration.statement.executeUpdate("INSERT INTO tickets(sessionID, ticketPrice, type, rowW, place) " +
                                "VALUES(" + sessionID + ", " + expensiveTicketPrice + ", 'expensive', " + j + ", " + i + ");");
                    }
                }
            }
            System.out.println("\nTHE SESSION IS ADDED");
        } catch (InputMismatchException | SQLException e) {
            System.out.printf("ERROR!!! Problems: {%s}", e.getMessage());
        }
    }
     */

    /*
    static void getSchedule() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);

        System.out.print("ENTER THE DATE (FOR EXAMPLE, 2018-11-23): ");
        String date = scanner.nextLine();
        String queryTime = "'" + date + " 00:00:00' AND '" + date + " 23:59:59'";

        ResultSet resultSet = DatabaseConfiguration.statement.executeQuery(
                "SELECT s.startTime, s.endTime, m.movieName, h.hallName, s.numberOfAvailableTickets, s.numberOfSoldTickets " +
                        "FROM sessions s, movies m, halls h " +
                        "WHERE startTime BETWEEN " + queryTime + " AND s.hallID = h.hallID AND s.movieID = m.movieID ORDER BY startTime;");

        System.out.printf("\n%-25s%-25s%-30s%-10s%-30s%-30s%n", "START TIME", "END TIME", "MOVIE", "HALL", "NUMBER OF AVAILABLE TICKETS",
                "NUMBER OF SOLD TICKETS");
        System.out.println();
        while (resultSet.next()) {
            System.out.printf("%-25s", resultSet.getString(1));
            System.out.printf("%-25s", resultSet.getString(2));
            System.out.printf("%-30s", resultSet.getString(3));
            System.out.printf("%-10s", resultSet.getString(4));
            System.out.printf("%-30s", resultSet.getString(5));
            System.out.printf("%-30s", resultSet.getString(6));
            System.out.println();
        }
    }
     */
}
