package com.nisum.myinventory.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.nisum.myinventory.util.InventoryCSV;
import com.nisum.myinventory.vo.Item;

import java.util.List;

public class InventoryCSVTest {
	private List<Item> items = new ArrayList<Item>();

	@Before
	public void before() {
		items.add(new Item(1L, "Lorem ipsum dolor sit amet", new Date()));
		items.add(new Item(2L, "consectetur adipiscing", new Date()));
		items.add(new Item(3L, "sed do eiusmod tempo", new Date()));
		items.add(new Item(4L, "eos qui ratione voluptatem", new Date()));
		items.add(new Item(5L, "modi tempora incidunt ut", new Date()));
		items.add(new Item(6L, "quam nihil molestiae ", new Date()));
	}

	@Test
	public void test() {
		InventoryCSV i = new InventoryCSV();

		i.toCSV(items);

		assertNotNull("Item mut be returned.", i.get(3L));
		assertNull("Item not exist. Must be null.", i.get(23456L));
	}

	@Test
	public void testException() {

	}
}
