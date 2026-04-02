package enums;

public enum ErrorType {

    // 400 (Bad Request)
    INVALID_MENU_OPTION("Invalid menu option"),
    INVALID_USER_TYPE("Invalid user type"),
    ASSIGNMENT_ALREADY_COMPLETED("Assignment already completed"),

    // 404 (Not Found)
    ASSIGNMENT_NOT_FOUND("Assignment not found"),
    USER_NOT_FOUND("User not found");

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
