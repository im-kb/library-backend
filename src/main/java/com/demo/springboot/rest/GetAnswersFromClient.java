package com.demo.springboot.rest;

import com.demo.springboot.domain.dto.AnswerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetAnswersFromClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAnswersFromClient.class);

    @PutMapping(value = "/quiz/calculate")
    public ResponseEntity<AnswerDto> test2(@RequestBody AnswerDto answerDto) {
        System.out.print("dziala klasa GetAnswersFromClient: ");
        LOGGER.info(answerDto.toString());
        return new ResponseEntity<AnswerDto>(answerDto, HttpStatus.OK);
    }

    public class CheckAnswer { //sprawdzanie odpowiedzi

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

}


