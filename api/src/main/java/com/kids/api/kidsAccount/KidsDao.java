package com.kids.api.kidsAccount;

import java.util.List;

public interface KidsDao {
    int addKid(Kids kid);
    int deleteKid(int kidId);
    Kids detailKid(int kidId);
    List<Kids> getKidsByParentId(int parentId);

}
