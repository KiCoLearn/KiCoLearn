package com.kids.api.kidsAccount;

public interface KidsService {
    int addKid(Kids kid);
    int deleteKid(int kidId);
    Kids detailKid(int kidId);
}
