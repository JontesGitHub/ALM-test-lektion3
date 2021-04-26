package com.example.testing.repository;

import com.example.testing.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @DisplayName("Should return correct book and ignoring case")
    @Test
    void existsBookByAuthorIgnoreCaseAndTitleIgnoreCase() {
        String title = "title";
        String author = "author";
        Book book = new Book(title, author);

        String upperCaseTitle = title.toUpperCase();
        String upperCaseAuthor = author.toUpperCase();

        boolean expected = title.equalsIgnoreCase(upperCaseTitle) && author.equalsIgnoreCase(upperCaseAuthor);

        repository.save(book);

        // real method
        boolean actual = repository.existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(upperCaseAuthor, upperCaseTitle);

        assertEquals(expected, actual);
    }
}