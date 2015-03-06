package com.nisum.myinventory.domain;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.nisum.myinventory.exception.StorageCSVItemParseException;
import com.nisum.myinventory.exception.InventoryItemNotFoundException;
import com.nisum.myinventory.vo.Item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class InventoryCSV implements Inventory{
	private static final Logger log = LogManager.getLogger(Inventory.class);
    private String dateFormat = "yyyyMMDD";

	/**
	* Convert a List of Items into a CSV format.
	*
	* @param  items	List of items to be converted
	* @param  out	An Appendable object where the result will be written to.
	* @return      	The number of elements (Item) exported.
	*/
	public Long toCSV(List<Item> items, Appendable out) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		CSVPrinter p = null;
		Long lw = 0L;

		try {
			p = new CSVPrinter(out, CSVFormat.EXCEL);

			for (Item i : items) {
				p.print(i.getSerialNumber());
				p.print(i.getDescription());
				p.print(sdf.format(i.getBuyDate()));
				p.println();
				lw++;
			}

			p.close();

		} catch (IOException e) {
			log.error(e.getMessage());

		} finally {
			try {
				if(p != null) p.close();
			} catch(IOException e) {
                log.error(e.getMessage());
            }
		}

		return lw;
	}

	/**
	* Return a item residing in a CSV formatted Readable object.
	*
	* @param	sn  	Serial number of the Item.
	* @param	data	Readable object with CSV format.
	* @return	Resulting Item, if not found Throws InventoryItemNotFound Exception.
	*/
	public Item get(Long sn, Reader data) throws InventoryItemNotFoundException {
		Item item = null;

        try {
            Iterable<CSVRecord> rd = CSVFormat.EXCEL.parse(data);

            for (CSVRecord r : rd) {
                if(Long.parseLong(r.get(0)) == sn) {
                    item = this.parseRecord2Item(r);
                }
            }
        } catch (IOException | StorageCSVItemParseException  e) {
            throw new InventoryItemNotFoundException(e);
        }

		if (item != null) {
			return item;
		} else {
			throw new InventoryItemNotFoundException("Item with the SN: " + sn + " was not found");
		}
	}

	private Item parseRecord2Item(CSVRecord r) throws StorageCSVItemParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Item item = new Item();

		try {
			item.setSerialNumber(Long.parseLong(r.get(0)));
			item.setDescription(r.get(1));
			item.setBuyDate(sdf.parse(r.get(2)));
		} catch (ParseException e) {
			throw new StorageCSVItemParseException(e);
		}

		return item;
	}
}