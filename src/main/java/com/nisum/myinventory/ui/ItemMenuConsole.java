package com.nisum.myinventory.ui;

import com.nisum.myinventory.exception.InventoryItemServiceException;
import com.nisum.myinventory.exception.UIException;
import com.nisum.myinventory.vo.Item;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nisum.myinventory.service.InventoryItemService;
import com.nisum.myinventory.service.InventoryItemServiceImpl;

public class ItemMenuConsole implements ItemMenu {
	private static final Logger log = LogManager.getLogger(ItemMenuConsole.class);

	private InventoryItemService iis = new InventoryItemServiceImpl();

	private static enum MenuOption {
		LIST(1),
		SHOW(2),
		NEW(3),
		UPDATE(4),
		DELETE(5),
		EXIT(0);

		int option;

		MenuOption(int option) { this.option = option; }

		public int toInt() {
			return this.option;
		}
	}

	PrintStream out;
	Scanner in;

	public ItemMenuConsole() {
		out = System.out;
		in = new Scanner(System.in);
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
				op = in.nextInt();

				if( op == MenuOption.LIST.toInt()) this.showAllItems();
				if( op == MenuOption.SHOW.toInt()) this.showItemBySN();;
				if( op == MenuOption.NEW.toInt()) this.createItem();;
				if( op == MenuOption.UPDATE.toInt()) this.updateItem();;
				if( op == MenuOption.DELETE.toInt()) this.deleteItemBySN();;
			} catch (InputMismatchException e) {
				out.println("Invalid option passed to showMenu.");
				this.paktc();
			}
		} while(op != MenuOption.EXIT.toInt());
		out.println("\n\nProgram ended.\n Have a nice day :)");
	}

	private void showItemBySN() {
		out.print("What's the Serial Number of the Item: ");

		try {
			Long k = in.nextLong();
			if(iis.getItemBySN(k) == null) {
				out.print("No Item with this SN was found.");
			} else {
				this.showItem(k);
			}
			this.paktc();
		} catch (InputMismatchException | UIException e) {
			log.warn("Invalid option passed to showItemBySN.", e);
		}
	}

	private void createItem() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Long sn;
		String desc;
		Date buy;

		out.println("Serial Number (Only Numbers): ");
		sn = in.nextLong();

		out.println("Description: ");
		desc = in.next();

		out.println("Buy Date (dd/mm/yyyy):");
		String tmp = in.next();
		try {
			buy = sdf.parse(tmp);
		} catch (ParseException e) {
			out.println("Date entered is not valid.");
			this.paktc();
			return;
		}

		try {
			this.createItem(new Item(sn, desc, buy));
			out.println("The item was created successfully.");
		} catch (UIException e) {
			out.println("The item can't be created.\n Maybe the serial number already exist.\n\n" + e.getMessage());
		}

		this.paktc();
	}

	private void updateItem() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Item item = null;
		Long sn = -1L;
		String desc;
		Date buy;

		out.print("What's the Serial Number of the Item: ");

		try {
			sn = in.nextLong();
			item = iis.getItemBySN(sn);
			if(sn == null) {
				out.print("No Item with this SN was found.");
				this.paktc();
				return;
			}
		} catch (InputMismatchException e) {
			log.warn("Invalid option passed to showItemBySN.", e);
		}

		out.println("New description\n Actual: " + item.getDescription() +  " \n ");
		desc = in.next();

		out.println("New buy date (dd/mm/yyyy)\n Actual: " + sdf.format(item.getBuyDate()) + "\n");
		String tmp = in.next();
		try {
			buy = sdf.parse(tmp);
		} catch (ParseException e) {
			out.println("Date entered is not valid.");
			this.paktc();
			return;
		}

		try {
			this.updateItem(sn, new Item(sn, desc, buy));
			out.println("The item was updated successfully.");
		} catch (UIException e) {
			out.println("The item can't be updated.\n\n" + e.getMessage());
		}

		this.paktc();
	}

	private void deleteItemBySN() {
		out.print("What's the Serial Number of the Item: ");

		try {
			Long k = in.nextLong();
			if(k == null) {
				out.print("No Item with this SN was found.");
			} else {
				this.deleteItem(k);
				out.print("Item delete successfully.");
			}
			this.paktc();
		} catch (InputMismatchException | UIException e) {
			out.print("There was an error traying to delete this item. Maybe the serial number entered is't wrong.\n" + e.getMessage());
		}
	}

	private void paktc() {
		out.println("Press the c key to continue...");
		in.next();
	}

	/*private void showError(UIException uie) {
		out.print("Something whent wrong:\n " + uie.getMessage());
		out.print("Press any key to continue...");
	}*/

	@Override
	public void showItem(Long sn) throws UIException {
		Item item = iis.getItemBySN(sn);

		if(item == null) {
			out.println("Item with SN: " + sn + " not found");
		} else {
			out.println("SN:          " + item.getSerialNumber());
			out.println("Description: " + item.getDescription());
			out.println("Buy Date:    " + item.getBuyDate());
		}
	}

	@Override
	public void showAllItems() {
		List<Item> items = iis.listAllItems();

		if(items == null) {
			out.println("No items found.");
		} else {
			for (Item i : items) {
				out.println(String.format("%1$s %2$s %3$s", i.getSerialNumber(), i.getDescription(), i.getBuyDate()));
			}
		}

		this.paktc();
	}

	@Override
	public Item createItem(Item it) throws UIException {
		try {
			return iis.createItem(it, false);
		} catch (InventoryItemServiceException e) {
			throw new UIException("Can't create the Item.", e);
		}
	}

	@Override
	public Item updateItem(Long sn, Item i) throws UIException {
		try {
			iis.updateItem(i);
			return iis.getItemBySN(sn);
		} catch (InventoryItemServiceException e) {
			throw new UIException("Can't update the Item.", e);
		}
	}

	@Override
	public void deleteItem(Long sn) throws UIException {
		try {
			iis.deleteItemBySN(sn);
		} catch (InventoryItemServiceException e) {
			throw new UIException("There was a problem deleting the item.", e);
		}
	}
}