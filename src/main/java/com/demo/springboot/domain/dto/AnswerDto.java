package com.demo.springboot.domain.dto;

public class AnswerDto{

    private int questionId;
    private boolean lastQuestion;
    private String selectedAnswers;

    public AnswerDto() {
    }

    public AnswerDto(int questionId, boolean lastQuestion, String selectedAnswers) {
        this.questionId=questionId;
        this.lastQuestion = lastQuestion;
        this.selectedAnswers = selectedAnswers;
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

    public void setSelectedAnswers(String answers) {
        this.selectedAnswers = selectedAnswers;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId=questionId;
    }

}
