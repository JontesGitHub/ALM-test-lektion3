package com.example.testing.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data // lombok
@Entity
//@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue
    private UUID id;

//    @Column(name = "title", nullable = false)
    private String title;

//    @Column(name = "author", nullable = false)
    private String author;

}
