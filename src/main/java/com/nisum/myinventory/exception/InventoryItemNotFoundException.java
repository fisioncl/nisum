package com.nisum.myinventory.exception;

public class InventoryItemNotFoundException extends Exception {

	public InventoryItemNotFoundException() {
		super("The item requested was not found in the storage.");
	}

	public InventoryItemNotFoundException(String message) {
		super(message);
	}

	public InventoryItemNotFoundException(Throwable cause) {
		super(cause);
	}

	public InventoryItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventoryItemNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
