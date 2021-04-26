package com.example.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor // lombok
@AllArgsConstructor // lombok
@Data // lombok
@Entity
public class Book {
    @Id
    @GeneratedValue
    private UUID id;

//    @Column(name = "title", nullable = false)
    private String title;

//    @Column(name = "author", nullable = false)
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

}
