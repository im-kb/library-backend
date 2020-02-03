package com.demo.springboot.domain.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AnswerDto{
    private int questionId;
    private boolean lastQuestion;
    private String selectedAnswers;

    public AnswerDto() {
    }


    public boolean getLastQuestion() {
        return lastQuestion;
    }

    public void setLastQuestion(boolean lastQuestion) {
        this.lastQuestion = lastQuestion;
    }

    public String getSelectedAnswers() {
        return selectedAnswers;
    }

    public void setSelectedAnswers(String selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId=questionId;
    }

}
