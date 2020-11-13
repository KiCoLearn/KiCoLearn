package com.kids.api.kidsaccount;

import java.util.List;

public interface KidsService {
    int addKid(Kids kid);

    int deleteKid(int kidId);

    Kids detailKid(int kidId);

    List<Kids> getKidsByParentId(int parentId);
    
    int updateKid(Kids kid);

    int updateProfileKid(Kids kid);
}
