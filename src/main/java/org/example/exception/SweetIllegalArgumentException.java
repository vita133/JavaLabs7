package org.example.exception;

public class SweetIllegalArgumentException extends Exception {

    public static final String message = "The name of the sweet cannot be null or empty.";
    public SweetIllegalArgumentException() {
            super(message);
        }
}
