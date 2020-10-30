package com.kids.api.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Quiz {
    private int quizNo;
    @NonNull private String question;
    @NonNull private String description;
    @NonNull private Boolean answer;
    @NonNull private String category;
}
