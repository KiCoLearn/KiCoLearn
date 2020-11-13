package com.kids.api.money;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Budget {
    private String contents;
    private int amount;
    private Date date;
    private Boolean isDeposit;
    private int leftMoney;
    private int kidId;
}
