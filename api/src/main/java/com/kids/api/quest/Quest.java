package com.kids.api.quest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quest {
    private int questNo;
    private String name;
    private int reward;
    private String description;
    private int parentId;
    private boolean deleted;

}
