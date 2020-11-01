package com.kids.api.money;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {
    private String contents;
    private int amount;
    private Date date;
    private boolean type;
    private int leftMoney;
    private int kidId;
}
