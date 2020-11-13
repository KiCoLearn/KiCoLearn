package com.kids.api.money;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyServiceImpl implements MoneyService {

    @Autowired
    MoneyDao mDao;

    @Override
    public int getTotalMoney(int kidId) {
        return mDao.getTotalMoney(kidId);
    }

    @Override
    public int deposit(Money money) {
        return mDao.moneyDeposit(money);
    }

    @Override
    public int withdraw(Money money) {
        return mDao.moneyWithdraw(money);
    }

    @Override
    public int activity(Budget budget) {
        return mDao.budgetActivity(budget);
    }

    @Override
    public List<Budget> activityList(int kidId) {
        return mDao.kidActivityList(kidId);
    }

    @Override
    public int getTodaySpend(int kidId) {
        return mDao.getTodaySpend(kidId);
    }

    @Override
    public int getWeekSpend(int kidId) {
        return mDao.getWeekSpend(kidId);
    }

    @Override
    public int getTodayDeposit(int kidId) {
        return mDao.getTodayDeposit(kidId);
    }

    @Override
    public int getWeekDeposit(int kidId) {
        return mDao.getWeekDeposit(kidId);
    }

    @Override
    public List<Budget> todaySpendContents(int kidsId) {
        return mDao.todaySpendContents(kidsId);
    }

    @Override
    public List<Budget> weekSpendContents(int kidsId) {
        return mDao.weekSpendContents(kidsId);
    }

    @Override
    public List<Budget> todayDepositContents(int kidsId) {
        return mDao.todayDepositContents(kidsId);
    }

    @Override
    public List<Budget> weekDepositContents(int kidsId) {
        return mDao.weekDepositContents(kidsId);
    }

   
}
