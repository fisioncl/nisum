package com.nisum.myinventory.exception;

/**
 * Created by cristian on 03-03-15.
 */
public class InventoryServiceException extends Exception {
    public InventoryServiceException(String message) {
        super(message);
    }

    public InventoryServiceException(Throwable cause) {
        super(cause);
    }

    public InventoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryServiceException(String message, Throwable cause,
                                          boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
