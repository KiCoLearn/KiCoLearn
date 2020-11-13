package com.kids.api.money;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MoneyDaoImpl implements MoneyDao {

    private static String ns = "com.kids.api.mapper.money.";

    @Autowired
    SqlSessionTemplate temp;

    @Override
    public int getTotalMoney(int kidsId) {
        return temp.selectOne(ns + "totalMoney", kidsId);
    }

    @Override
    public int moneyDeposit(Money money) {
        return temp.update(ns + "deposit", money);
    }

    @Override
    public int moneyWithdraw(Money money) {
        return temp.update(ns + "withdraw", money);
    }

    @Override
    public int budgetActivity(Budget budget) {
        return temp.insert(ns + "activity", budget);
    }

    @Override
    public List<Budget> kidActivityList(int kidId) {
        return temp.selectList(ns + "activityList", kidId);
    }

    @Override
    public int getTodaySpend(int kidId) {
        if (temp.selectOne(ns + "todaySpend", kidId) == null)
            return 0;
        return temp.selectOne(ns + "todaySpend", kidId);
    }

    @Override
    public int getWeekSpend(int kidId) {
        if (temp.selectOne(ns + "weekSpend", kidId) == null)
            return 0;
        return temp.selectOne(ns + "weekSpend", kidId);
    }

    @Override
    public List<Budget> todayDepositContents(int kidsId) {
        return temp.selectList(ns + "todayDepositContents", kidsId);
    }

    @Override
    public List<Budget> weekDepositContents(int kidsId) {
        return temp.selectList(ns + "weekDepositContents", kidsId);
    }

    @Override
    public int getTodayDeposit(int kidId) {
        if (temp.selectOne(ns + "todayDeposit", kidId) == null)
            return 0;
        return temp.selectOne(ns + "todayDeposit", kidId);
    }

    @Override
    public int getWeekDeposit(int kidId) {
        if (temp.selectOne(ns + "weekDeposit", kidId) == null)
            return 0;
        return temp.selectOne(ns + "weekDeposit", kidId);
    }

    @Override
    public List<Budget> todaySpendContents(int kidsId) {
        return temp.selectList(ns + "todaySpendContents", kidsId);
    }

    @Override
    public List<Budget> weekSpendContents(int kidsId) {
        return temp.selectList(ns + "weekSpendContents", kidsId);
    }

}
