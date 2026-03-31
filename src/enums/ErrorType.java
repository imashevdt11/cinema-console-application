package enums;

public enum ErrorType {
    INVALID_USER_TYPE("Invalid user type"),
    INVALID_MENU_OPTION("Invalid menu option"),
    USER_NOT_FOUND("User not found");

    private String message;

    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
