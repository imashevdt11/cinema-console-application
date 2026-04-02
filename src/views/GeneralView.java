package views;

import enums.ErrorType;

public class GeneralView {

    public static void printErrorMessage(ErrorType errorMessageType) {
        System.out.println(errorMessageType.getMessage());
        insertExtraLines(2);
    }

    public static void printTryAgainOptions() {
        System.out.print("WANT TO TRY AGAIN? (`YES` / `NO`)? ");
    }

    public static void printGoodbyeMessage() {
        System.out.println("GOODBYE! HAVE A NICE DAY!\n");
    }

    public static void printDataSuccessfullySavedMessage() {
        System.out.println("DATA SAVED SUCCESSFULLY!");
    }

    private static void insertExtraLines(int extraLinesNumber) {
        for (int i = 0; i < extraLinesNumber; i++) {
            System.out.println("\n");
        }
    }
}
