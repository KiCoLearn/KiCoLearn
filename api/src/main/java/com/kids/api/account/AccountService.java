package com.kids.api.account;

import java.util.List;

import com.kids.api.kidsaccount.Kids;

public interface AccountService<T extends User> {
    <T extends User> T login(T request);
    
    <T extends User> int add(T object);

    <T extends User> long delete(T request);

    <T extends User> T detail(int kidId);

    <T extends User> List<T> getKidsByParentId(int parentId);

}
