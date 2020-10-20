package com.kids.api.kidsAccount;

public interface KidsDao {
    int addKid(Kids kid);
    int deleteKid(int kidId);
    Kids detailKid(int kidId);

}
