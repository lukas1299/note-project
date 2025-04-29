package com.example.note.service;

import com.example.note.model.Author;
import com.example.note.model.Note;
import com.example.note.repository.AuthorRepository;
import com.example.note.repository.NoteRepository;
import com.example.note.request.NoteRequest;
import com.example.note.response.NoteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final AuthorRepository authorRepository;

    public NoteService(NoteRepository noteRepository, AuthorRepository authorRepository) {
        this.noteRepository = noteRepository;
        this.authorRepository = authorRepository;
    }

    public NoteResponse createNote(NoteRequest noteRequest) {
        Author author = authorRepository.findById(noteRequest.authorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id " + noteRequest.authorId() + " does not exist."));
        Note note = noteRepository.save(new Note(noteRequest.title(), noteRequest.content(), LocalDateTime.now(), author));
        return mapToResponse(note);
    }

    public List<NoteResponse> getAllNotes() {
        return noteRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public NoteResponse getNoteById(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with id " + id + " does not exist."));
        return mapToResponse(note);
    }

    public void deleteNoteById(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with id " + id + " does not exist."));
        noteRepository.delete(note);
    }

    private NoteResponse mapToResponse(Note note){
        return new NoteResponse(
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getAuthor());
    }


}
