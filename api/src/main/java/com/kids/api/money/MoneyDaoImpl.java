package com.kids.api.money;

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

}
