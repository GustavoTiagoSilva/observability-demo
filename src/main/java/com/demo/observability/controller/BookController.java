package com.demo.observability.controller;

import com.demo.observability.dto.BookInsertDto;
import com.demo.observability.dto.BookResponseDto;
import com.demo.observability.service.BookService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/books")
@Log4j2
@RequiredArgsConstructor
@Observed(name = "bookController")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDto findById(@PathVariable(name = "bookId") UUID bookdId) throws InterruptedException {
        log.info("[GET] /books/{}", bookdId);
        return bookService.findById(bookdId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto create(@RequestBody BookInsertDto bookInsertDto) {
        log.info("[POST] /books [{}]", bookInsertDto);
        return bookService.save(bookInsertDto);
    }

    @GetMapping("/error")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void simulateAnError() {
        bookService.runError();
    }

}
