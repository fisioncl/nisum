package com.nisum.myinventory.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nisum.myinventory.vo.Item;

public interface ItemMapper {
	@Insert("INSERT into item(serial_number, description, buy_date) " +
			"VALUES (#{serialNumber}, #{description}, #{buyDate})")
	void insert(Item item);
	
	@Update("UPDATE item SET description = #{description} and buy_date = #{buyDate} WHERE serial_number = #{serialNumber}")
	void update(Item item);

	@Select("SELECT serial_number serialNumber, description, buy_date buyDate FROM item")
	List<Item> selectAll();

	@Select("SELECT * FROM item WHERE serial_number = #{serialNumber}")
	Item selectItemById(Long serialNumber);
	
	@Delete("DELETE FROM item WHERE serial_number = #{serialNumber}")
	void deleteById(Long serialNumber);
}
