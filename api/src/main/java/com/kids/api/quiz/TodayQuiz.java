package com.kids.api.quiz;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TodayQuiz {
    private Date date;

    @NonNull
    private Integer quizNo;
}
