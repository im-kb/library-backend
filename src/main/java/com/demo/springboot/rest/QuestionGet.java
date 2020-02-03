package com.demo.springboot.rest;

import java.util.ArrayList;

import com.demo.springboot.domain.dto.AnswerDto;
import com.demo.springboot.domain.dto.Questions;
import com.demo.springboot.domain.dto.ReturnQuestion;
import com.demo.springboot.service.impl.QuizCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionGet {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionGet.class);
    @RequestMapping(value = "/quiz/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReturnQuestion>test(@PathVariable("id") Integer id) {
        boolean ifLast;
        if (id==9){
            ifLast=true;
        }
        else{
            ifLast=false;
        }
        ArrayList<Questions> quiz = new ArrayList<Questions>(QuizCode.readData());
        final ReturnQuestion quizValues = new ReturnQuestion(
                quiz.get(id).getQuestion(),
                quiz.get(id).getAnswerA(),
                quiz.get(id).getAnswerB(),
                quiz.get(id).getAnswerC(),
                quiz.get(id).getAnswerD(),
                quiz.get(id).getPoints(),
        ifLast);
        return new ResponseEntity<ReturnQuestion>(quizValues, HttpStatus.OK);
    }
    @PutMapping(value = "/quiz/calculate")
    public ResponseEntity<AnswerDto>test2(@RequestBody AnswerDto answerDto) {
        LOGGER.info(String.valueOf(answerDto.getQuestionId()));
        LOGGER.info(answerDto.getSelectedAnswers());
        LOGGER.info(String.valueOf(answerDto.getLastQuestion()));
        return new ResponseEntity<AnswerDto>(answerDto, HttpStatus.OK);
    }

}


