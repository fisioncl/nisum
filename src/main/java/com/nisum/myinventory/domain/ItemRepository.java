package com.nisum.myinventory.domain;

import com.nisum.myinventory.exception.ItemDomainException;
import com.nisum.myinventory.vo.Item;
import java.util.List;

public interface ItemRepository {
	public Item create(Item item, Boolean genSN) throws ItemDomainException;
	public Item read(Long sn);
	public List<Item> readAll();
	public void update(Item item) throws ItemDomainException;
	public void delete(Long sn) throws ItemDomainException;
}