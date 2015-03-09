package com.nisum.myinventory.service;

import com.nisum.myinventory.domain.InventoryCSV;
import com.nisum.myinventory.exception.InventoryServiceException;
import com.nisum.myinventory.vo.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cristian on 03-03-15.
 */
public class InventoryServiceCSV {
    private List<Item> items = new ArrayList<>();

    public InventoryServiceCSV() {
        items.add(new Item(21L, "Dos y Uno", new Date()));
        items.add(new Item(34L, "Tres y Cuatro", new Date()));
        items.add(new Item(46L, "Cuatro y Seis", new Date()));
        items.add(new Item(11L, "Uno y Uno", new Date()));
        items.add(new Item(76L, "Siete y Seis", new Date()));
        items.add(new Item(65L, "Seis y Cinco", new Date()));
    }

    public Boolean exportInventory(String destination) throws InventoryServiceException {
        FileWriter os;

        try {
            os = new FileWriter(destination);
            InventoryCSV i = new InventoryCSV();
            i.toCSV(items, os);

            os.close();

            return true;

        } catch (IOException ex) {
            throw new InventoryServiceException("Can't write to the destination file.", ex);
        }
    }
}
