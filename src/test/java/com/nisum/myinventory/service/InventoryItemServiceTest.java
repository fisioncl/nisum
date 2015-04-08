package com.nisum.myinventory.service;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.*;

import java.util.Date;

import com.nisum.myinventory.domain.ItemRepository;
import com.nisum.myinventory.exception.InventoryItemServiceException;
import com.nisum.myinventory.exception.ItemDomainException;
import com.nisum.myinventory.vo.Item;

public class InventoryItemServiceTest { 
	@Mock(name="ir") private ItemRepository ir;
	@InjectMocks private InventoryItemServiceImpl iis;
	
	private static final Item[] validItemsWithSN = {
		new Item(System.nanoTime(), "Item with SerialNumber", new Date()),
		new Item(System.nanoTime(), "Another Item with SerialNumber", new Date())
	};
	
	private static final Item noSNItem = new Item(null, "Item without SerialNumber", new Date());
	
	private static final Item[] invalidItems = {
		new Item(-1L, "", new Date()), 
		new Item(null, "Item with SerialNumber", new Date()),
		new Item(0L, "", new Date())
	};

	@Before
	public void before() throws InventoryItemServiceException, ItemDomainException {
		MockitoAnnotations.initMocks(this);
		
		for (Item item : validItemsWithSN) {
			when(ir.create(eq(item), any(boolean.class))).then(returnsFirstArg());
		}
		
		when(ir.create(eq(noSNItem), eq(true))).thenReturn(new Item(System.nanoTime(), "Generated ID", new Date()));
		
		for (Item item : invalidItems) {			
			doThrow(ItemDomainException.class).when(ir).update(eq(item));
			doThrow(ItemDomainException.class).when(ir).delete(eq(item.getSerialNumber()));
			doThrow(ItemDomainException.class).when(ir).create(eq(item), eq(false));
		}
	}

	@Test(expected=InventoryItemServiceException.class)
	public void createItemException() throws InventoryItemServiceException {
		iis.createItem(invalidItems[0], false);
	}

	@Test
	public void createItemWithSN() throws InventoryItemServiceException {
		Item p = validItemsWithSN[0];
		Item r = iis.createItem(p, false);

		assertEquals("Items should be the same.", p, r);
	}

	@Test
	public void createItemWithoutSN() throws InventoryItemServiceException {
		Item p = noSNItem;
		Item r = iis.createItem(p, true);

		assertNotNull("SN must be generated after creation.", r.getSerialNumber());
	}

	@Test(expected=InventoryItemServiceException.class)
	public void updateItemException() throws InventoryItemServiceException {
		iis.updateItem(invalidItems[0]);
	}

	@Test
	public void getItemIsNull() {
		Item item = iis.getItemBySN(-1L);
		assertNull("Item should be null", item);
	}

	@Test(expected=InventoryItemServiceException.class)
	public void getItemByListException() throws InventoryItemServiceException{
		iis.listItemsByFilter(null);
	}

	@Test(expected=InventoryItemServiceException.class)
	public void getItemByListArgsException() throws InventoryItemServiceException{
		// At least one parameter should be set.
		iis.listItemsByFilter(new InventoryItemFilter());
	}

	// ---
	// How do I test that the Items returned are consistent with the filter ????
	// ---

	@Test(expected=InventoryItemServiceException.class)
	public void InventoryItemDeleteException() throws InventoryItemServiceException {
		// Item does not exists, should throws an Exception
		iis.deleteItemBySN(invalidItems[1].getSerialNumber());
	}

	@Test
	public void InventoryItemDelete() throws InventoryItemServiceException {
		Long sn = validItemsWithSN[1].getSerialNumber();
		iis.deleteItemBySN(sn);

		assertNull("Item delete, result should be null", iis.getItemBySN(sn));
	}
}