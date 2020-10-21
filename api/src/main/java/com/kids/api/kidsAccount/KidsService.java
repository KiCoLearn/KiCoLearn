package com.kids.api.kidsAccount;

import java.util.List;

public interface KidsService {
    int addKid(Kids kid);
    int deleteKid(int kidId);
    Kids detailKid(int kidId);
    List<Kids> getKidsByParentId(int parentId);
}
