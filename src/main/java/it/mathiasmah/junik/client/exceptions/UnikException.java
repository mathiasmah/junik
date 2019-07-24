package it.mathiasmah.junik.client.exceptions;

import java.io.IOException;

/**
 * Signals that an Exception within the Unik framework occurred.
 */
public class UnikException extends IOException {

    public UnikException() {
    }

    public UnikException(String message) {
        super(message);
    }

    public UnikException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnikException(Throwable cause) {
        super(cause);
    }
}
