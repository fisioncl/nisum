package com.nisum.myinventory.exception;

/**
 * Created by cristian on 03-03-15.
 */
public class UIException extends Exception {
    public UIException(String message) {
        super(message);
    }

    public UIException(Throwable cause) {
        super(cause);
    }

    public UIException(String message, Throwable cause) {
        super(message, cause);
    }

    public UIException(String message, Throwable cause,
                       boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
