package com.kids.api.money;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kids.api.global.handler.Handler;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/money")
public class MoneyController {
    static Logger logger = LoggerFactory.getLogger(MoneyController.class);

    @Autowired
    MoneyService mService;

    @Autowired
    Handler resultHandler;

    @GetMapping("/{kidId}")
    @ApiOperation(value = "아이의 잔액 조회")
    public ResponseEntity<Map<String, Object>> getLeftMoney(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            int totalMoney = mService.getTotalMoney(kidId);
            result = resultHandler.handleSuccess(totalMoney);
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }

    @PostMapping("/activity")
    @ApiOperation(value = "활동을 기록(용돈기입장)")
    public ResponseEntity<Map<String, Object>> activity(@RequestBody Budget budget) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            String contents = budget.getContents();
            int amount = budget.getAmount();
            Boolean type = budget.getIsDeposit();
            int kidId = budget.getKidId();
            Money money = new Money(kidId, amount);
            int leftMoney = mService.getTotalMoney(kidId);

            if (!type) {
                leftMoney -= amount;
                mService.withdraw(money);
            } else {
                leftMoney += amount;
                mService.deposit(money);
            }

            Budget budget2 = new Budget(contents, amount, null, type, leftMoney, kidId);
            mService.activity(budget2);

            result = resultHandler.handleSuccess(budget2);
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }

    @GetMapping("/activity/{kidId}")
    @ApiOperation(value = "아이의 활동 기록 조회(용돈기입장)")
    public ResponseEntity<Map<String, Object>> activityList(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            result = resultHandler.handleSuccess(mService.activityList(kidId));
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }

    @GetMapping("/spend/total/{kidId}")
    @ApiOperation(value = "아이의 이번주 소비 조회")
    public ResponseEntity<Map<String, Object>> weekSpendList(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            Map<String, Integer> data = new HashMap<>();
            data.put("week", mService.getWeekSpend(kidId));
            data.put("today", mService.getTodaySpend(kidId));

            result = resultHandler.handleSuccess(data);
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }
    
    @GetMapping("/deposit/total/{kidId}")
    @ApiOperation(value = "아이의 이번주 수입 조회")
    public ResponseEntity<Map<String, Object>> weekDepositList(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            Map<String, Integer> data = new HashMap<>();
            data.put("week", mService.getWeekDeposit(kidId));
            data.put("today", mService.getTodayDeposit(kidId));

            result = resultHandler.handleSuccess(data);
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }

    @PostMapping("/deposit")
    @ApiOperation(value = "입금")
    public ResponseEntity<Map<String, Object>> deposit(@RequestBody Money money) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            result = resultHandler.handleSuccess(mService.deposit(money));
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }

    @PostMapping("/withdraw")
    @ApiOperation(value = "출금")
    public ResponseEntity<Map<String, Object>> withdraw(@RequestBody Money money) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            result = resultHandler.handleSuccess(mService.withdraw(money));
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }

    @GetMapping("/deposit/today/{kidId}")
    @ApiOperation(value = "아이의 오늘 입금통계")
    public ResponseEntity<Map<String, Object>> todayDeposit(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            result = resultHandler.handleSuccess(mService.todayDepositContents(kidId));
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }
    
    @GetMapping("/deposit/week/{kidId}")
    @ApiOperation(value = "아이의 이번주 입금통계")
    public ResponseEntity<Map<String, Object>> weekDeposit(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            result = resultHandler.handleSuccess(mService.weekDepositContents(kidId));
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }
    
    @GetMapping("/spend/today/{kidId}")
    @ApiOperation(value = "아이의 오늘 소비통계")
    public ResponseEntity<Map<String, Object>> todaySpend(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            result = resultHandler.handleSuccess(mService.todaySpendContents(kidId));
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }
    
    @GetMapping("/spend/week/{kidId}")
    @ApiOperation(value = "아이의 이번주 소비통계")
    public ResponseEntity<Map<String, Object>> weekSpend(@PathVariable int kidId) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            result = resultHandler.handleSuccess(mService.weekSpendContents(kidId));
        } catch (Exception e) {
            result = resultHandler.handleException(e);
        }
        return result;
    }
}
