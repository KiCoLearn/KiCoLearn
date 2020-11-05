package com.kids.api.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private int itemNo;
    private String name;
    private int price;
    private String description;
    private String field;
    private int parentId;

}
