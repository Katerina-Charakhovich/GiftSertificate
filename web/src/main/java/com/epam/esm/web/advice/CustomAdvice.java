package com.epam.esm.web.advice;

import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RecourseExistException.class)
    public ResponseEntity<ErrorResponse> handleRecourseExistException(RecourseExistException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setLocalDateTime(LocalDateTime.now());
        String str=CustomErrorCode.RECOURSE_EXIST.getStatusCode();
        response.setStatusCode(CustomErrorCode.RECOURSE_EXIST.getStatusCode());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecourseNotExistException.class)
    public ResponseEntity<ErrorResponse> handleRecourseNotExistException(RecourseNotExistException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setLocalDateTime(LocalDateTime.now());
        response.setStatusCode(CustomErrorCode.RECOURSE_NOT_EXIST.getStatusCode());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }
}
