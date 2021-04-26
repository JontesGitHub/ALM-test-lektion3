package com.example.testing.repository;

import com.example.testing.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    boolean existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(String author, String title);
}
