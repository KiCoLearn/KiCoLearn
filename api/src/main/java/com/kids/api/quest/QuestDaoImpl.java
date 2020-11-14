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
    public List<Quest> getKidQuestListByKidId(int kidId) {
        return temp.selectList(ns + "kidQuestList", kidId);
    }

	@Override
	public int deleteQuest(int questNo) {
		// TODO Auto-generated method stub
		return temp.delete(ns+"deleteQuest",questNo);
	}

}
