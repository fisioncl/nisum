package com.nisum.myinventory.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nisum.myinventory.domain.ItemRepository;
import com.nisum.myinventory.domain.ItemRepositoryCSV;
import com.nisum.myinventory.util.ApplicationConfig;

public class InventoryItemRepositoryFactory {
	private static Logger log = LogManager.getLogger(InventoryItemRepositoryFactory.class);

	public InventoryItemRepositoryFactory() {}

	public ItemRepository getRepository() {
		String rt = this.getRepositoryType();

		switch (rt) {
			case "DBMS":
				return null;

			case "CSV":
				return new ItemRepositoryCSV();
		}

		log.error("Invalid datasource type: " + rt + " is not a valid option.");
		return null;
	}

	private String getRepositoryType() {
		return new ApplicationConfig().getProperty(ApplicationConfig.PropertyName.DATASOURCE_TYPE);
	}
}
