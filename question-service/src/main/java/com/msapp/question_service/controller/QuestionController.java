package com.msapp.question_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.msapp.question_service.dto.QuestionRequest;
import com.msapp.question_service.entity.Question;
import com.msapp.question_service.entity.QuestionWrapper;
import com.msapp.question_service.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.msapp.question_service.entity.QuizResponse;




@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")   
    public ResponseEntity<List<Question>> allQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat}")
    public ResponseEntity<List<Question>> questionsByCategory(@PathVariable String cat){
        return questionService.getQuestionsByCategory(cat);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionRequest request) {
        return questionService.addQuestion(request);
    }
    

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer noOfQuestions) {
        return questionService.getQuestionsForQuiz(categoryName,noOfQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds) {
        System.out.println(environment.getProperty("local.server.port"));

        return questionService.getQuestionsFromIds(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses) {
        return questionService.getScore(responses);
    }
    
    
    
}
