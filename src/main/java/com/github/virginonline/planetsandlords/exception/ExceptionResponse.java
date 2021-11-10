package com.github.virginonline.planetsandlords.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "ExceptionResponse")
public class ExceptionResponse {
    private final int status;
    private final String title;
    private final List<String> errors;


    public ExceptionResponse(HttpStatus status, String title, List<String> errors) {
        this.status = status.value();
        this.title = title;
        this.errors = errors;
    }

    public ExceptionResponse(HttpStatus status, String title) {
        this.status = status.value();
        this.title = title;
        this.errors = null;
    }
}

