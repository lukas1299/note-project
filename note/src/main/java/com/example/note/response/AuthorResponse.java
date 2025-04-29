package com.example.note.response;


public class AuthorResponse {

    private String name;

    public AuthorResponse() {
    }

    public AuthorResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
