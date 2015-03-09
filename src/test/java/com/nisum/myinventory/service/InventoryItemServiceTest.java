package com.nisum.myinventory.service;

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
		
		doReturn(returnsFirstArg()).when(iis).createItem(any(Item.class), any(Boolean.class));
	}

	@Test(expected=InventoryItemServiceException.class)
	public void createItemException() throws InventoryItemServiceException {
		Item item = new Item(0L, "", new Date());

		Item returned = iis.createItem(item, false);

		if(!returned.equals(item)) fail();
	}
}
