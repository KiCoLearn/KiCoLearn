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
        // TODO Auto-generated method stub
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
    public List<Budget> todayDeposit(int kidsId) {
        return temp.selectList(ns + "todayDeposit", kidsId);
    }

    @Override
    public List<Budget> weekDeposit(int kidsId) {
        return temp.selectList(ns + "weekDeposit", kidsId);
    }

}
