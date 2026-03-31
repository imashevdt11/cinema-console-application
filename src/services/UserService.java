package services;

public class UserService {

    /*
    static void addAdmin() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nFIRST NAME (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
        String firstName = scanner.nextLine();
        System.out.print("LAST NAME (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
        String lastName = scanner.nextLine();
        System.out.print("PHONE NUMBER (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
        String phoneNumber = scanner.nextLine();
        System.out.print("PASSWORD (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
        String password = scanner.nextLine();
        if (firstName.length() > 20 || lastName.length() > 20 || phoneNumber.length() > 20 || password.length() > 20) {
            System.out.print("""
                    \nYOU HAVE ENTERED THE NUMBER OF CHARACTERS EXCEEDING THE SPECIFIED LIMIT

                    DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
            String choice = scanner.nextLine();
            if (choice.equals("1")) addAdmin();
            else chooseMenuOption();
        } else {
            Admin.setFirstName(firstName);
            Admin.setLastName(lastName);
            Admin.setPassword(password);
            boolean isUserExists = false;
            PreparedStatement preparedStatement = DatabaseConfiguration.connection.prepareStatement(
                    "SELECT password FROM admins WHERE password = ?"); {
                preparedStatement.setString(1, password);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) { if (resultSet.next()) isUserExists = true; }
            if (isUserExists) {
                System.out.println("""
                        \nADMIN WITH ENTERED DATA ALREADY EXISTS

                        DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
                String choice = scanner.nextLine();
                if (choice.equals("1")) addAdmin();
                else Manager.mMenu();
            } else {
                DatabaseConfiguration.statement.executeUpdate("INSERT INTO admins(firstname, lastname, phoneNumber, password) " +
                        "VALUES ('" + firstName + "', '" + lastName + "', '" + phoneNumber + "', '" + password + "');");
                System.out.println("\n" + firstName + " " + lastName + "'s DATA IS STORED IN THE DATABASE");
                Manager.mMenu();
            }
        }
    }
     */

    /*
    static void deleteAccount() throws ClassNotFoundException, SQLException, IOException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nARE YOU SURE YOU WANT TO DELETE YOUR ACCOUNT? (1 - YES / 0 - NO): ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            DatabaseConfiguration.statement.executeUpdate("UPDATE visitors SET status = 'former' " +
                    "WHERE firstname = '" + Visitor.getFirstName() + "' AND lastname = '" + Visitor.getLastName() + "' AND password = '"
                    + Visitor.getPassword() + "';");
            DatabaseConfiguration.statement.executeUpdate("UPDATE visitors SET deletionDate = now() " +
                    "WHERE firstname = '" + Visitor.getFirstName() + "' AND lastname = '" + Visitor.getLastName() + "' AND password = '" +
                    Visitor.getPassword() + "';");
            System.out.println("\nYOUR ACCOUNT HAS BEEN DELETED");
            UserMenuView.openMainMenu();
        }
        else vMenu();
    }
     */

    /*
    static void findAdmin() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nFIRST NAME: ");
        String firstName = scanner.nextLine();
        System.out.print("SECOND NAME: ");
        String lastName = scanner.nextLine();

        ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT * FROM admins " +
                "WHERE firstName = '" + firstName + "' AND lastName = '" +  lastName + "';");

        System.out.printf("\n%-25s%-25s%-25s%-15s%-35s%n", "FIRST NAME ", "LAST NAME", "PHONE NUMBER", "STATUS", "NUMBER OF COMPLETE ASSIGNMENTS");
        System.out.println();
        while (resultSet.next()) {
            System.out.printf("%-25s", resultSet.getString(2));
            System.out.printf("%-25s", resultSet.getString(3));
            System.out.printf("%-25s", resultSet.getString(4));
            System.out.printf("%-15s", resultSet.getString(6));
            System.out.printf("%-35s", resultSet.getString(7));
            System.out.println();
        }
        chooseMenuOption(scanner);
    }
     */

    /*
    static void findVisitor() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nFIRST NAME: ");
        String firstName = scanner.nextLine();
        System.out.print("SECOND NAME: ");
        String lastName = scanner.nextLine();

        ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT * FROM visitors " +
                "WHERE firstName = '" + firstName + "' AND lastName = '" +  lastName + "';");

        System.out.printf("\n%-30s%-30s%-30s%-30s%n", "FIRST NAME", "LAST NAME", "PHONE NUMBER", "STATUS");
        System.out.println();
        while (resultSet.next()) {
            System.out.printf("%-30s", resultSet.getString(2));
            System.out.printf("%-30s", resultSet.getString(3));
            System.out.printf("%-30s", resultSet.getString(4));
            System.out.printf("%-30s", resultSet.getString(7));
            System.out.println();
        }
    }
     */

    /*
    static void getBalance() throws ClassNotFoundException, SQLException, IOException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT balance FROM visitors " +
                "WHERE firstName = '" + Visitor.getFirstName() + "' AND lastName = '" + Visitor.getLastName() + "' AND password = '"  + Visitor.getPassword() + "';");
        int balance = 0;
        while (resultSet.next())  balance = resultSet.getInt(1);

        System.out.print("\n" + Visitor.getFirstName() + " " + Visitor.getLastName() + "'s balance: " + balance);
        Visitor.vMenu();
    }
     */

