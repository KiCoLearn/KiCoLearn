package com.kids.api.money;

public interface MoneyService {
	int getTotalMoney(int kidId);
	int deposit(Money money);
	int withdraw(Money money);
}
