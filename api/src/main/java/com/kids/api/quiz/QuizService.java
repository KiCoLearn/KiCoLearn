package com.kids.api.quiz;

import java.util.List;

public interface QuizService {
    List<Quiz> quizList();

    Quiz reference(int quizNo);

    int create(Quiz quiz);

    int update(Quiz quiz);

    int delete(int quizNo);

    TodayQuiz getTodayQuiz();

    Integer getNextQuiz();

    int createTodayQuiz(TodayQuiz newQuiz);

    int countQuiz();
}
