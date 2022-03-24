package com.example.updateprojecttoapi.models.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Response {
    private HttpStatus httpStatus;
    private String message;
}
