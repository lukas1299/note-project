package com.example.note.controller;

import com.example.note.request.NoteRequest;
import com.example.note.response.NoteResponse;
import com.example.note.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@RequestBody NoteRequest noteRequest){
        return ResponseEntity.ok(noteService.createNote(noteRequest));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes(){
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id){
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id){
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
}
