package com.nisum.myinventory.exception;

public class InventoryItemAlreadyExistException extends Exception {

	public InventoryItemAlreadyExistException() {
		super("You are traing to creare a item that already exist.");
	}

	public InventoryItemAlreadyExistException(String arg0) {
		super(arg0);
	}

	public InventoryItemAlreadyExistException(Throwable arg0) {
		super(arg0);
	}

	public InventoryItemAlreadyExistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InventoryItemAlreadyExistException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
