package com.kids.api.kidsaccount;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kids {
    private int kidId;
    private String name;
    private Date birth;
    private int totalMoney;
    private int parentId;
    private boolean gender;
    private int characterIdx;
}
