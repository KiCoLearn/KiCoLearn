package com.kids.api.quiz;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Quiz {
    private int quizNo;

    @NonNull
    private String question;

    @NonNull
    private String description;

    @NonNull
    private Boolean answer;

    @NonNull
    private String category;
}
