package com.nisum.myinventory.exception;

public class InventoryItemServiceException extends Exception {
    public InventoryItemServiceException(String message) {
        super(message);
    }

    public InventoryItemServiceException(Throwable cause) {
        super(cause);
    }

    public InventoryItemServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryItemServiceException(String message, Throwable cause,
                       boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
