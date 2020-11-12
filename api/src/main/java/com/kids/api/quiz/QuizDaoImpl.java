package com.kids.api.quiz;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuizDaoImpl implements QuizDao {

    private static String ns = "com.kids.api.mapper.quiz.";

    @Autowired
    SqlSessionTemplate temp;

    @Override
    public List<Quiz> getQuizList() {
        return temp.selectList(ns + "quizlist");
    }

    @Override
    public Quiz selectQuiz(int quizNo) {
        return temp.selectOne(ns + "select", quizNo);
    }

    @Override
    public int createQuiz(Quiz quiz) {
        return temp.insert(ns + "create", quiz);
    }

    @Override
    public int updateQuiz(Quiz quiz) {
        return temp.update(ns + "update", quiz);
    }

    @Override
    public int deleteQuiz(int quizNo) {
        return temp.delete(ns + "delete", quizNo);
    }

    @Override
    public TodayQuiz getTodayQuiz() {
        return temp.selectOne(ns + "today");
    }

    @Override
    public int createTodayQuiz(TodayQuiz newQuiz) {
        return temp.insert(ns + "createToday", newQuiz);
    }

    @Override
    public int countQuiz() {
        return temp.selectOne(ns + "count");
    }

    @Override
    public Integer getNextQuiz() {
        return temp.selectOne(ns + "getNext");
    }

    @Override
    public QuizSolved getTodayQuizSolved(int kidId) {
        return temp.selectOne(ns+"getSolved", kidId);
    }

    @Override
    public int solve(QuizSolved quizSolved) {
        return temp.insert(ns+"solve", quizSolved);
    }

}
