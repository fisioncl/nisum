package com.nisum.myinventory.vo;

import java.util.Date;

public class Item {
	private Long serialNumber;
	private String description;
	private Date buyDate;
	
	public Item() {}
	
	public Item(Long serialNumber, String description, Date buyDate) {
		super();
		this.serialNumber = serialNumber;
		this.description = description;
		this.buyDate = buyDate;
	}

	public Long getSerialNumber() {return serialNumber;}
	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

    @Override
    public boolean equals(Object o) {
        if(o == null) return Boolean.FALSE;
        if(o.getClass() != this.getClass()) return Boolean.FALSE;

        Item i = (Item)o;

        return serialNumber == i.getSerialNumber()
                && description.equals(i.getDescription())
                && buyDate.compareTo(i.getBuyDate()) == 0;
    }
}
