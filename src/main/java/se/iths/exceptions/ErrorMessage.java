package se.iths.exceptions;

import java.time.LocalDateTime;

public class ErrorMessage {

    private final String errorMessage;
    private final String localDateTime;

    public ErrorMessage(String errorMessage, String localDateTime) {
        this.errorMessage = errorMessage;
        this.localDateTime = localDateTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }
}
