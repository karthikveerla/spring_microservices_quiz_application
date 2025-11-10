package com.msapp.question_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.msapp.question_service.entity.Question;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>{
    List<Question> findByCategoryIgnoreCase(String category);



    @Query(value = "SELECT q.id FROM question q where q.category=:category ORDER BY DBMS_RANDOM.VALUE FETCH FIRST :numq ROWS ONLY",nativeQuery=true)
    List<Integer> findRandomQuestionsByCategory(String category, int numq);
}
