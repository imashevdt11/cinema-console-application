package enums;

public enum UserType {
    ADMIN("1"),
    VISITOR("2"),
    MANAGER("3");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public static UserType fromValue(String value) {
        for (UserType userType : UserType.values()) {
            if (userType.value.equals(value)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Invalid user type value: " + value);
    }
}
