package com.kids.api.store;

import java.util.List;

public interface StoreDao {
    int addItem(Item item);

    List<Item> getItemList(int parentId);
    
    int updateItem(Item item);

    int deleteItem(int itemNo);

    Item getItemByItemNo(int itemNo);
    
    int addKidItem(KidsStore kidItem);
    
    List<Item> getKidsStore(int kidId);
}
