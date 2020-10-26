package com.kids.api.certification;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kids.api.kidsaccount.Kids;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/certification")
public class CertificationController {
	static Logger logger = LoggerFactory.getLogger(CertificationController.class);

	@Autowired
	CertificationService cService;

	@PostMapping()
	@ApiOperation(value = "인증번호 요청")
	public ResponseEntity<Map<String, Object>> requestNumber(@RequestBody Kids kid, Parent parent) {
		ResponseEntity<Map<String, Object>> entity = null;
		logger.debug("parentId : " + parent.getParentId() + ", kidId: " + kid.getKidId());
		try {
			// 인증번호 6자리 난수
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			int digit = sr.nextInt(900000) + 100000;
			// 한국시간 + 5분뒤 만료
			LocalDateTime now = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() + 300000),
					ZoneId.of("Asia/Seoul"));

			Certification certification = new Certification(digit+"", parent.getParentId(), kid.getKidId(), now);
			// DB 인증번호 등록
			cService.addCertification(certification);

			entity = handleSuccess(digit);
		} catch (NoSuchAlgorithmException | RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}

	@PostMapping("/login")
	@ApiOperation(value = "아이 인증번호로 로그인 요청")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Certification certification) {
		ResponseEntity<Map<String, Object>> entity = null;
		logger.debug("certificationNo : " + certification.getCertificationNo());
		try {
			Certification temCertification = cService.getCertificationByNo(certification.getCertificationNo());
			System.out.println(temCertification);
			
			entity = handleSuccess(temCertification);
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
