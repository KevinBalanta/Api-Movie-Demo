package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDTO errorDetails = new ErrorDTO(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MovieAPIException.class)
    public ResponseEntity<ErrorDTO> handleMovieAPIException(MovieAPIException exception,WebRequest webRequest){
        ErrorDTO errorDto= new ErrorDTO(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(Exception exception,WebRequest webRequest){
        ErrorDTO errorDetails = new ErrorDTO(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
