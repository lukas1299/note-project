package com.example.note.service;

import com.example.note.repository.AuthorRepository;
import com.example.note.model.Author;
import com.example.note.request.AuthorRequest;
import com.example.note.response.AuthorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorResponse createAuthor(AuthorRequest authorRequest) {
        Author author = authorRepository.save(new Author(authorRequest.name()));
        return new AuthorResponse(author.getName());
    }

    public List<AuthorResponse> getAllAuthors() {
        return authorRepository
                .findAll()
                .stream()
                .map(author -> new AuthorResponse(author.getName()))
                .toList();
    }

    public AuthorResponse getAuthorById(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id " + id + " not found"));
        return new AuthorResponse(author.getName());
    }
}
