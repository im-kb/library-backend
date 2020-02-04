package com.demo.springboot.domain.model;

import com.demo.springboot.domain.dto.AnswerDto;
import com.demo.springboot.domain.dto.UserDataDto;

import java.util.ArrayList;

public interface DocumentComponent {
    void createDocument(ArrayList<AnswerDto> answerDto, String fileDestination);
}
