package com.kids.api.account.parents;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParentsRepository {
    List<Parents> getParentsByUID(Parents parents);

    int insert(Parents parents);
    
    int unlink(Parents parents);

    int enable(Parents parents);

    int updateToken(Parents parents);
}
