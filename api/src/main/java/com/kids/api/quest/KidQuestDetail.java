package com.kids.api.quest;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KidQuestDetail {
    private int kidId;
    private int questNo;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean finish;
    private String name;
    private int reward;
    private String description;

}
