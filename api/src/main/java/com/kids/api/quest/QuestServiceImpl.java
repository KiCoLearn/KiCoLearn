package com.kids.api.quest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestServiceImpl implements QuestService {

    @Autowired
    QuestDao qDao;

    @Override
    public int createQuest(Quest quest) {
        return qDao.createQuest(quest);
    }

    @Override
    public Quest detailQuest(int questNo) {
        return qDao.detailQuest(questNo);
    }

    @Override
    public List<Quest> getQuestListByParentId(int parentId) {
        return qDao.getQuestListByParentId(parentId);
    }

    @Override
    public int updateQuest(Quest quest) {
        return qDao.updateQuest(quest);
    }

    @Override
    public int setKidsQuest(KidsQuest quest) {
        return qDao.setKidsQuest(quest);
    }

    @Override
    public int finishKidsQuest(KidsQuest quest) {
        int count = qDao.finishKidsQuest(quest);
        if (count!=1) throw new RuntimeException("퀘스트가 없거나 완료되지 못함.");
        return 1;
    }

    @Override
    public int deleteKidQuest(KidsQuest quest) {
        return qDao.deleteKidQuest(quest);
    }

    @Override
    public List<KidQuestDetail> getKidQuestListByKidId(int kidId) {
        return qDao.getKidQuestListByKidId(kidId);
    }

	@Override
	public int deleteQuest(int questNo) {
		// TODO Auto-generated method stub
		return qDao.deleteQuest(questNo);
	}

}
