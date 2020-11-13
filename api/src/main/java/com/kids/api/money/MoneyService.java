package com.kids.api.money;

import java.util.List;

public interface MoneyService {
    int getTotalMoney(int kidId);

    int deposit(Money money);

    int withdraw(Money money);

    int activity(Budget budget);

    List<Budget> activityList(int kidId);

    int getTodaySpend(int kidId);

    int getWeekSpend(int kidId);
    
    int getTodayDeposit(int kidId);

    int getWeekDeposit(int kidId);
    
    List<Budget> todaySpendContents(int kidsId);
    
    List<Budget> weekSpendContents(int kidsId);
    
    List<Budget> todayDepositContents(int kidsId);
    
    List<Budget> weekDepositContents(int kidsId);
}
