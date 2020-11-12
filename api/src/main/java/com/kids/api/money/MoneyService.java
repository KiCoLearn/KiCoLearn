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
    
    List<Budget> todayDeposit(int kidId);
    
    List<Budget> weekDeposit(int kidId);
}
