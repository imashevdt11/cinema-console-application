package constants;

public class SqlQueries {

    public final static String ADMINS_LOGIN_QUERY = "SELECT firstname, lastname, status, password " +
            "FROM admins WHERE firstName = ? AND lastName = ? AND password = ? AND status = 'current'";

    public final static String MANAGERS_LOGIN_QUERY = "SELECT firstname, lastname, status, password " +
            "FROM managers WHERE firstName = ? AND lastName = ? AND password = ? AND status = 'current'";

    public final static String VISITORS_LOGIN_QUERY = "SELECT firstname, lastname, status, password " +
            "FROM visitors WHERE firstName = ? AND lastName = ? AND password = ? AND status = 'current'";

}
