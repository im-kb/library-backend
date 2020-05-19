package com.demo.springboot.domain.model_DO_PDF_GENERATORA;
import com.demo.springboot.domain.dto.AnswerDto;
import java.util.ArrayList;
public interface DocumentComponent {
    void createDocument(ArrayList<AnswerDto> answerDto, String fileDestination);
}
