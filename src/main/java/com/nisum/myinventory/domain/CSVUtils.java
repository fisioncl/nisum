package com.nisum.myinventory.domain;

import java.util.ArrayList;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.nisum.myinventory.exception.StorageCSVItemParseException;
import com.nisum.myinventory.vo.Item;
import java.util.Date;

public class CSVUtils {
	public CSVUtils() {
		// TODO Auto-generated constructor stub
	}

	public List<List<String>> itemsToList(List<Item> il) {
		List<List<String>> list = new ArrayList<List<String>>();

		for(Item item : il) {
			List<String> sl = new ArrayList<String>();

			sl.add(String.valueOf(item.getSerialNumber()));
			sl.add(item.getDescription());
			sl.add(String.valueOf(item.getBuyDate().getTime()));

			list.add(sl);
		}

		return list;
	}

	public Item csvToItem(CSVRecord r) throws Exception {
		Item item = new Item();

		try {
			item.setSerialNumber(Long.parseLong(r.get(0)));
			item.setDescription(r.get(1));
			item.setBuyDate(new Date(Long.parseLong(r.get(2))));
		} catch (Exception e) {
			throw new StorageCSVItemParseException(e);
		}

		return item;
	}
}
