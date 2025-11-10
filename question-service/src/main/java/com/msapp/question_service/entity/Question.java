package com.msapp.question_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // works for Oracle 12c+ with identity column
    private Integer id;

    private String category;

    @Column(name = "difficultylevel")
    private String difficultyLevel;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "right_answer")
    private String rightAnswer;
}