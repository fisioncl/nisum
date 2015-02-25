package com.nisum.myinventory.exception;

public class StorageCSVItemParseException extends Exception {

	public StorageCSVItemParseException() {
		super("The item cannot be parsed");
	}

	public StorageCSVItemParseException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public StorageCSVItemParseException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public StorageCSVItemParseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public StorageCSVItemParseException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
