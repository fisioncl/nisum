package com.nisum.myinventory.util;

import com.nisum.myinventory.vo.Item;

import java.util.List;

public interface Inventory {
	public Item get(Long sn);
	public void toCSV(List<Item> items);
}
