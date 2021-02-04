package com.coding.task.exception;

public class ApplicationRunException extends RuntimeException {

    public ApplicationRunException(String message, Throwable cause) {
        super(message, cause);
    }
}
