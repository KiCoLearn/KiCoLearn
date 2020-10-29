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

}