    /*
    static void getUsersStatistic() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nENTER THE TIME PERIOD FOR WHICH YOU WANT TO RECEIVE INFORMATION ABOUT REGISTERED AND UNREGISTERED USERS");
        System.out.print("\nFROM (FOR EXAMPLE, 2022-12-07): ");
        String start = scanner.nextLine();
        System.out.print("TO (FOR EXAMPLE, 2022-12-21): ");
        String end = scanner.nextLine();
        String queryTime = "'" + start + " 00:00:00' AND '" + end + " 23:59:59'";

        int registered = 0;
        int unregistered = 0;
        ResultSet resultSet = DatabaseConfiguration.statement.executeQuery("SELECT COUNT(*) FROM visitors " +
                "WHERE status = 'current' AND registrationDate BETWEEN " + queryTime + ";");
        while (resultSet.next()) registered = resultSet.getInt(1);
        ResultSet resultSet2 = DatabaseConfiguration.statement.executeQuery("SELECT COUNT(*) FROM visitors " +
                "WHERE status = 'former' AND deletionDate BETWEEN " + queryTime + ";");
        while (resultSet2.next()) unregistered = resultSet2.getInt(1);

        System.out.print("\nTHE RATION OF REGISTERED/UNREGISTERED USERS FROM " + start + " TO " + end + ": " + registered + "/" + unregistered);
        Manager.mMenu();
    }
     */

    /*
    static void removeAdmin() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nFIRST NAME: ");
        String firstName = scanner.nextLine();
        System.out.print("LAST NAME: ");
        String lastName = scanner.nextLine();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine();

        DatabaseConfiguration.statement.executeUpdate("UPDATE admins SET status = 'former' " +
                "WHERE firstname = '" + firstName + "' AND lastname = '" + lastName + "' AND password = '" + password + "';");
        DatabaseConfiguration.statement.executeUpdate("UPDATE admins SET dateofdismissal = now() " +
                "WHERE firstname = '" + firstName + "' AND lastname = '" + lastName + "' AND password = '" + password + "';");

        System.out.println("\n" + firstName + " " + lastName + "'s DATA CHANGED");
        chooseMenuOption(scanner);
    }
     */

    /*
    static void replenishTheBalance() throws ClassNotFoundException, SQLException, IOException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nENTER THE AMOUNT OF MONEY YOU WANT TO REPLENISH YOUR BALANCE WITH: ");
        int amountOfMoney = scanner.nextInt();

        DatabaseConfiguration.statement.executeUpdate("UPDATE visitors SET balance = (balance + " + amountOfMoney + ") " +
                "WHERE password = '" + Visitor.getPassword() + "';");

        System.out.println("\nDATA SAVED");
        Visitor.vMenu();
    }
     */

    /*
    public static void signUp() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scannerInt = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("\nFIRST NAME (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
            String firstName = scanner.nextLine();
            System.out.print("LAST NAME (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
            String lastName = scanner.nextLine();
            System.out.print("PHONE NUMBER (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
            String phoneNumber = scanner.nextLine();
            System.out.print("PASSWORD (MUST CONTAIN LESS THAN 20 CHARACTERS): ");
            String password = scanner.nextLine();
            System.out.print("BALANCE (IN KGS): ");
            int balance = scannerInt.nextInt();
            if (firstName.length() > 20 || lastName.length() > 20 || password.length() > 20 || phoneNumber.length() > 20) {
                System.out.print("""
                        \nYOU HAVE ENTERED THE NUMBER OF CHARACTERS EXCEEDING THE SPECIFIED LIMIT

                        DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
                String choice = scanner.nextLine();
                if (choice.equals("1")) signUp();
                else UserMenuView.openMainMenu();
            }
            else {
                Visitor.setFirstName(firstName);
                Visitor.setLastName(lastName);
                Visitor.setPassword(password);
                boolean isUserExists = false;
                PreparedStatement prepareStatement = DatabaseConfiguration.connection.prepareStatement("SELECT password FROM visitors WHERE password = ?");{
                    prepareStatement.setString(1, password);
                    try (ResultSet resultSet = prepareStatement.executeQuery()) {
                        if (resultSet.next()) isUserExists = true;}}
                if (isUserExists) {
                    System.out.print("""
                            \nVISITOR WITH ENTERED DATA ALREADY EXISTS

                            DO YOU WANT TO TRY AGAIN? (1 - YES / 0 - NO):\040""");
                    String choice = scanner.nextLine();
                    if (choice.equals("1")) signUp();
                    else vMenu();
                } else {
                    DatabaseConfiguration.statement.executeUpdate("INSERT INTO visitors(firstName, lastName, phoneNumber, password, balance) " +
                            "VALUES ('" + firstName + "', '" + lastName + "', '" + phoneNumber + "', '" + password + "', " + balance + ");");
                    System.out.println("\n" + firstName + " " + lastName + "'s DATA IS STORED IN THE DATABASE");
                    UserMenuView.openMainMenu();
                }
            }
        } catch (InputMismatchException exception) {
            System.out.println("\nPLEASE, ENTER THE BALANCE IN NUMERIC FORMAT");
            signUp();
        }
    }
     */
}
