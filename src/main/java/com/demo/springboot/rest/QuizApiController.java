package com.demo.springboot.rest;
import com.demo.springboot.domain.dto.ErrorDto;
import com.demo.springboot.domain.dto.ErrorMessage;
import com.demo.springboot.domain.dto.FileData;
import com.demo.springboot.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.springboot.domain.dto.*;
import com.demo.springboot.service.impl.QuizCode;
import java.util.ArrayList;


@RestController
public class QuizApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizApiController.class);
    private static final String PATH = "C://pdf//"; // dla linux / unix

    @Autowired
    private FileService fileService;


    private static int yourPoints=0;
    ArrayList<Questions> quiz = new ArrayList<Questions>(QuizCode.readData());
    @RequestMapping(value = "/quiz/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReturnQuestion>test(@PathVariable("id") Integer id) {
        try {
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
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value = "/quiz/calculate")
    public ResponseEntity<AnswerDto> test2(@RequestBody AnswerDto answerDto) {
        System.out.print("Dobre odpowiedzi to pytania o id "+answerDto.getQuestionId()+" to: ");
        System.out.println(quiz.get(answerDto.getQuestionId()).getCorrectAnswers());//pobiera correct answers do aktualnego id pytania (id pobrane od klienta)

       int plus=QuizCode.checkAnswer(answerDto.getSelectedAnswers(),quiz.get(answerDto.getQuestionId()).getCorrectAnswers(),Integer.parseInt(quiz.get(answerDto.getQuestionId()).getPoints()));
        yourPoints = yourPoints + plus;
        System.out.println("Twoj wynik wynosi teraz: "+yourPoints);
        System.out.print("dziala klasa GetAnswersFromClient: ");
        LOGGER.info(answerDto.toString());
        return new ResponseEntity<AnswerDto>(answerDto, HttpStatus.OK);
    }
    @PostMapping(value = "/quiz/report")
    public ResponseEntity<ArrayList<AnswerDto>> test3(@RequestBody ArrayList<AnswerDto> answerDto) {
        //LOGGER.info(Arrays.toString(answerDto.get(0).getSelectedAnswers()));

        LOGGER.info("Tworzenie raportu:");

        FileData fileData = fileService.createFile(answerDto, PATH);

        ErrorDto errorMessage = new ErrorDto(ErrorMessage.ERROR_PATH.getErrorMessage());
        HttpStatus errorCode = ErrorMessage.ERROR_PATH.getErrorCode();

        //return fileData != null ? new ResponseEntity<>(HttpStatus.CREATED) :
               // new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(answerDto, HttpStatus.OK);
    }
}


