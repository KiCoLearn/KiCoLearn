package com.kids.api.quiz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ExampleProperty;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    static Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    QuizService qService;

    @GetMapping("/")
    @ApiOperation(value = "퀴즈 목록 조회")
    private ResponseEntity<Map<String, Object>> quizList() {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            List<Quiz> quizzes = qService.quizList();
            result = handleSuccess(quizzes);
        } catch (Exception e) {

        }
        return result;
    }

    @GetMapping("/{quizNo}")
    @ApiOperation(value = "퀴즈번호로 퀴즈정보 조회")
    private ResponseEntity<Map<String, Object>> selectQuiz(@PathVariable int quizNo) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            Quiz quiz = qService.reference(quizNo);
            result = handleSuccess(quiz);
        } catch (Exception e) {
            result = handleException(e);
        }
        return result;
    }

    @PostMapping("/create")
    @ApiOperation(value = "새로운 퀴즈를 생성")
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "quizInfo",
            examples = @io.swagger.annotations.Example(
                                                       
                value = {
                    @ExampleProperty(value = "{'property': 'test'}", mediaType = "application/json")
                })) 
    })
    private ResponseEntity<Map<String, Object>> createQuiz(@RequestBody Quiz quiz) {
        ResponseEntity<Map<String, Object>> result = null;
        try {           
            result = handleSuccess(qService.create(quiz));
        } catch (Exception e) {
            result = handleException(e);
        }
        return null;
    }

    @PostMapping("/update")
    @ApiOperation(value = "퀴즈의 정보를 수정")
    private ResponseEntity<Map<String, Object>> updateQuiz(@RequestBody Quiz quiz) {
        ResponseEntity<Map<String, Object>> result = null;
        try {            
            result = handleSuccess(qService.update(quiz));
        } catch (Exception e) {
            result = handleException(e);
        }
        return null;
    }

    @DeleteMapping("/delete/{quizNo}")
    @ApiOperation(value = "퀴즈 삭제")
    private ResponseEntity<Map<String, Object>> deleteQuiz(@PathVariable int quizNo) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            result = handleSuccess(qService.delete(quizNo));
        } catch (Exception e) {
            result = handleException(e);
        }
        return null;
    }

    private ResponseEntity<Map<String, Object>> template() {
        ResponseEntity<Map<String, Object>> result = null;
        return null;
    }

    private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", true);
        resultMap.put("data", data);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> handleException(Exception e) {
        logger.error("예외 발생 : ", e);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", false);
        resultMap.put("data", e.getMessage());
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
