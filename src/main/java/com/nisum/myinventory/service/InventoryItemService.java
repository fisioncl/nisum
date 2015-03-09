package com.nisum.myinventory.service;

import com.nisum.myinventory.vo.Item;
import com.nisum.myinventory.exception.InventoryItemServiceException;
import java.util.List;

public interface InventoryItemService {
	public Item createItem(Item item, Boolean genSerialNumber) throws InventoryItemServiceException;
	public Item getItemBySN(Long sn);
	public void updateItem(Item item) throws InventoryItemServiceException;
	public List<Item> listAllItems();
	public List<Item> listItemsByFilter(InventoryItemFilter filter) throws InventoryItemServiceException;
	public void deleteItemBySN(Long sn) throws InventoryItemServiceException;
}