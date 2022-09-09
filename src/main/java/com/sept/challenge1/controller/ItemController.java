package com.sept.challenge1.controller;

import com.sept.challenge1.exception.CustomExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.sept.challenge1.dao.ItemDao;
import com.sept.challenge1.model.Item;
import com.sept.challenge1.model.Items;

@RestController
@RequestMapping(path = "/item")
public class ItemController{

    @Autowired
    private ItemDao itemDao;

    @GetMapping(path="/", produces = "application/json")
    public Items getItems()
    {
        return itemDao.getAllItems();
    }

    @GetMapping(path="/item{id}", produces = "application/json")

    public Item getItem(@RequestParam @PathVariable int id)
    {
        return itemDao.getItem(id);
    }

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public Object createItem(@RequestBody Item item) throws Exception {

        if (itemDao.getItem(item.getId())==null) {
            itemDao.addItem(item);
            return item;
        }
        else {
            return new CustomExceptionHandler().itemAlreadyExists("Item already exists!");

        }
    }

    @DeleteMapping(path= "/", consumes = "application/json", produces = "application/json")
    public Object deleteItem(@RequestBody Item item) throws Exception {

        if (!itemDao.deleteItem(item)) {
            return new CustomExceptionHandler().itemAlreadyExists("Item cannot delete!");
        }else{
            return "Item successfully deleted\n";
        }
    }

    @PutMapping(path= "/", consumes = "application/json", produces = "application/json")
    public Object updateItem(@RequestBody Item item) throws Exception {

        if (!itemDao.updateItem(item)) {
            return new CustomExceptionHandler().itemAlreadyExists("Item cannot update!");
        }else{
            return "Item successfully updated\n";
        }
    }

}
