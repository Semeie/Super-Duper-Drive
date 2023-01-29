package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @PostConstruct
    public void postConstruct (){
        System.out.println("Creating FileService bean");
    }

    public List<File> findAllFiles(Integer userId){
        return fileMapper.getFilesByUser(userId);
    }

    public File getFile(Integer fileId){
        return fileMapper.getByFile(fileId);
    }


    public File getFileByFileName(String fileName){
        return fileMapper.getFileByFileName(fileName);
    }

    public void upLoad(File file ){
        fileMapper.uploadFile(file);
    }

    public File getFileById(Integer fileId) {
        return fileMapper.getByFile(fileId);
    }

    public int delete(Integer fileId) {
        return fileMapper.delete(fileId);
    }
}
