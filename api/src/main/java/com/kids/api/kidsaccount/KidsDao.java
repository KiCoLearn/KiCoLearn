package com.kids.api.kidsaccount;

import java.util.List;

public interface KidsDao {
    int addKid(Kids kid);
    int deleteKid(int kidId);
    Kids detailKid(int kidId);
    List<Kids> getKidsByParentId(int parentId);

}
