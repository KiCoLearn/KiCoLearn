package com.kids.api.quest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/quest")
public class QuestController {
    static Logger logger = LoggerFactory.getLogger(QuestController.class);

    @Autowired
    QuestService qService;

    @GetMapping("/list/{parentId}")
    @ApiOperation(value = "부모 번호로 퀘스트 리스트 조회")
    public ResponseEntity<Map<String, Object>> getQuestList(@PathVariable int parentId) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            List<Quest> quests = qService.getQuestListByParentId(parentId);
            logger.debug("search quest list by parentId: " + parentId + ", kid : " + quests);
            entity = handleSuccess(quests);
        } catch (RuntimeException e) {
            entity = handleException(e);
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
            entity = handleSuccess(quest);
        } catch (RuntimeException e) {
            entity = handleException(e);
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
            entity = handleSuccess("success");
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @PutMapping("/parent/delete")
    @ApiOperation(value = "부모가 퀘스트 삭제")
    public ResponseEntity<Map<String, Object>> deleteQuest(@RequestBody Quest quest) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("update quest: " + quest);
        try {
            qService.updateQuest(quest.getQusestNo());
            entity = handleSuccess("success");
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @GetMapping("/kid/list/{kidId}")
    @ApiOperation(value = "아이 번호로 퀘스트 리스트 조회")
    public ResponseEntity<Map<String, Object>> getKidsQuestListByKidId(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            List<KidsQuest> list = qService.getKidQuestListByKidId(kidId);
            logger.debug("kid_quest: " + list);
            entity = handleSuccess(list);
        } catch (RuntimeException e) {
            entity = handleException(e);
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
            entity = handleSuccess("success");
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    @PutMapping("/kid/finish")
    @ApiOperation(value = "아이 퀘스트 완료")
    public ResponseEntity<Map<String, Object>> finishQuest(@RequestBody KidsQuest kidQuest) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("finish kid_quest: " + kidQuest);
        try {
            qService.finishKidsQuest(kidQuest);
            entity = handleSuccess("success");
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
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
            entity = handleSuccess("success");
        } catch (RuntimeException e) {
            entity = handleException(e);
        }
        return entity;
    }

    private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", true);
        resultMap.put("data", data);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> handleException(Exception e) {
        logger.error("예외 발생 : ", e);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", false);
        resultMap.put("data", e.getMessage());
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
