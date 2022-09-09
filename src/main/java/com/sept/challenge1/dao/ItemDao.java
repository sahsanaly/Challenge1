package com.sept.challenge1.dao;

import com.sept.challenge1.model.Item;
import org.springframework.stereotype.Repository;

import com.sept.challenge1.model.Items;

@Repository
public class ItemDao
{
    private static Items list = new Items();

    static
    {
        list.getItemList().add(new Item(1, "Lokesh", "Gupta", 10));
        list.getItemList().add(new Item(2, "Alex", "Kolenchiskey", 20));
        list.getItemList().add(new Item(3, "David", "Kameron", 30));
    }

    public Items getAllItems()
    {
        return list;
    }

    public Item getItem(int id)
    {
        for (int i=0; i<list.getItemList().size(); i++){
            if(list.getItemList().get(i).getId()==id){
                return list.getItemList().get(i);
            }
        }
        return null;
    }

    public void addItem(Item item) {
        list.getItemList().add(item);
    }

    public boolean deleteItem(Item item) {
        for (int i=0; i<list.getItemList().size(); i++){
            if(list.getItemList().get(i).getId()==item.getId()){
                list.getItemList().remove(i);
                return true;
            }
        }
        return false;

    }

    public boolean updateItem(Item item) {
        for (int i=0; i<list.getItemList().size(); i++){
            if(list.getItemList().get(i).getId()==item.getId()){
                if(item.getName()!=null){
                    list.getItemList().get(i).setName(item.getName());
                }
                if(item.getDesc()!=null){
                    list.getItemList().get(i).setDesc(item.getDesc());
                }
                if(item.getPrice()>=0){
                    list.getItemList().get(i).setPrice(item.getPrice());
                }
                return true;
            }
        }
        return false;
    }
}