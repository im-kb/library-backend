/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

public class CheckAnswer {

    public float checkAnswer(String yourAnswer, String correctAnswers) {
        float countCorrect = 0;
        if (yourAnswer.length() > 7) {
            yourAnswer = yourAnswer.substring(0, 7);
        }
        yourAnswer = yourAnswer.replaceAll(",", "");
        correctAnswers = correctAnswers.replaceAll(",", "");

        char[] yourAnswerArray = yourAnswer.toCharArray();
        char[] correctAnswersArray = correctAnswers.toCharArray();

        for (int i = 0; i < correctAnswers.length(); i++) {
            for (int j = 0; j < yourAnswer.length(); j++) {
                if (yourAnswerArray[j] == correctAnswersArray[i]) {
                    countCorrect++;
                }
            }
        }
        return countCorrect / correctAnswers.length();


    }
}

/// A, B, C, D

/// A, B, C, D