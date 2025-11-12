package com.msapp.quiz_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class QuestionRequest {

    private String category;

    private String difficultyLevel;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String questionTitle;

    private String rightAnswer;
}
