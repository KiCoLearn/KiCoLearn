package com.kids.api.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StoreDaoImpl implements StoreDao {
    private static String ns = "com.kids.api.mapper.Store.";

    @Autowired
    SqlSessionTemplate temp;

    @Override
    public int addItem(Item item) {
        return temp.insert(ns + "insertItem", item);
    }

    @Override
    public List<Item> getItemList(int parentId) {
        return temp.selectList(ns + "parentItem", parentId);
    }

    @Override
    public int updateItem(Item item) {
        return temp.update(ns + "updateItem", item);
    }

    @Override
    public int deleteItem(int itemNo) {
        return temp.delete(ns + "deleteItem", itemNo);
    }

    @Override
    public Item getItemByItemNo(int itemNo) {
        return temp.selectOne(ns + "detailItem", itemNo);
    }

    @Override
    public int addKidItem(KidsStore kidItem) {
        return temp.insert(ns+"addKidItem", kidItem);
    }

    @Override
    public List<Item> getKidsStore(int kidId) {
        return temp.selectList(ns+"kidsStore", kidId);
    }

}
