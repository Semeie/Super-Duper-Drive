package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Controller.HomeController;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating NoteService bean");
    }

    public List<Note> findAllNotes(Integer userId){
        return noteMapper.findByUser(userId);
    }

    public void addNote(Note note) {

        if (note.getNoteId() == null) {
            HomeController.status = "added";
            noteMapper.addNote(note);
        } else {
            HomeController.status = "updated";
            noteMapper.update(note);
        }
    }

    public int delete(Integer noteId){
        return noteMapper.delete(noteId);
    }
}
