package com.nisum.myinventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.myinventory.domain.ItemRepository;
import com.nisum.myinventory.exception.InventoryItemServiceException;
import com.nisum.myinventory.exception.ItemDomainException;
import com.nisum.myinventory.vo.Item;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {
	@Autowired
	private ItemRepository ir;
	
	@Override
	public Item createItem(Item item, Boolean genSerialNumber) throws InventoryItemServiceException {
		List<String> hm = validateItem(item, !genSerialNumber);

		if(hm.size() != 0) {
			StringBuffer sf = new StringBuffer();

			for (String string : hm) {
				sf.append(string + "\n");
			}

			throw new InventoryItemServiceException("Validation error:\n" + sf.toString());
		}

		try {
			return ir.create(item, genSerialNumber);
		} catch (ItemDomainException e) {
			throw new InventoryItemServiceException("There was an error creating the Item.", e);
		}
	}

	@Override
	public Item getItemBySN(Long sn) {
		return ir.read(sn);
	}

	@Override
	public void updateItem(Item item) throws InventoryItemServiceException {
		try {
			ir.update(item);
		} catch (ItemDomainException e) {
			throw new InventoryItemServiceException("There was an error updating the Item.", e);
		}
	}

	@Override
	public List<Item> listAllItems() {
		return ir.readAll();
	}

	@Override
	public List<Item> listItemsByFilter(InventoryItemFilter filter) throws InventoryItemServiceException {
		if(filter == null || filter.isEmpty()) throw new InventoryItemServiceException("Filter can not be null.");

		//TODO: Not implemented yet.
		return null;
	}

	@Override
	public void deleteItemBySN(Long sn) throws InventoryItemServiceException {
		try {
			ir.delete(sn);
		} catch (ItemDomainException e) {
			throw new InventoryItemServiceException("The item can not be deleted.", e);
		}
	}

	private List<String> validateItem(Item item, Boolean validateSerialNumber) {
		List<String> hm = new ArrayList<String>();

		if(item.getBuyDate() == null) hm.add("BuyDate can't be null.");
		if(item.getDescription() == null) hm.add("Description can't be null or empty.");

		if(validateSerialNumber) {
			if(item.getSerialNumber() == null || item.getSerialNumber().longValue() <= 0) {
				hm.add("SerialNumer invalid value for Serial Number.");
			}
		}

		return hm;
	}
}