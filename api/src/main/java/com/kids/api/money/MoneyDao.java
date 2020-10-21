package com.kids.api.money;

public interface MoneyDao {
	int getTotalMoney(int kidsId);
	int moneyDeposit(Money money);
	int moneyWithdraw(Money money);
}
