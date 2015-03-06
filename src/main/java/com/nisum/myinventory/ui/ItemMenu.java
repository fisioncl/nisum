package com.nisum.myinventory.ui;

import com.nisum.myinventory.exception.UIException;
import com.nisum.myinventory.vo.Item;

public interface ItemMenu {
    public void showMenu();
    public void showItem(Long sn) throws UIException;
    public void showAllItems();
    public Item createItem(Item it) throws UIException;
    public Item updateItem(Long sn, Item i) throws UIException;
    public void deleteItem(Long sn) throws UIException;
}
