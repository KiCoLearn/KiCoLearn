package com.kids.api.certification;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kids.api.certification.exception.BasicCertificationException;
import com.kids.api.certification.exception.ExpireTimeException;
import com.kids.api.certification.exception.CodeNotFoundException;
import com.kids.api.global.handler.Handler;
import com.kids.api.jwt.JwtService;
import com.kids.api.kidsaccount.Kids;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/certification")
public class CertificationController {
    static Logger logger = LoggerFactory.getLogger(CertificationController.class);

    @Autowired
    CertificationService cService;

    @Autowired
    JwtService jwtService;

    @Autowired
    Handler resultHandler;

    @PostMapping()
    @ApiOperation(value = "인증번호 요청")
    public ResponseEntity<Map<String, Object>> requestNumber(@RequestBody Kids kid) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("parentId : " + kid.getParentId() + ", kidId: " + kid.getKidId());
        try {
            // 인증번호 6자리 난수
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            int digit = sr.nextInt(900000) + 100000;
            // 한국시간 + 5분뒤 만료
            // LocalDateTime now =
            // LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() +
            // 300000),ZoneId.of("Asia/Seoul"));
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).plusMinutes(5);
            System.out.println(now);

            Certification certification = new Certification(digit + "", kid.getParentId(), kid.getKidId(),
                                                            Timestamp.valueOf(now.toLocalDateTime()));
            // DB 인증번호 등록
       
            if(cService.isDuplicate(kid.getKidId())) {
                cService.updateCertification(certification);
            }else {
                cService.addCertification(certification);
            }
            
            
            entity = resultHandler.handleSuccess(digit);
        } catch (NoSuchAlgorithmException | RuntimeException e) {
            entity = resultHandler.handleException(e);
        }
        return entity;
    }

    @PostMapping("/login")
    @ApiOperation(value = "아이 인증번호로 로그인 요청")
    public ResponseEntity<Map<String, Object>> login(HttpServletResponse response, @RequestBody Certification certification) {
        ResponseEntity<Map<String, Object>> entity = null;
        logger.debug("certificationNo : " + certification.getCertificationNo());

        Certification temCertification = cService.getCertificationByNo(certification.getCertificationNo());
       
        if (temCertification == null) {
            throw new CodeNotFoundException("일치하는 데이터가 조회되지 않습니다.");
        } else {
            if (System.currentTimeMillis() > temCertification.getExpiredTime().getTime()) {
                throw new ExpireTimeException("인증코드 시간이 만료되었습니다.");
            } else {
                StringBuffer temp = new StringBuffer();
                Random rnd = new Random();

                // 20자리 난수,문자열 조합
                for (int i = 0; i < 20; i++) {
                    int rIndex = rnd.nextInt(3);
                    switch (rIndex) {
                    case 0:
                        // a-z
                        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                        break;
                    case 1:
                        // A-Z
                        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                        break;
                    case 2:
                        // 0-9
                        temp.append((rnd.nextInt(10)));
                        break;
                    }
                }

                KidsAuth auth = new KidsAuth(temCertification.getKidId(), temp.toString());
                
                if(cService.isDuplicateKid(temCertification.getKidId())) {
                    cService.deleteKidAuth(temCertification.getKidId());
                }
                cService.createKidsAuth(auth);
                String token = jwtService.create(auth);
                response.setHeader("Authorization", token);
                System.out.println(token);
                response.setHeader("provider", "kicolearn");
                entity = resultHandler.handleSuccess(auth);
            }
        }

        return entity;
    }

}
