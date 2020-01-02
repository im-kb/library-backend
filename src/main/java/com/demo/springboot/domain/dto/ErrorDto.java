package com.demo.springboot.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/** Klasa odpowiedzialna za przechowywanie informacji o bledach w aplikacji */

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorDto {
    String errorMessage;
}
