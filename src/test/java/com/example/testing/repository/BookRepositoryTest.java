package com.example.testing.repository;

import com.example.testing.model.Book;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    // vår klass vi vill testa
    @Autowired
    BookRepository bookRepository;

    @Test
    void existsBookByAuthorIgnoreCaseAndTitleIgnoreCase() {
        String expectedTitle = "title";
        String expectedAuthor = "author";

        Book savingBook = new Book();
        savingBook.setTitle(expectedTitle);
        savingBook.setAuthor(expectedAuthor);

        bookRepository.save(savingBook);
        // ---------------------------

        boolean actual = bookRepository.existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(expectedAuthor, expectedTitle);

        // ---------------------------

        assertEquals(true, actual);
    }

    @Disabled
    @Test
    void existsBookByAuthorIgnoreCaseAndTitleIgnoreCase_1() {
        String expectedTitle = "title";
        String expectedAuthor = "author";

        // ---------------------------

        boolean actual = bookRepository.existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(expectedAuthor, expectedTitle);

        // ---------------------------

        assertEquals(false, actual);
    }
}