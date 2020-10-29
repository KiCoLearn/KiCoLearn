package com.kids.api.quest;

import java.util.List;

public interface QuestService {
    int createQuest(Quest quest);

    Quest detailQuest(int questNo);

    List<Quest> getQuestListByParentId(int parentId);

    int updateQuest(int questNo);

    int setKidsQuest(KidsQuest quest);

    int finishKidsQuest(KidsQuest quest);

    int deleteKidQuest(KidsQuest quest);

    List<KidsQuest> getKidQuestListByKidId(int kidId);
}
