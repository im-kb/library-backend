package com.demo.springboot.rest;


import com.demo.springboot.service.impl.QuizCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")

public class QuestionGet {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionGet.class);
    @Autowired
    QuizCode quizCode;

    @RequestMapping(value = "/quiz/question", method = RequestMethod.GET)
    public ResponseEntity<quiz.Questions>test(){
        final quiz.Questions quizValues = new quiz.Questions();
        LOGGER.info("### cokolwiek");
        LOGGER.info("### Question : {} , Answers : {} , Points : {} ",
                quizValues.getQuestionText(),
                quizValues.getCorrectAnswers(),
                quizValues.getPoints());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
