package com.example.testing;

import com.example.testing.model.Book;
import com.example.testing.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

//@Slf4j
@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingApplication.class, args);
    }

//    @Autowired
//    private BookRepository repository;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void runAfterStartup() {
//        List<Book> books = repository.findAll();
//        log.info("Starting number of books: " + books.size());
//
//        Book book = new Book();
//        book.setAuthor("john doe");
//        book.setTitle("Best title nr" + (books.size() +1));
//        log.info("Saving new Book...");
//        repository.save(book);
//
//        books = repository.findAll();
//        log.info("Current number of books: " + books.size());
//    }
}
