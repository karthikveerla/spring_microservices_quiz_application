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
            // Optional<Quiz> quiz = quizRepository.findById(id);
            // List<Question> questionsFromDB = quiz.get().getQuestions();
             List<QuestionWrapper> questionsForUser = new ArrayList<>();

            // for(Question q: questionsFromDB){
            //     QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            //     questionsForUser.add(qw);
            // }

            return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> quizresponses) {
        // Quiz quiz = quizRepository.findById(id).get();
        // List<Question> questions = quiz.getQuestions();
         int score = 0,i =0;
        // for(QuizResponse qr: quizresponses){
        //     if(qr.getResponse().equals(questions.get(i).getRightAnswer())){
        //         score += 1;
        //     }
        //     i += 1;
        // }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }


    
}
