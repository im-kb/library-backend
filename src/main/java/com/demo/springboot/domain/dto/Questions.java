package quiz;

public class Questions {
    private String id;
    private String questionText;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswers;
    private String points;

    public Questions(){
    }

    public Questions(String id, String questionText, String answerA, String answerB, String answerC, String answerD, String correctAnswers, String points) {
        this.id=id;
        this.questionText = questionText;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswers = correctAnswers;
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
    public String getQuestionText() {
        return questionText;
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