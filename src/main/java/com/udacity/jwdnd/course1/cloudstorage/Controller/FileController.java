package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
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
@Controller
@RequestMapping("/home/files")
public class FileController {
    private UserService userService;
    private FileService fileService;
    private HomeController homeController;
    private String message;


    public FileController(UserService userService, FileService fileService, HomeController homeController) {
        this.userService = userService;
        this.fileService = fileService;
        this.homeController = homeController;
    }

    @PostMapping
    public String upload(Authentication authentication, MultipartFile file, @ModelAttribute("files") File file1, Model model) {

        Integer userId = userService.getUserByName(authentication.getName()).getUserId();


        try {

            if (file.isEmpty()) {
                message = "Please select a file!";
                throw new IOException("File Not FoundException");
            }

            if (file.getSize() > 100000000) {
                message = "File size larger than size limit";
                model.addAttribute("fileSizeError","File size larger than size limit");
                throw new IOException("Error: File size larger than size limit");
            }

            if (this.fileService.getFileByFileName(file.getOriginalFilename()) != null) {
                message = file.getOriginalFilename() + " exists in the database!!! Please upload another file!";
                throw new IOException("File name exists");
            }


            // Converting MultipartFile this projects File

            file1.setFileName(file.getOriginalFilename());
            file1.setContentType(file.getContentType());
            file1.setFileSize(file.getSize());
            file1.setFileData(file.getBytes());
            file1.setUserId(userId);
            this.fileService.upLoad(file1);
            message = "You successfully uploaded '" + file1.getFileName() + "' !";
        } catch (IOException e) {
        }

        homeController.addAttributes(model,userId,"files",message);
        return "home";
    }


    @RequestMapping("/view/{fileId}")
    public ResponseEntity downloadFile(Authentication authentication, @PathVariable("fileId") Integer fileId, Model model) throws SQLException {


        Integer userId = userService.getUserByName(authentication.getName()).getUserId();

        homeController.addAttributes(model, userId,"files",message);

        File file = fileService.getFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getFileData());
    }


    @GetMapping("/delete/{fileId}")
    public String deleteFile(Authentication authentication, @PathVariable Integer fileId, Model model) {

        Integer userId = userService.getUserByName(authentication.getName()).getUserId();
        int result = fileService.delete(fileId);

        if (result >= 1) {
            message = "File successfully deleted!";
        } else {
           message = "Deleting the File was unsuccessfully!";
        }
        homeController.addAttributes(model, userId,"files",message);
        return "redirect:/home";
    }
}
