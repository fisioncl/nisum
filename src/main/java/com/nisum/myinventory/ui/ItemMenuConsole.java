package com.nisum.myinventory.ui;

import com.nisum.myinventory.exception.UIException;
import com.nisum.myinventory.vo.Item;

import java.io.IOException;
import java.io.PrintStream;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nisum.myinventory.ui.Action;

public class ItemMenuConsole implements ItemMenu {
	private static final Logger log = LogManager.getLogger(ItemMenuConsole.class);

	PrintStream out;
	InputStream in;

	public ItemMenuConsole() {
		out = System.out;
		in = System.in;
	}

	@Override
	public void showMenu() {
		int op = 0;

		do {
			out.println("(1) List All");
			out.println("(2) List Item By S.N.");
			out.println("(3) New Item");
			out.println("(4) Update Item");
			out.println("(5) Delete");
			out.println("\n");
			out.println("(0) Exit");
			out.println("\n\n");

			out.println("Please choose an option: ");

			try {
				op = in.read();
				processOp(op);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while(op != 0);
	}

	private void processOp(int op) {
		if( op == 1) this.showAllItems();
		if( op == 2) this.showItemBySN();;
		if( op == 3) this.showAllItems();
		if( op == 4) this.showAllItems();
		if( op == 5) this.showAllItems();
	}

	private void showItemBySN() {
		
	}

	private void showError(UIException uie) {
		out.print("Something whent wrong:\n " + uie.getMessage());
		out.print("Press any key to continue...");
	}

	@Override
	public void showItem(Long sn) throws UIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void showAllItems() {
		// TODO Auto-generated method stub
	}

	@Override
	public Item createItem(Item it) throws UIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item updateItem(Long sn, Item i) throws UIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteItem(Long sn) throws UIException {
		// TODO Auto-generated method stub
	}
}