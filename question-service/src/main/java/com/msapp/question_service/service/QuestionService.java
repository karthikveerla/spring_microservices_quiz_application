package com.msapp.question_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.msapp.question_service.dto.QuestionRequest;
import com.msapp.question_service.entity.Question;
import com.msapp.question_service.entity.QuestionWrapper;
import com.msapp.question_service.entity.QuizResponse;
import com.msapp.question_service.repository.QuestionRepository;

import java.util.*;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionRepository.findAll(),HttpStatus.OK);
    
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String cat) {
        try{
            return new ResponseEntity<>(questionRepository.findByCategoryIgnoreCase(cat),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        
    }

    public ResponseEntity<String> addQuestion(QuestionRequest request){
        try{
            Question qtn = new Question();
            qtn.setCategory(request.getCategory());
            qtn.setDifficultyLevel(request.getDifficultyLevel());
            qtn.setOption1(request.getOption1());
            qtn.setOption2(request.getOption2());
            qtn.setOption3(request.getOption3());
            qtn.setOption4(request.getOption4());
            qtn.setQuestionTitle(request.getQuestionTitle());
            qtn.setRightAnswer(request.getRightAnswer());
            questionRepository.save(qtn);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("unable to create",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer noOfQuestions) {
        List<Integer> questions = questionRepository.findRandomQuestionsByCategory(categoryName,noOfQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> qs = new ArrayList<>();
        for(Integer id: questionIds){
            qs.add(questionRepository.findById(id).get());
        }
        for(Question q : qs){
            QuestionWrapper w = new QuestionWrapper();
            w.setId(q.getId());
            w.setQuestionTitle(q.getQuestionTitle());
            w.setOption1(q.getOption1());
            w.setOption2(q.getOption2());
            w.setOption3(q.getOption3());
            w.setOption4(q.getOption4());
            wrappers.add(w);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<QuizResponse> responses) {
        
        int score = 0;
        for(QuizResponse qr: responses){
            Question q = questionRepository.findById(qr.getId()).get();
            if(qr.getResponse().equals(q.getRightAnswer())){
                score += 1;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
    

}
