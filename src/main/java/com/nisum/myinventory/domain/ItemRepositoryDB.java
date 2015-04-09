package com.nisum.myinventory.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nisum.myinventory.exception.ItemDomainException;
import com.nisum.myinventory.persistence.mapper.ItemMapper;
import com.nisum.myinventory.vo.Item;

@Component
public class ItemRepositoryDB implements ItemRepository {
	@Autowired
	ItemMapper mapper;
	
	public Item create(Item item, Boolean genSN) throws ItemDomainException {
		if(genSN) item.setSerialNumber(System.nanoTime());

		mapper.insert(item);

		return this.read(item.getSerialNumber());
	}
	
	public Item read(Long sn){
		return mapper.selectItemById(sn);
	}
	
	public List<Item> readAll() {
		return mapper.selectAll();
	}

	public void update(Item item) throws ItemDomainException {
		mapper.update(item);
	}

	public void delete(Long sn) throws ItemDomainException {
		mapper.deleteById(sn);		
	}
}
