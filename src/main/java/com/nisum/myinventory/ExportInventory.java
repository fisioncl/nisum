package com.nisum.myinventory;

import com.nisum.myinventory.service.InventoryServiceCSV;
import com.nisum.myinventory.exception.InventoryServiceException;
import org.apache.commons.cli.*;

import java.io.IOException;


public class ExportInventory {
	public static void main(String[] args) {
        InventoryServiceCSV iServices = new InventoryServiceCSV();

        CommandLineParser parser = new BasicParser();
        Options ops = new Options();

        ops.addOption("file", "destination-file", true, "Destination CSV file");
        ops.addOption("h", "help", false, "Print this help");

		try {
            CommandLine cl = parser.parse(ops, args);

            if(cl.hasOption("file")) {
                iServices.exportInventory(cl.getOptionValue("file"));

                System.out.println("Data exported successfully.");

                return;
            }

            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("exportInventory -file destination/csv/file", ops);

		} catch (ParseException | InventoryServiceException e) {
			e.printStackTrace();
			return;
		}
    }
}
