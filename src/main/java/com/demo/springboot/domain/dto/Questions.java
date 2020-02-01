package com.demo.springboot.domain.dto;


public class Questions {
    private String id;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswers;
    private String points;

    public Questions() {
    }

    public Questions(String id, String question, String answerA, String answerB, String answerC, String answerD, String correctAnswers, String points) {
        this.id = id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswers = correctAnswers;
        this.points = points;
    }

    public Questions(String question, String answerA, String answerB, String answerC, String answerD, String points) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.points = points;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }


    /**
     * @return the questionText
     */
    public String getQuestion() {
        return question;
    }


    /**
     * @return the answerA
     */
    public String getAnswerA() {
        return answerA;
    }


    /**
     * @return the answerB
     */
    public String getAnswerB() {
        return answerB;
    }


    /**
     * @return the answerC
     */
    public String getAnswerC() {
        return answerC;
    }


    /**
     * @return the answerD
     */
    public String getAnswerD() {
        return answerD;
    }


    /**
     * @return the correctAnswers
     */
    public String getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * @return the points
     */
    public String getPoints() {
        return points;
    }


}