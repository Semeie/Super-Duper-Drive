package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

import static com.udacity.jwdnd.course1.cloudstorage.Utils.Constants.*;

@Controller
@RequestMapping("/home/files")
public class FileController {
    private UserService userService;
    private FileService fileService;
    private HomeController homeController;

    public FileController(UserService userService, FileService fileService, HomeController homeController) {
        this.userService = userService;
        this.fileService = fileService;
        this.homeController = homeController;
    }

    @PostMapping
    public String uploadFile(Authentication authentication, MultipartFile file, @ModelAttribute("files") File fileX, Model model) {

        Integer userId = userService.getUserByName(authentication.getName()).getUserId();


        try {

            if (file.isEmpty()) {
                model.addAttribute("errorMsg",ERROR_FILE_EMPTY);
                throw new IOException(ERROR_FILE_EXCEPTION);
            }

            if (file.getSize() > 100000000) {
                model.addAttribute("errorMsg",ERROR_FILE_SIZE);
                throw new IOException(ERROR_FILE_SIZE);
            }

            if (this.fileService.getFileByFileName(file.getOriginalFilename()) != null) {
                model.addAttribute("errorMsg",ERROR_FILE_ALREADY_EXISTS);
                throw new IOException(ERROR_FILE_ALREADY_EXISTS);
            }
            // Convert MultipartFile to this project File
            fileX.setFileName(file.getOriginalFilename());
            fileX.setContentType(file.getContentType());
            fileX.setFileSize(file.getSize());
            fileX.setFileData(file.getBytes());
            fileX.setUserId(userId);
            this.fileService.upLoad(fileX);
//            String msg = "You successfully uploaded '" + fileX.getFileName() + "' !";
            model.addAttribute("success",SUCCESS_FILE_UPLOAD);
        } catch (IOException e) {
        }

        homeController.addAttributes(model,userId,"files");
        return "home";
    }


    @RequestMapping("/view/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(Authentication authentication, @PathVariable("fileId") Integer fileId, Model model) throws SQLException {


        Integer userId = userService.getUserByName(authentication.getName()).getUserId();

        homeController.addAttributes(model, userId,"files");

        File file = fileService.getFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getFileData()));
    }


    @GetMapping("/delete/{fileId}")
    public String deleteFile(Authentication authentication, @PathVariable Integer fileId, Model model) {

        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        int deletedFile = fileService.delete(fileId);

        if (deletedFile >= 1) {
            model.addAttribute("success",SUCCESS_FILE_DELETE);
        } else {
           model.addAttribute("errorMsg", ERROR_FILE_DELETE);
        }
        homeController.addAttributes(model, userId,"files");
        return "home";
    }
}
