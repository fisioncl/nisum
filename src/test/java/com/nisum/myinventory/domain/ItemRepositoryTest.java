package com.nisum.myinventory.domain;

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
import com.nisum.myinventory.exception.ItemDomainException;

public class ItemRepositoryTest {
	ItemRepository ir;

	@Before
	public void before() throws ItemDomainException{
		//ir = Mockito.mock(ItemRepository.class);
		ir = new ItemRepositoryCSV();
		//doAnswer(returnsFirstArg()).when(ir).create(any(Item.class), eq(false));
		//when(ir.create(any(Item.class), eq(true))).thenReturn(new Item(1234L, "1234", new Date()));
	}

	@Test(expected=ItemDomainException.class)
	public void createItemException() throws ItemDomainException{
		Item item = new Item(0L, "", new Date());
		ir.create(item, false);
	}

	@Test
	public void createItemWithSN() throws ItemDomainException{
		Item p = new Item(System.nanoTime(), "Item with SerialNumber", new Date());
		Item r = ir.create(p, false);

		assertEquals("Items should be the same.", p, r);
	}

	@Test
	public void createItemWithSNAndTestExistence() throws ItemDomainException{
		Long id = System.nanoTime();
		
		Item p = new Item(id, "Item with SerialNumber", new Date());
		ir.create(p, false);
		Item r = ir.read(id);

		assertNotNull("Items should exists.", r);
	}

	@Test
	public void createItemWithoutSN() throws ItemDomainException{
		Item p = new Item(null, "Item with SerialNumber", new Date());
		Item r = ir.create(p, true);

		assertNotNull("SN must be generated after creation.", r.getSerialNumber());
	}

	@Test(expected=ItemDomainException.class)
	public void updateItemException() throws ItemDomainException {
		ir.update(new Item(-1L, "", new Date()));
	}

	@Test
	public void getItemIsNull() {
		Item item = ir.read(-1L);
		assertNull("Item should be null", item);
	}

	// ---
	// How do I test that the Items returned are consistent with the filter ????
	// ---

	@Test(expected=ItemDomainException.class)
	public void InventoryItemDeleteException() throws ItemDomainException{
		// Item does not exists, should throws an Exception
		ir.delete(-1L);
	}

	@Test
	public void InventoryItemDelete() throws ItemDomainException {
		Long id = System.nanoTime();
		
		Item p = new Item(id, id.toString() + ": Item with SerialNumber", new Date());
		ir.create(p, false);

		Item r = ir.read(id);

		assertNotNull("Items should exists.", r);

		ir.delete(id);

		assertNull("Item delete, result should be null", ir.read(id));
	}
}