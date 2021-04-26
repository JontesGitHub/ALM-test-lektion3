package com.example.testing.controller;

import com.example.testing.model.Book;
import com.example.testing.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService service;

    @GetMapping("/books")
    public List<Book> getAll() {
        return service.getAll();
    }

    @PostMapping("/books")
    public Book save(@RequestBody Book book) {
        return service.save(book);
    }

}
