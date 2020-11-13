package com.kids.api.kidsaccount;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KidsDaoImpl implements KidsDao {

    private static String ns = "com.kids.api.mapper.Kids.";

    @Autowired
    SqlSessionTemplate temp;

    @Override
    public int addKid(Kids kid) {
        return temp.insert(ns + "insert", kid);
    }

    @Override
    public int deleteKid(int kidId) {
        return temp.delete(ns + "delete", kidId);
    }

    @Override
    public Kids detailKid(int kidId) {
        return temp.selectOne(ns + "detail", kidId);
    }

    @Override
    public List<Kids> getKidsByParentId(int parentId) {
        return temp.selectList(ns + "list", parentId);
    }

    @Override
    public int updateKid(Kids kid) {
        return temp.update(ns+"update", kid);
    }

    @Override
    public Kids getParentsByKidId(int kidId) {
        // TODO Auto-generated method stub
        return temp.selectOne(ns + "getParentsByKidId", kidId);
    }

}
