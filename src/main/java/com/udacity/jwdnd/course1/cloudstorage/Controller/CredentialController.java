package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home/credential")
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;
    private HomeController homeController;
    private String message;

    public CredentialController(CredentialService credentialService, UserService userService, HomeController homeController) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.homeController = homeController;
    }

    @PostMapping
    public String postCredential(Authentication authentication, Credential credential, Model model) {

        Integer userId = userService.getUserByName(authentication.getName()).getUserId();

        if(credential.getUserId() ==null){
            credential.setUserId(userId);
        }
        this.credentialService.addCredential(credential);
        message = "Credential"+" successfully "+ HomeController.status +" !";
        model.addAttribute("success", message);
        homeController.addAttributes(model,userId,"credentials");

        return "home";
    }

    @GetMapping("/{credentialId}")
    @ResponseBody
    public Credential getCredential(Authentication authentication,@PathVariable(name = "credentialId") String credentialID ) {
        Integer credentialId = Integer.parseInt(credentialID);
        return credentialService.getCredential(credentialId);
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer credentialId, Model model) {
        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        int result = credentialService.delete(credentialId);

        if (result >= 1) {
            message="Credential successfully deleted!";
            model.addAttribute("success", message);
        } else {
            message="Deleting the Credential was unsuccessfully!";
            model.addAttribute("errorMessage",message);
        }
        homeController.addAttributes(model,userId,"credentials");
        return "home";
    }
}
