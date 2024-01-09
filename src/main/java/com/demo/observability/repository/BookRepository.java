package com.demo.observability.repository;

import com.demo.observability.entity.Book;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Observed(name = "bookRepository")
public interface BookRepository extends JpaRepository<Book, UUID> {
}
