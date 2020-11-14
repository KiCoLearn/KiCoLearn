package com.kids.api.quest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kids.api.global.handler.Handler;
import com.kids.api.notification.NotificationService;
import com.kids.api.store.Item;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/quest")
public class QuestController {
    static Logger logger = LoggerFactory.getLogger(QuestController.class);

    @Autowired
    QuestService qService;
    
    @Autowired
    NotificationService notificationService;

    @Autowired
    Handler resultHandler;

    @GetMapping("/list/{parentId}")
    @ApiOperation(value = "부모 번호로 퀘스트 리스트 조회")
    public ResponseEntity<Map<String, Object>> getQuestList(@PathVariable int parentId) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            List<Quest> quests = qService.getQuestListByParentId(parentId);
            logger.debug("search quest list by parentId: " + parentId + ", kid : " + quests);
            entity = resultHandler.handleSuccess(quests);
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @GetMapping("/detail/{questNo}")
    @ApiOperation(value = "퀘스트 키로 퀘스트 정보 조회")
    public ResponseEntity<Map<String, Object>> getQuestDetail(@PathVariable int questNo) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            Quest quest = qService.detailQuest(questNo);
            logger.debug("search quest by questNo: " + questNo + ", kid : " + quest);
            entity = resultHandler.handleSuccess(quest);
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @PostMapping("/parent/regist")
    @ApiOperation(value = "부모가 퀘스트 등록")
    public ResponseEntity<Map<String, Object>> registQuest(@RequestBody Quest quest) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("insert quest: " + quest);
        try {
            qService.createQuest(quest);
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @PutMapping("/parent/update")
    @ApiOperation(value = "부모가 퀘스트 수정")
    public ResponseEntity<Map<String, Object>> updateQuest(@RequestBody Quest quest) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("update quest: " + quest);
        try {
            qService.updateQuest(quest);
            System.out.println(quest);
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }
  
    
    @DeleteMapping("/parent/delete/{questNo}")
    @ApiOperation(value = "부모가 퀘스트 삭제")
    public ResponseEntity<Map<String, Object>> deleteQuest(@PathVariable int questNo) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("delete quest: " + questNo);
        try {
        	qService.deleteQuest(questNo);
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }
    

    @GetMapping("/kid/list/{kidId}")
    @ApiOperation(value = "아이 번호로 퀘스트 리스트 조회")
    public ResponseEntity<Map<String, Object>> getKidsQuestListByKidId(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            List<KidQuestDetail> list = qService.getKidQuestListByKidId(kidId);
            logger.info("kid_quest: " + list);
            entity = resultHandler.handleSuccess(list);
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @PostMapping("/kid/regist")
    @ApiOperation(value = "아이 퀘스트 등록")
    public ResponseEntity<Map<String, Object>> setQuest(@RequestBody KidsQuest kidQuest) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("set kid_quest: " + kidQuest);
        try {
            qService.setKidsQuest(kidQuest);
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @PutMapping("/kid/finish")
    @ApiOperation(value = "아이 퀘스트 완료")
    public ResponseEntity<Map<String, Object>> finishQuest(@RequestBody KidsQuest kidQuest) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.info("finish kid_quest: " + kidQuest);
        try {
            qService.finishKidsQuest(kidQuest);
            int kidId = kidQuest.getKidId();
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }
    
    @PostMapping("/{id}/request")
    @ApiOperation(value = "아이 퀘스트 완료")
    public ResponseEntity<Object> finishQuest(@PathVariable int id) {
        try {
            notificationService.completeQuestFromKidId(id);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/kid/delete/{questNo}/{kidId}")
    @ApiOperation(value = "부모가 아이 퀘스트 삭제")
    public ResponseEntity<Map<String, Object>> deleteKidQuest(@PathVariable int questNo, @PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("delete kid questNo: " + questNo + ", kidId: " + kidId);
        try {
            KidsQuest kidQuest = new KidsQuest();
            kidQuest.setQuestNo(questNo);
            kidQuest.setKidId(kidId);
            qService.deleteKidQuest(kidQuest);
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

}
