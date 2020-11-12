package com.kids.api.quiz;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizSolved {
    private Date date;
    private int kidId;
    private Boolean correct;
}
