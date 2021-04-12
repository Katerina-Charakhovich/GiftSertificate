package com.epam.esm.web.advice;
import com.epam.esm.service.exeption.IllegalRequestParameterException;
import com.epam.esm.service.exeption.IllegalRequestSortParameterException;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RecourseExistException.class)
    public ResponseEntity<ErrorResponse> handleRecourseExistException(RecourseExistException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setLocalDateTime(LocalDateTime.now());
        response.setStatusCode(CustomErrorCode.RECOURSE_EXIST.getStatusCode());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RecourseNotExistException.class)
    public ResponseEntity<ErrorResponse> handleRecourseNotExistException(RecourseNotExistException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setLocalDateTime(LocalDateTime.now());
        response.setStatusCode(CustomErrorCode.RECOURSE_NOT_EXIST.getStatusCode());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalRequestSortParameterException.class)
    public ResponseEntity<ErrorResponse> handleIllegalRequestSortParameterException(IllegalRequestSortParameterException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setLocalDateTime(LocalDateTime.now());
        response.setStatusCode(CustomErrorCode.ILLEGAL_SORT_PARAMETER.getStatusCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleSqlException(SQLException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setLocalDateTime(LocalDateTime.now());
        response.setStatusCode(CustomErrorCode.ERROR_SQL.getStatusCode());
        response.setDebugMessage("Sql exception");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleIllegalRequestParameterException(IllegalRequestParameterException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setLocalDateTime(LocalDateTime.now());
        response.setStatusCode(CustomErrorCode.ILLEGAL_SORT_PARAMETER.getStatusCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setDebugMessage("Error in JSON");
        response.setLocalDateTime(LocalDateTime.now());
        response.setStatusCode(CustomErrorCode.ERROR_JSON.getStatusCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setDebugMessage("Validation arguments");
        response.setLocalDateTime(LocalDateTime.now());
        response.setStatusCode(CustomErrorCode.ERROR_VALIDATION.getStatusCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
