package com.kids.api.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreDao sDao;

    @Override
    public int addItem(Item item) {
        return sDao.addItem(item);
    }

    @Override
    public List<Item> getItemList(int parentId) {
        return sDao.getItemList(parentId);
    }

    @Override
    public int updateItem(Item item) {
        return sDao.updateItem(item);
    }

    @Override
    public int deleteItem(int itemNo) {
        return sDao.deleteItem(itemNo);
    }

    @Override
    public Item getItemByItemNo(int itemNo) {
        return sDao.getItemByItemNo(itemNo);
    }

    @Override
    public int addKidItem(KidsStore kidItem) {
        return sDao.addKidItem(kidItem);
    }

    @Override
    public List<Item> getKidsStore(int kidId) {
        return sDao.getKidsStore(kidId);
    }

    @Override
    public int deleteKidItem(KidsStore kidItem) {
        return sDao.deleteKidItem(kidItem);
    }

    @Override
    public int updatePurchaseRequest(PurchaseItem item) {
        return sDao.updatePurchaseRequest(item);
    }

}
