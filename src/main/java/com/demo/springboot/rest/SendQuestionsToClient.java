package com.demo.springboot.rest;

import java.util.ArrayList;

import com.demo.springboot.domain.dto.Questions;
import com.demo.springboot.domain.dto.ReturnQuestion;
import com.demo.springboot.service.impl.QuizCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SendQuestionsToClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendQuestionsToClient.class);

    @RequestMapping(value = "/quiz/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReturnQuestion> test(@PathVariable("id") Integer id) {

        ArrayList<Questions> quiz = new ArrayList<Questions>(QuizCode.readData());
        boolean ifLast;
        if (id == quiz.size()-1) {
            ifLast = true;
        } else {
            ifLast = false;
        }
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
}


