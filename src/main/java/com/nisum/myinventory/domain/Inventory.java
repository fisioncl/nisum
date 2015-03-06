package com.nisum.myinventory.domain;

import com.nisum.myinventory.vo.Item;
import com.nisum.myinventory.exception.InventoryItemNotFoundException;

import java.util.List;
import java.io.Reader;

public interface Inventory {
	public Long toCSV(List<Item> items, Appendable destination);
	public Item get(Long itemSN, Reader data) throws InventoryItemNotFoundException;
}
