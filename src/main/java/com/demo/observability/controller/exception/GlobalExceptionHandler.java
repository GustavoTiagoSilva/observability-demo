package com.demo.observability.controller.exception;

import com.demo.observability.dto.HttpExceptionHandlerResponseDto;
import com.demo.observability.exceptions.BookNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<HttpExceptionHandlerResponseDto> bookNotFoundException(BookNotFoundException e, HttpServletRequest req) {

        log.error("Book not found", e);

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        HttpExceptionHandlerResponseDto httpExceptionHandlerResponse = new HttpExceptionHandlerResponseDto(
                Instant.now(),
                httpStatus.value(),
                "Book not found",
                e.getMessage(),
                req.getRequestURI()
        );

        return new ResponseEntity<>(httpExceptionHandlerResponse, httpStatus);
    }

}
