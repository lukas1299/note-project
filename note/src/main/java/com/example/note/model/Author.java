package com.example.note.model;

import jakarta.persistence.*;

import lombok.RequiredArgsConstructor;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Author(String name){
        this.name = name;
    }

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
