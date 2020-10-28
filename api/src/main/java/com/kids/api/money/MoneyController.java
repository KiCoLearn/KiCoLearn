package com.kids.api.money;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/money")
public class MoneyController {
    static Logger logger = LoggerFactory.getLogger(MoneyController.class);

    @Autowired
    MoneyService mService;

    @GetMapping("/{kidId}")
    @ApiOperation(value="아이의 잔액 조회")
    public ResponseEntity<Map<String, Object>> getLeftMoney(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            int totalMoney = mService.getTotalMoney(kidId);
            result = handleSuccess(totalMoney);
        } catch (Exception e) {
            result = handleException(e);
        }
        return result;
    }
    
    @PostMapping("/activity")
    @ApiOperation(value="활동을 기록(용돈기입장)")
    public ResponseEntity<Map<String, Object>> activity(@RequestBody Map<String, Object> data) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            String contents = (String) data.get("contents");
            int amount = (int) data.get("amount");
            boolean type = (boolean) data.get("type");
            int kidId = (int) data.get("kidId");
            int leftMoney = mService.getTotalMoney(kidId) - amount;
            Money money = new Money(kidId, amount);
            int totalMoneyUpdate = mService.deposit(money);
            Budget budget = new Budget(contents, amount, null, type, leftMoney, kidId);
            result = handleSuccess(mService.activity(budget));
        } catch (Exception e) {
            result = handleException(e);
        }
        return result;
    }    
    
    @GetMapping("/activity/{kidId}")
    @ApiOperation(value="아이의 활동 기록 가져오기(용돈기입장)")
    public ResponseEntity<Map<String, Object>> activityList(@PathVariable int kidId){
        ResponseEntity<Map<String, Object>> result = null;
        try {
            result = handleSuccess(mService.activityList(kidId));
        } catch (Exception e) {
            result = handleException(e);
        }
        return result;        
    }
    
    @PostMapping("/deposit")
    @ApiOperation(value="입금")
    public ResponseEntity<Map<String, Object>> deposit(@RequestBody Map<String, Object> data) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            int kidId = (int) data.get("kidId");
            int calculation = (int) data.get("calculation");
            Money money = new Money(kidId, calculation);
            result = handleSuccess(mService.deposit(money));
        } catch (Exception e) {
            result = handleException(e);
        }
        return result;
    }

    @PostMapping("/withdraw")
    @ApiOperation(value="출금")
    public ResponseEntity<Map<String, Object>> withdraw(@RequestBody Map<String, Object> data) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            int kidId = (int) data.get("kidId");
            int calculation = (int) data.get("calculation");
            Money money = new Money(kidId, calculation);
            result = handleSuccess(mService.withdraw(money));
        } catch (Exception e) {
            result = handleException(e);
        }
        return result;
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
