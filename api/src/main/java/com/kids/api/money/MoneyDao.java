package com.kids.api.money;

import java.util.List;

public interface MoneyDao {
    int getTotalMoney(int kidsId);

    int moneyDeposit(Money money);

    int moneyWithdraw(Money money);

    int budgetActivity(Budget budget);

    List<Budget> kidActivityList(int kidId);

    int getTodaySpend(int kidId);

    int getWeekSpend(int kidId);
    
    int getTodayDeposit(int kidId);

    int getWeekDeposit(int kidId);
    
    List<Budget> todaySpendContents(int kidsId);
    
    List<Budget> weekSpendContents(int kidsId);
    
    List<Budget> todayDepositContents(int kidsId);
    
    List<Budget> weekDepositContents(int kidsId);
}
