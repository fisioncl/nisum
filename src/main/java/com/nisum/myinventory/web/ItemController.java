package com.nisum.myinventory.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nisum.myinventory.service.InventoryItemService;

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
}
