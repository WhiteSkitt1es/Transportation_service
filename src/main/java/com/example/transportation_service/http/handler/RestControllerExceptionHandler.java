package com.example.transportation_service.http.handler;

import com.example.transportation_service.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validateException(MethodArgumentNotValidException exception) {
        log.warn(exception.getMessage());
        return ResponseEntity.badRequest().body(ErrorDto.of("Ошибка валидации, проверьте правильность полей!"));
    }
}
