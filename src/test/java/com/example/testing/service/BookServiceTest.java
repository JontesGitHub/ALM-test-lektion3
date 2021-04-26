package com.example.testing.service;

import com.example.testing.model.Book;
import com.example.testing.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    BookService bookService;

    @Mock
    BookRepository repositoryMock;

    @BeforeEach
    public void init() {
        bookService = new BookService(repositoryMock);
    }

    @DisplayName("get all books successfully")
    @Test
    void getAll() {
        // testing method
        bookService.getAll();

        verify(repositoryMock).findAll();
    }

    @DisplayName("saving book will be successful")
    @Test
    void save() {
        UUID expectedID = UUID.randomUUID();
        String expectedTitle = "title";
        String expectedAuthor = "author";

        Book book = new Book(expectedTitle, expectedAuthor);

        when(repositoryMock.existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(anyString(), anyString()))
                .thenReturn(false);

        when(repositoryMock.save(isA(Book.class)))
                .thenReturn(new Book(expectedID, expectedTitle, expectedAuthor));

        // testing method
        Book actual = bookService.save(book);

        verify(repositoryMock).save(isA(Book.class));

        assertEquals(expectedID, actual.getId());
        assertEquals(expectedTitle, actual.getTitle());
        assertEquals(expectedAuthor, actual.getAuthor());
    }

    @DisplayName("saving existing book will throw error")
    @Test
    void save_existing() {
        String existingTitle = "title";
        String existingAuthor = "author";

        Book book = new Book(existingTitle, existingAuthor);

        when(repositoryMock.existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(anyString(), anyString()))
                .thenReturn(true);

        // testing method
        assertThrows(ResponseStatusException.class, () -> bookService.save(book));

        verify(repositoryMock, times(0)).save(isA(Book.class));
    }

    @DisplayName("saving invalid book will throw error")
    @Test
    void save_invalid() {
        String invalidTitle = "";
        String invalidAuthor = "";

        Book book = new Book(invalidTitle, invalidAuthor);

        // testing method
        assertThrows(ResponseStatusException.class, () -> bookService.save(book));

        verify(repositoryMock, times(0)).save(isA(Book.class));
    }

}