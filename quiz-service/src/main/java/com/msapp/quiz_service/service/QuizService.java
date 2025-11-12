package com.msapp.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.msapp.quiz_service.entity.QuestionWrapper;
import com.msapp.quiz_service.entity.Quiz;
import com.msapp.quiz_service.entity.QuizResponse;
import com.msapp.quiz_service.feign.QuizInterface;
import com.msapp.quiz_service.repository.QuizRepository;



@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    // @Autowired
    // QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numq, String title) {
        
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numq).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Success",HttpStatus.CREATED);
        
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
            Quiz quiz = quizRepository.findById(id).get();
            List<Integer> qids = quiz.getQuestionIds();
            ResponseEntity<List<QuestionWrapper>> qs = quizInterface.getQuestionFromId(qids);

            return qs;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> quizresponses) {
        ResponseEntity<Integer> score = quizInterface.getScore(quizresponses);

        return score;
    }


    
}
