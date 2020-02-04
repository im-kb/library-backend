package com.demo.springboot.domain.dto;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class AnswerDto{
    private int questionId;
    private boolean lastQuestion;
    private Integer[] selectedAnswers;
    public AnswerDto() {}
    public boolean getLastQuestion() {
        return lastQuestion;
    }
    public void setLastQuestion(boolean lastQuestion) {
        this.lastQuestion = lastQuestion;
    }
    public Integer[] getSelectedAnswers() {
        return selectedAnswers;
    }
    public void setSelectedAnswers(Integer[] selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
    }
    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId=questionId;
    }
    @Override
    public String toString() {
        return "{\"AnswerData\":{"
                + "\"questionId\":\"" + questionId + ""
                + ", \"lastQuestion\":\"" + lastQuestion + "\""
                + ", \"selectedAnswers\":\"" + Arrays.toString(selectedAnswers) + "\""
                + "}}";
    }

}
