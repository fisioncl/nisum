package com.nisum.myinventory.service;

import java.util.List;
import java.util.Date;

public class InventoryItemFilter {
	private List<Long> serialNumbers;
	private String description;
	private Boolean descriptionLike;
	private Date from;
	private Date to;
	
	public List<Long> getSerialNumbers() {
		return serialNumbers;
	}
	public void setSerialNumbers(List<Long> serialNumbers) {
		this.serialNumbers = serialNumbers;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getDescriptionLike() {
		return descriptionLike;
	}
	public void setDescriptionLike(Boolean descriptionLike) {
		this.descriptionLike = descriptionLike;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}

	public Boolean isEmpty() {
		if(this.serialNumbers != null) return false;
		if(this.descriptionLike != null) {
			if(!this.descriptionLike.equals("")) return false;
		}
		if(this.from != null) return false;
		if(this.to != null) return false;

		return true;
	}
}
