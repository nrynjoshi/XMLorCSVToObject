package com.demo.induction.tp.api;

import com.demo.induction.tp.model.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ Exception.class})
    public ResponseEntity<ResponseMessage> exceptionHandle(){
       return new ResponseEntity<>(new ResponseMessage(500,"Sorry for inconvenience"), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
