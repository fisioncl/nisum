package com.nisum.myinventory.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nisum.myinventory.exception.InventoryItemServiceException;
import com.nisum.myinventory.service.InventoryItemService;
import com.nisum.myinventory.vo.Item;

@Controller
@RequestMapping("/")
public class ItemController {
	@Autowired
	InventoryItemService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String home(Map<String, Object> model) {
		model.put("items", service.listAllItems());

		return "home";
	}

	@RequestMapping(value="/item/{serialNumber}", method=RequestMethod.GET)
	public @ResponseBody Item getItem(@PathVariable Long serialNumber) {
		return service.getItemBySN(serialNumber);
	}
	
	@RequestMapping(value="/item/delete/{serialNumber}", method=RequestMethod.GET)
	public String deleteItem(@PathVariable Long serialNumber) throws InventoryItemServiceException {
		service.deleteItemBySN(serialNumber);
		
		return "home";
	}

	@RequestMapping(value="/item/new", method=RequestMethod.POST)
	public String newItem(Item item) throws InventoryItemServiceException {
		service.createItem(item, true);
		
		return "home";
	}

	@RequestMapping(value="/item/edit", method=RequestMethod.POST)
	public String editItem(Item item) throws InventoryItemServiceException {
		service.updateItem(item);

		return "home";
	}
}
