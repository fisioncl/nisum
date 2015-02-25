package com.nisum.myinventory.vo;

import java.util.Date;

public class Item {
	private Long SerialNumber;
	private String Description;
	private Date buyDate;
	
	public Item() {}
	
	public Item(Long serialNumber, String description, Date buyDate) {
		super();
		SerialNumber = serialNumber;
		Description = description;
		this.buyDate = buyDate;
	}

	public Long getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(Long serialNumber) {
		SerialNumber = serialNumber;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
}
