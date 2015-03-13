package com.nisum.myinventory.service;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import org.mockito.Mockito;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.*;

import java.util.Date;

import com.nisum.myinventory.exception.InventoryItemServiceException;
import com.nisum.myinventory.vo.Item;

public class InventoryItemServiceTest {
	InventoryItemService iis;

	@Before
	public void before() throws InventoryItemServiceException {
		iis = Mockito.mock(InventoryItemService.class);

		doAnswer(returnsFirstArg()).when(iis).createItem(any(Item.class), eq(false));
		when(iis.createItem(any(Item.class), eq(true))).thenReturn(new Item(1234L, "1234", new Date()));
	}

	@Test(expected=InventoryItemServiceException.class)
	public void createItemException() throws InventoryItemServiceException {
		Item item = new Item(0L, "", new Date());

		iis.createItem(item, false);
	}

	@Test
	public void createItemWithSN() throws InventoryItemServiceException {
		Item p = new Item(1234L, "Item with SerialNumber", new Date());
		Item r = iis.createItem(p, false);

		assertEquals("Items should be the same.", p, r);
	}

	@Test
	public void createItemWithSNAndTestExistence() throws InventoryItemServiceException {
		Item p = new Item(1234L, "Item with SerialNumber", new Date());
		iis.createItem(p, false);
		Item r = iis.getItemBySN(1234L);

		assertNotNull("Items should exists.", r);
	}

	@Test
	public void createItemWithoutSN() throws InventoryItemServiceException {
		Item p = new Item(null, "Item with SerialNumber", new Date());
		Item r = iis.createItem(p, true);

		assertNotNull("SN must be generated after creation.", r.getSerialNumber());
	}

	@Test(expected=InventoryItemServiceException.class)
	public void updateItemException() throws InventoryItemServiceException {
		iis.updateItem(new Item(-1L, "", new Date()));
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
		iis.deleteItemBySN(-1L);
	}

	@Test
	public void InventoryItemDelete() throws InventoryItemServiceException {
		Item p = new Item(1234L, "Item with SerialNumber", new Date());
		iis.createItem(p, false);

		Item r = iis.getItemBySN(1234L);

		assertNotNull("Items should exists.", r);

		iis.deleteItemBySN(1234L);

		assertNull("Item delete, result should be null", iis.getItemBySN(1234L));
	}
}