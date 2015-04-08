package com.nisum.myinventory.domain;

import java.util.LinkedList;
import java.util.List;

import com.nisum.myinventory.exception.ItemDomainException;
import com.nisum.myinventory.util.ApplicationConfig;
import com.nisum.myinventory.vo.Item;

import java.util.Properties;
import java.io.Reader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ItemRepositoryCSV implements ItemRepository {
	private static final Logger log = LogManager.getLogger(ItemRepositoryCSV.class);

	private CSVUtils csvUtils = new CSVUtils();

	public ItemRepositoryCSV() {}

	@Override
	public Item create(Item item, Boolean genSN) throws ItemDomainException {
		if(genSN == true) {
			item.setSerialNumber(System.nanoTime());
		} else {
			if(item.getSerialNumber() == null || item.getSerialNumber() <= 0)
				throw new ItemDomainException("Item SN can't be Zero or Null.");
			
			if(this.read(item.getSerialNumber()) != null) throw new ItemDomainException("Item SN: " + item.getSerialNumber() + " already exist.");
		}

		this.save(item);

		return read(item.getSerialNumber());
	}

	@Override
	public Item read(Long sn) {
		for(Item i : this.getItems()) {
			if(i.getSerialNumber().equals(sn)) return i;
		}

		return null;
	}

	@Override
	public List<Item> readAll() {
		return this.getItems();
	}

	@Override
	public void update(Item item) throws ItemDomainException {
		this.delete(item.getSerialNumber());
		this.save(item);
	}

	@Override
	public void delete(Long sn) throws ItemDomainException {
		if(this.read(sn) == null) {
			throw new ItemDomainException("Item SN: " + sn + " does not exist.");
		}

		List<Item> items = this.getItems();

		for(int i = 0; i < items.size(); i++) {
			Item ci = (Item)items.get(i);
			if(ci.getSerialNumber().equals(sn)) {
				items.remove(i);
				save(items);
				return;
			}
		}	
	}

	private List<Item> getItems() {
		Reader reader = null;
		List<Item> items = new LinkedList<Item>();

		try {
			reader = new FileReader(this.getFileName());
			Iterable<CSVRecord> rd = CSVFormat.EXCEL.parse(reader);

			for (CSVRecord r : rd) {
				items.add(csvUtils.csvToItem(r));
			}

			reader.close();
		} catch (Exception e) {
			log.error("There was a problem reading the CSV file.", e);
		} finally {
			try {
				if( reader != null ) reader.close();
			} catch (Exception io) {
				log.error(io.getMessage(), io);
			}
		}

		return items;
	}

	private String getFileName() {
		return new ApplicationConfig().getProperty(ApplicationConfig.PropertyName.CSV_LOCATION);
	}

	private void save(List<Item> list) throws ItemDomainException {
		FileWriter fw = null;
		CSVPrinter printer = null;

		try {
			fw = new FileWriter(this.getFileName());
			printer = new CSVPrinter(fw, CSVFormat.EXCEL);

			printer.printRecords(csvUtils.itemsToList(list));

			fw.close();
			printer.close();

		} catch (Exception e) {
			throw new ItemDomainException("There was a problem creating the Item.", e);

		} finally {
			try {
				if(printer != null) printer.close();
				if(fw != null) fw.close();
			} catch (IOException io) {
				log.error(io.getMessage(), io);
			}

		}
	}

	private void save(Item i) throws ItemDomainException {
		List<Item> l = this.getItems();

		l.add(i);

		this.save(l);
	}
}