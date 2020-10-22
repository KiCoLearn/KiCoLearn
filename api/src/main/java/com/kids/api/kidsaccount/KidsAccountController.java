package com.kids.api.kidsaccount;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/kidsaccount")
public class KidsAccountController {
	static Logger logger = LoggerFactory.getLogger(KidsAccountController.class);
	
	@Autowired
	KidsService kService;
	
	@GetMapping("/list/{parentId}")
	@ApiOperation(value = "부모번호로 아이들 정보 조회")
	public ResponseEntity<Map<String, Object>> getKidsList(@PathVariable int parentId) {
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			List<Kids> kids = kService.getKidsByParentId(parentId);
			logger.debug("search kids list by parentId: " + parentId+", kid : "+kids);
			entity = handleSuccess(kids);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	

	@GetMapping("/detail/{kidId}")
	@ApiOperation(value = "아이 정보 조회")
	public ResponseEntity<Map<String, Object>> getKidDetail(@PathVariable int kidId) {
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			Kids kid = kService.detailKid(kidId);
			logger.debug("search kid by kidId: " + kidId+", kid : "+kid);
			entity = handleSuccess(kid);
		} catch (RuntimeException e) {
			entity = handleException(e);
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
			entity = handleSuccess("success");
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@DeleteMapping("/delete/{kidId}")
	@ApiOperation(value = "아이 정보 삭제")
	public ResponseEntity<Map<String, Object>> deleteKid(@PathVariable int kidId){
		logger.debug("delete kid by kidId: " + kidId);
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			kService.deleteKid(kidId);
			entity =handleSuccess("success");
		} catch (RuntimeException e) {
			entity =handleException(e);
		}
		return entity;
	}
	

	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", true);
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap,
				HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleException(Exception e) {
		logger.error("예외 발생 : ", e);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", false);
		resultMap.put("data", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(resultMap,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
