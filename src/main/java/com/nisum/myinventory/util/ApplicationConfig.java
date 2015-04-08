package com.nisum.myinventory.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationConfig {
	private static Logger log  = LogManager.getLogger(ApplicationConfig.class);
	private static String FILE_NAME = "app.properties";

	public static enum PropertyName {
		CSV_LOCATION("csv.location"),
		DATASOURCE_TYPE("datasource.type");

		private final String property;

		PropertyName(String property) {
			this.property = property;
		}

		@Override
		public String toString() {
			return this.property;
		}
	}

	public ApplicationConfig() {}

	public String getProperty(PropertyName propertyName) {
		try {
			Properties ap = new Properties();
			ap.load(new FileReader(FILE_NAME));

			return ap.getProperty(propertyName.toString());

		} catch (IOException e) {
			log.error("There was an error traying reading from the property file (" + FILE_NAME + ").", e);
		}

		return null;
	}
}
