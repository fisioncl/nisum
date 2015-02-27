package com.nisum.myinventory.util;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.nisum.myinventory.exception.StorageCSVItemParseException;
import com.nisum.myinventory.vo.Item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

//TODO: Add thread safety to this!!
public class InventoryCSV implements Inventory{
	private static final Logger log = LogManager.getLogger(Inventory.class);

	//TODO: Put this in a property file.
	private static final String FILE_NAME = "ohmy.csv";

	public void toCSV(List<Item> items) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");

		try {
			Appendable out = new FileWriter(FILE_NAME);
			CSVPrinter p = new CSVPrinter(out, CSVFormat.EXCEL);

			for (Item i : items) {
				p.print(i.getSerialNumber());
				p.print(i.getDescription());
				p.print(sdf.format(i.getBuyDate()));
				p.println();
			}

			p.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public Item get(Long sNumber) {
		return find(sNumber);
	}

	private Item find(Long sn) {
		Item item = null;

		try {
			Reader in = new FileReader(FILE_NAME);

			Iterable<CSVRecord> rd = CSVFormat.EXCEL.parse(in);

			for (CSVRecord r : rd) {
				if(Long.parseLong(r.get(0)) == sn) {
					item = this.parseRecord2Item(r);
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return item;
	}

	private Item parseRecord2Item(CSVRecord r) throws StorageCSVItemParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
		Item item = new Item();

		try {
			item.setSerialNumber(Long.parseLong(r.get(0)));
			item.setDescription(r.get(1));
			item.setBuyDate(sdf.parse(r.get(2)));
		} catch (ParseException p) {
			throw new StorageCSVItemParseException(p);
		}

		return item;
	}
}
