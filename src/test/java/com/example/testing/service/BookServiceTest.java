package com.example.testing.service;

import com.example.testing.model.Book;
import com.example.testing.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@RunWith(MokcitoRunner.class) // Junit 4
@ExtendWith(MockitoExtension.class) // Junit 5
class BookServiceTest {

    // klass vi vill testa
    BookService bookService;

    @Mock
    BookRepository mockRepository;

    @BeforeEach
    public void init() {
        bookService = new BookService(mockRepository);
    }

    @Test
    void getAll() {
        Book mockBook = new Book();
        mockBook.setTitle("title1");
        String expectedAuthor = "author1";

        mockBook.setAuthor(expectedAuthor);

        when(mockRepository.findAll())
                .thenReturn(Arrays.asList(mockBook));

        //        ------------------------------------


        // anropa våran metod
        List<Book> actual = bookService.getAll();

//        ------------------------------------

        assertEquals(mockBook.getTitle(), actual.get(0).getTitle());
        assertEquals(expectedAuthor, actual.get(0).getAuthor());
        assertEquals(1, actual.size());

        verify(mockRepository).findAll();
    }

    @Test
    void save_successful() {
        String expectedTitle = "title";
        String expectedAuthor = "author";

        Book savingBook = new Book();
        savingBook.setTitle(expectedTitle);
        savingBook.setAuthor(expectedAuthor);

        when(mockRepository.save(any()))
                .thenReturn(savingBook);
        // -----------------------------------

        Book actual = bookService.save(savingBook);

        // ----------------------------------

        assertEquals(savingBook.getTitle(), actual.getTitle());
        assertEquals(savingBook.getAuthor(), actual.getAuthor());

        verify(mockRepository).save(any());
        verify(mockRepository).existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(anyString(), anyString());
    }

    @Test
    void save_invalid() {
        Book invalidBook = new Book();
        // -----------------------------------

        assertThrows(ResponseStatusException.class, () -> bookService.save(invalidBook));

        // ----------------------------------

        verify(mockRepository, times(0)).save(any());
        verify(mockRepository, times(0)).existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(anyString(), anyString());

    }

    @Test
    void save_existing() {
        String expectedTitle = "title";
        String expectedAuthor = "author";

        Book savingBook = new Book();
        savingBook.setTitle(expectedTitle);
        savingBook.setAuthor(expectedAuthor);

        when(mockRepository.existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(anyString(), anyString()))
                .thenReturn(true);

        // -----------------------------------

        assertThrows(ResponseStatusException.class, () -> bookService.save(savingBook));

        // ----------------------------------


        verify(mockRepository, times(0)).save(any());
        verify(mockRepository).existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(anyString(), anyString());
    }
}