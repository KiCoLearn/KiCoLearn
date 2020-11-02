package com.kids.api.store;

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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/store")
public class StoreController {
    static Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Autowired
    StoreService sService;

    Handler resultHandler;

    @GetMapping("/list/{parentId}")
    @ApiOperation(value = "부모 번호로 아이템 리스트 조회")
    public ResponseEntity<Map<String, Object>> getStore(@PathVariable int parentId) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            List<Item> store = sService.getItemList(parentId);
            logger.debug("search store items by parentId: " + parentId + ", store : " + store);
            entity = resultHandler.handleSuccess(store);
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @GetMapping("/detail/{itemNo}")
    @ApiOperation(value = "아이템 키로 아이템 정보 조회")
    public ResponseEntity<Map<String, Object>> getItemDetail(@PathVariable int itemNo) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            Item item = sService.getItemByItemNo(itemNo);
            logger.debug("search item by itemNo: " + itemNo + ", item : " + item);
            entity = resultHandler.handleSuccess(item);
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @PostMapping("/item/regist")
    @ApiOperation(value = "아이템 등록")
    public ResponseEntity<Map<String, Object>> addItem(@RequestBody Item item) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("insert item: " + item);
        try {
            sService.addItem(item);
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @DeleteMapping("/item/delete/{itemNo}")
    @ApiOperation(value = "아이템 삭제")
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable int itemNo) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("delete item: " + itemNo);
        try {
            sService.deleteItem(itemNo);
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @PutMapping("/item/update")
    @ApiOperation(value = "아이템 수정")
    public ResponseEntity<Map<String, Object>> updateItem(@RequestBody Item item) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("update item: " + item);
        try {
            sService.updateItem(item);
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @GetMapping("/list/{kidId}")
    @ApiOperation(value = "아이 번호로 아이템 리스트 조회")
    public ResponseEntity<Map<String, Object>> getKidsStore(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            List<Item> store = sService.getKidsStore(kidId);
            logger.debug("search store items by kidId: " + kidId + ", store : " + store);
            entity = resultHandler.handleSuccess(store);
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

}