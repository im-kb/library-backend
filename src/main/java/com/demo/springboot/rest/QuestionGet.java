package com.demo.springboot.rest;

import java.util.ArrayList;
import com.demo.springboot.domain.dto.Questions;
import com.demo.springboot.domain.dto.ReturnQuestion;
import com.demo.springboot.service.impl.QuizCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/api")

public class QuestionGet {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionGet.class);


    @RequestMapping(value = "/quiz/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReturnQuestion> test(@PathVariable("id") Integer id) {
        id = id - 1;// zeby sie zgadzaly indexy XDDD

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
}


