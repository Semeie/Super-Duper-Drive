package com.udacity.jwdnd.course1.cloudstorage.Controller;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private NoteService noteService;
    private CredentialService credentialService;
    private FileService fileService;
    public static String status;
    private EncryptionService encryptionService;
    private UserService userService;

    public HomeController(NoteService noteService, CredentialService credentialService, FileService fileService, EncryptionService encryptionService, UserService userService) {
        this.encryptionService = encryptionService;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping
    public String getHomePage(Model model, Authentication authentication) {
        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        addAttributes(model,userId,"files","HOME PAGE");
        return  "home";
    }

    public void addAttributes(Model model, Integer userId,String activeTab,String message) {

        model.addAttribute("credentials",this.credentialService.findAllCred(userId));
        model.addAttribute("files",this.fileService.findAllFiles(userId));
        model.addAttribute("notes", this.noteService.findAllNotes(userId));
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("activeTab", activeTab);
        model.addAttribute("message", message);
    }
}
