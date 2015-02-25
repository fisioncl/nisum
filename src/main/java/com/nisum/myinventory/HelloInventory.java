package com.nisum.myinventory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

public class HelloInventory {
	public static void main(String[] args) {
		Reader in;

		try {
			in = new FileReader("ohmy.csv");
			Iterable<CSVRecord> rcds = CSVFormat.EXCEL.parse(in);
			
			for (CSVRecord r : rcds) {
				System.out.println(r.isConsistent());
				System.out.println(r.get(1));
			}
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
  }
}
