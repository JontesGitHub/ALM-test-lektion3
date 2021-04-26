package com.example.testing.service;

import com.example.testing.model.Book;
import com.example.testing.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book save(Book book) {
        validateBook(book);

        boolean found = repository.existsBookByAuthorIgnoreCaseAndTitleIgnoreCase(book.getAuthor(), book.getTitle());
        if (found) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book already exists.");
        }
        return repository.save(book);
    }

    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getAuthor() == null || book.getAuthor().isEmpty() || book.getTitle().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Both Author and Title must exist.");
        }
    }
}
