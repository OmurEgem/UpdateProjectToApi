package com.example.updateprojecttoapi.exceptions;


import com.example.updateprojecttoapi.models.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException){
        return Response.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
    }
}
