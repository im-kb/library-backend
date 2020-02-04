//package com.demo.springboot.rest;
//
//import com.demo.springboot.domain.dto.*;
//import com.demo.springboot.service.FileService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class PdfGeneratorApiController {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(PdfGeneratorApiController.class);
//
//    //private static final String PATH = "D:\\pdf\\"; // dla windows
//    private static final String PATH = "/Users/tomaszgadek/Downloads/pdf/"; // dla linux / unix
//
//    @Autowired
//    private FileService fileService;
//
//    @CrossOrigin
//    @GetMapping(value = "/server-test")
//    public ResponseEntity<Map<String, String>> serverTest() {
//        LOGGER.info("--- run server status");
//
//        Map<String, String> serverTestMessage = new HashMap<>();
//        serverTestMessage.put("server-status", "RUN :-)");
//
//        return new ResponseEntity<>(serverTestMessage, HttpStatus.OK);
//    }
//
//    @CrossOrigin
//    @GetMapping(value = "/files/find-all")
//    public ResponseEntity<List<FileData>> findAll() {
//        LOGGER.info("--- find all");
//
//        List<FileData> files = fileService.findAll(PATH);
//
//        return files.isEmpty() ?
//                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
//                new ResponseEntity<>(files, HttpStatus.OK);
//    }
//
//    @CrossOrigin
//    @PostMapping(value = "/files/create-file")
//    public ResponseEntity<?> createFile(@RequestBody UserDataDto userDataDto) {
//        LOGGER.info("--- create pdf file for user: {}", userDataDto.getFirstName());
//
//        @PostMapping(value = "/quiz/report")
//        public ResponseEntity<ArrayList<AnswerDto>> test3(@RequestBody ArrayList<AnswerDto> answerDto) {
//            //LOGGER.info(Arrays.toString(answerDto.get(0).getSelectedAnswers()));
//
//            LOGGER.info("Tworzenie raportu:");
//
//            FileData fileData = fileService.createFile(answerDto, PATH);
//
//            ErrorDto errorMessage = new ErrorDto(ErrorMessage.ERROR_PATH.getErrorMessage());
//            HttpStatus errorCode = ErrorMessage.ERROR_PATH.getErrorCode();
//
//        FileData fileData = fileService.createFile(userDataDto, PATH);
//
//        ErrorDto errorMessage = new ErrorDto(ErrorMessage.ERROR_PATH.getErrorMessage());
//        HttpStatus errorCode = ErrorMessage.ERROR_PATH.getErrorCode();
//
//        return fileData != null ?
//                new ResponseEntity<>(fileData, HttpStatus.CREATED) :
//                new ResponseEntity<>(errorMessage, errorCode);
//    }
//}
