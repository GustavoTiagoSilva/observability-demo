package com.demo.observability.dto;

import com.demo.observability.entity.Book;

import java.util.UUID;

public record BookResponseDto(UUID id, String name, String description) {
    public static BookResponseDto from(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getName(),
                book.getDescription()
        );
    }
}
