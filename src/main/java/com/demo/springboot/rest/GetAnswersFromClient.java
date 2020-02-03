package com.demo.springboot.rest;

import com.demo.springboot.domain.dto.AnswerDto;
import com.demo.springboot.domain.dto.Questions;
import com.demo.springboot.domain.dto.ReturnQuestion;
import com.demo.springboot.service.impl.QuizCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class GetAnswersFromClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAnswersFromClient.class);
     private static int yourPoints=0;
    @PutMapping(value = "/quiz/calculate")
    public ResponseEntity<AnswerDto> test2(@RequestBody AnswerDto answerDto) {
        System.out.print("Dobre odpowiedzi to pytania o id "+answerDto.getQuestionId()+" to: ");
        ArrayList<Questions> quiz = new ArrayList<Questions>(QuizCode.readData());//pobiera correct answers do aktualnego id pytania (id pobrane od klienta)
        System.out.println(quiz.get(answerDto.getQuestionId()).getCorrectAnswers());//pobiera correct answers do aktualnego id pytania (id pobrane od klienta)

       String yourAnswer="";
       for(int i=0;i<answerDto.getSelectedAnswers().length;i++){
           if(i<answerDto.getSelectedAnswers().length-1){
               yourAnswer=yourAnswer +answerDto.getSelectedAnswers()[i]+",";
           }
           else{
               yourAnswer=yourAnswer+answerDto.getSelectedAnswers()[i];
           }
       }
       int plus=QuizCode.checkAnswer(yourAnswer,quiz.get(answerDto.getQuestionId()).getCorrectAnswers(),Integer.parseInt(quiz.get(answerDto.getQuestionId()).getPoints()));

        yourPoints = yourPoints + plus;
        System.out.println("Twoj wynik wynosi teraz: "+yourPoints);
        System.out.print("dziala klasa GetAnswersFromClient: ");
        LOGGER.info(answerDto.toString());
        return new ResponseEntity<AnswerDto>(answerDto, HttpStatus.OK);

    }


}


