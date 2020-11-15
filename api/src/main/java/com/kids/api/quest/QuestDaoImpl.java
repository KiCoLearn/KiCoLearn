package com.kids.api.quest;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestDaoImpl implements QuestDao {
    private static String ns = "com.kids.api.mapper.Quest.";

    @Autowired
    SqlSessionTemplate temp;

    @Override
    public int createQuest(Quest quest) {
        return temp.insert(ns + "insert", quest);
    }

    @Override
    public Quest detailQuest(int questNo) {
        return temp.selectOne(ns + "detail", questNo);
    }

    @Override
    public List<Quest> getQuestListByParentId(int parentId) {
        return temp.selectList(ns + "list", parentId);
    }

    @Override
    public int updateQuest(Quest quest) {
        return temp.update(ns + "update", quest);
    }

    @Override
    public int setKidsQuest(KidsQuest quest) {
        return temp.insert(ns + "setQuest", quest);
    }

    @Override
    public int finishKidsQuest(KidsQuest quest) {
        return temp.update(ns + "finishQuest", quest);
    }

    @Override
    public int deleteKidQuest(KidsQuest quest) {
        return temp.delete(ns + "deletekidsQuest", quest);
    }

    @Override
    public List<KidQuestDetail> getKidQuestListByKidId(int kidId) {
        return temp.selectList(ns + "kidQuestList", kidId);
    }

    @Override
    public int deleteQuest(int questNo) {
        return temp.delete(ns + "deleteQuest", questNo);
    }

    @Override
    public int updateRequest(KidsQuest quest) {
        return temp.update(ns + "updateRequest", quest);
    }

    @Override
    public int getFinishCount(int kidId) {
        return temp.selectOne(ns+"finishCount", kidId);
    }

    @Override
    public int getRequestCount(int kidId) {
        return temp.selectOne(ns+"requestCount", kidId);
    }

}
