package com.nisum.myinventory;

import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.nisum.myinventory.persistence.mapper.ItemMapper;
import com.nisum.myinventory.vo.Item;

@ComponentScan
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) {
		/*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();

		ItemMapper itemMapper = ctx.getBean(ItemMapper.class);

		List<Item> items = itemMapper.selectAll();

		for (Item item : items) {
			System.out.println(item.toString());
		}

		//itemMapper.insert(new Item(23L, "NAnanananna", new Date()));

		System.out.println(items.size() + " :)");*/
		SpringApplication.run(Application.class, args);
	}
}
