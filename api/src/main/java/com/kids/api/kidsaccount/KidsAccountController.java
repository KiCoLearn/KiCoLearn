package com.kids.api.kidsaccount;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kids.api.global.handler.Handler;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/kidsaccount")
public class KidsAccountController {
    static Logger logger = LoggerFactory.getLogger(KidsAccountController.class);

    @Autowired
    KidsService kService;

    Handler resultHandler;

    // @ApiImplicitParams({ @ApiImplicitParam(name = "jwt-auth-token", value = "로그인 성공 후 access_token", required = true,
    // dataType = "String", paramType = "header") })
    @GetMapping("/list/{parentId}")
    @ApiOperation(value = "부모번호로 아이들 정보 조회")
    public ResponseEntity<Map<String, Object>> getKidsList(@PathVariable int parentId) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            List<Kids> kids = kService.getKidsByParentId(parentId);
            logger.debug("search kids list by parentId: " + parentId + ", kid : " + kids);
            entity = resultHandler.handleSuccess(kids);
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @GetMapping("/detail/{kidId}")
    @ApiOperation(value = "아이 정보 조회")
    public ResponseEntity<Map<String, Object>> getKidDetail(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            Kids kid = kService.detailKid(kidId);
            logger.debug("search kid by kidId: " + kidId + ", kid : " + kid);
            entity = resultHandler.handleSuccess(kid);
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @PostMapping("/regist")
    @ApiOperation(value = "아이 정보 등록")
    public ResponseEntity<Map<String, Object>> registKid(@RequestBody Kids kid) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("insert kid: " + kid);
        try {
            kService.addKid(kid);
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @DeleteMapping("/delete/{kidId}")
    @ApiOperation(value = "아이 정보 삭제")
    public ResponseEntity<Map<String, Object>> deleteKid(@PathVariable int kidId) {
        logger.debug("delete kid by kidId: " + kidId);
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            kService.deleteKid(kidId);
            entity = resultHandler.handleSuccess("success");
        } catch (RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

}
