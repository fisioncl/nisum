package com.nisum.myinventory;

import com.nisum.myinventory.ui.ItemMenu;
import com.nisum.myinventory.ui.ItemMenuConsole;

public class EntryPoint {
	public EntryPoint() {}

	public static void main(String[] args) {
		ItemMenu im = new ItemMenuConsole();

		im.showMenu();
	}
}