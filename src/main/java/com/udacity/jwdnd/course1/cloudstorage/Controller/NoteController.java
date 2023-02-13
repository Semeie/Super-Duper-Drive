package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.udacity.jwdnd.course1.cloudstorage.Utils.Constants.ERROR_NOTE_DELETE;
import static com.udacity.jwdnd.course1.cloudstorage.Utils.Constants.SUCCESS_NOTE_DELETE;

@Controller
@RequestMapping("/home/note")
public class NoteController {
    private UserService userService;
    private NoteService noteService;
    private HomeController homeController;

    public NoteController(UserService userService, NoteService noteService, HomeController homeController) {
        this.userService = userService;
        this.noteService = noteService;
        this.homeController = homeController;
    }

    @PostMapping
    public String postNote(Authentication authentication, @ModelAttribute("notes") Note note, Model model) {


        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        note.setUserId(userId);
        this.noteService.addNote(note);
        String msg = "Note successfully "+ HomeController.status +" !";
        model.addAttribute("success", msg);
        homeController.addAttributes(model,userId,"notes");

        return "home";
    }


    @GetMapping("/delete/{noteId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer noteId, Model model) {


        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        int deletedNote = noteService.delete(noteId);

        if (deletedNote >= 1) {
            model.addAttribute("success",SUCCESS_NOTE_DELETE);
        } else {
            model.addAttribute("errorMsg", ERROR_NOTE_DELETE);
        }
        homeController.addAttributes(model,userId,"notes");

        return "home";
    }
}
