package com.nisum.myinventory.service;

import static org.junit.Assert.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.nisum.myinventory.exception.InventoryItemNotFoundException;
import org.junit.Test;

import com.nisum.myinventory.domain.InventoryCSV;
import com.nisum.myinventory.vo.Item;

import java.util.List;

public class InventoryCSVTest {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDD");

	@Test
	public void testConverter() throws ParseException {
        String dt = "20150101";

        String expected = "1,TestMe," + dt + "\r\n";

		List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "TestMe", sdf.parse(dt)));

        InventoryCSV i = new InventoryCSV();
        StringBuilder sb = new StringBuilder();

		i.toCSV(items, sb);

        assertEquals(expected, sb.toString());
	}

	@Test
	public void testItemNotFound() {
        InventoryCSV i = new InventoryCSV();
        Reader br = new StringReader("1,Lorem ipsum dolor sit amet,20150316\n");

        try {
            i.get(-0L, br);
            fail();
        } catch (InventoryItemNotFoundException e) {
            assertTrue("Exception thrown", true);
        }
	}

    @Test
    public void testItemReturned() throws InventoryItemNotFoundException{
        InventoryCSV i = new InventoryCSV();
        Reader br = new StringReader("123456,LoremIpsumDolorSitAmet,20150316\n");

        assertNotNull(i.get(123456L, br));
    }

    @Test
    public void testParsing() throws InventoryItemNotFoundException, ParseException {
        String dt = "20150316";

        InventoryCSV i = new InventoryCSV();
        Reader br = new StringReader("123456,LoremIpsumDolorSitAmet," + dt + "\n");

        Item ti = new Item(123456L, "LoremIpsumDolorSitAmet", sdf.parse(dt));
        Item ri = i.get(123456L, br);

        assertEquals("Item Serial Number must be the same.", ri.getSerialNumber(), ti.getSerialNumber());
        assertEquals("Item Description must be the same.", ri.getDescription(), ti.getDescription());
        assertEquals("Item BuyDate must be the same.", ri.getBuyDate(), ti.getBuyDate());
    }
}