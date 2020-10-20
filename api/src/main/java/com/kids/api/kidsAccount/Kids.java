package com.kids.api.kidsAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kids {
    private int kidId;
    private String name;
    private int age;
    private int totalMoney;
    private int parentId;
}
