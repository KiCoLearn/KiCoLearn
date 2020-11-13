package com.kids.api.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.kids.api.money.Budget;
import com.kids.api.money.MoneyService;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizDao qDao;

    @Autowired
    MoneyService mService;
    
    @Override
    public List<Quiz> quizList() {
        return qDao.getQuizList();
    }

    @Override
    public Quiz reference(int quizNo) {
        return qDao.selectQuiz(quizNo);
    }

    @Override
    public int create(Quiz quiz) {
        return qDao.createQuiz(quiz);
    }

    @Override
    public int update(Quiz quiz) {
        return qDao.updateQuiz(quiz);
    }

    @Override
    public int delete(int quizNo) {
        return qDao.deleteQuiz(quizNo);
    }

    @Override
    public TodayQuiz getTodayQuiz() {
        return qDao.getTodayQuiz();
    }

    @Override
    public int createTodayQuiz(TodayQuiz newQuiz) {
        return qDao.createTodayQuiz(newQuiz);
    }

    @Override
    public int getFirstQuiz() {
        return qDao.getFirstQuiz();
    }

    @Override
    public Integer getNextQuiz() {
        return qDao.getNextQuiz();
    }

    @Override
    public QuizSolved getTodayQuizSolved(int kidId) {
        return qDao.getTodayQuizSolved(kidId);
    }

    @Override
    public int solve(QuizSolved quizSolved) {
        try {
            int count = qDao.solve(quizSolved);
            
            int count2 = mService.makeActivity(Budget.builder()
                                  .amount(300)
                                  .contents("퀴즈")
                                  .isDeposit(true)
                                  .kidId(quizSolved.getKidId())
                                  .build());
            
            return (count == 1 && count2 == 1) ? 1 : 0;
        } catch (DuplicateKeyException ignored) {
            return 0;
        }
    }

}
