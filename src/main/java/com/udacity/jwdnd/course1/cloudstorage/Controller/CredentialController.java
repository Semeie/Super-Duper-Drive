package com.udacity.jwdnd.course1.cloudstorage.Controller;
import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.udacity.jwdnd.course1.cloudstorage.Utils.Constants.ERROR_CREDENTIAL_DELETE;
import static com.udacity.jwdnd.course1.cloudstorage.Utils.Constants.SUCCESS_CREDENTIAL_DELETE;

@Controller
@RequestMapping("/home/credential")
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;
    private HomeController homeController;

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
        String msg = "Credential successfully "+ HomeController.status +" !";
        model.addAttribute("success", msg);
        homeController.addAttributes(model,userId,"credentials");

        return "home";
    }

    @GetMapping("/{credentialId}")
    @ResponseBody
    public Credential getCredential(Authentication authentication,@PathVariable("credentialId") String credentialID ) {
        Integer credentialId = Integer.parseInt(credentialID);
        return credentialService.getCredential(credentialId);
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(Authentication authentication, @PathVariable Integer credentialId, Model model) {
        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        int deletedCredential = credentialService.delete(credentialId);

        if (deletedCredential >= 1) {
            model.addAttribute("success", SUCCESS_CREDENTIAL_DELETE);
        } else {
            model.addAttribute("errorMsg",ERROR_CREDENTIAL_DELETE);
        }
        homeController.addAttributes(model,userId,"credentials");
        return "home";
    }
}
