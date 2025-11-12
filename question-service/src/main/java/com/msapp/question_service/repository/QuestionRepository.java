package com.msapp.question_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.msapp.question_service.entity.Question;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>{
    List<Question> findByCategoryIgnoreCase(String category);



    @Query(value = "SELECT id FROM (SELECT q.id FROM question q WHERE q.category = :category ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= :numq",nativeQuery=true)
    List<Integer> findRandomQuestionsByCategory(@Param("category") String category, @Param("numq") int numq);
}
