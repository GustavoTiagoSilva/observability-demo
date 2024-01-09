package com.demo.observability.service;

import com.demo.observability.dto.BookInsertDto;
import com.demo.observability.dto.BookResponseDto;
import com.demo.observability.entity.Book;
import com.demo.observability.exceptions.BookNotFoundException;
import com.demo.observability.repository.BookRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
@Observed(name = "bookService")
public class BookService {

    private final BookRepository bookRepository;


    public BookResponseDto save(BookInsertDto bookInsertDto) {
        Book book = new Book(null, bookInsertDto.name(), bookInsertDto.description());
        book = bookRepository.save(book);
        log.info("Book created successfully with id: [{}]", book.getId());
        return BookResponseDto.from(book);
    }

    @Transactional(readOnly = true)
    public BookResponseDto findById(UUID bookId) {

        var book = bookRepository.findById(bookId).orElseThrow(() -> {
            log.warn("Book with id [{}] not found", bookId);
            return new BookNotFoundException("Book with id" +  bookId + " not found");
        });

        log.info("Book with id [{}] found successfully", bookId);
        return BookResponseDto.from(book);
    }

    public void runError() {
        log.error("An error has occurred, check your observability tools to do a proper troubleshooting");
        throw new RuntimeException("An error has occurred, check your observability tools to do a proper troubleshooting");
    }

}
