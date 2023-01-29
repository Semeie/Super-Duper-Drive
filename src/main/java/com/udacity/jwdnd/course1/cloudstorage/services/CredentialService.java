package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Controller.HomeController;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating CredentialService bean");
    }

    public Credential getCredential(Integer credentialId) {
        Credential credential = (Credential) credentialMapper.getCreById(credentialId);
        String encryptedPassword = credential.getPassword();
        String key = credential.getKey();
        String plainTextPassword = encryptionService.decryptValue(encryptedPassword, key);
        credential.setPassword(plainTextPassword);
        return credential;
    }

    public int addCredential(Credential credential) {

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), encodedKey));
        credential.setKey(encodedKey);

        if (credential.getCredentialId() == null) {
            HomeController.status = "added";
            return credentialMapper.addCredential(credential);
        } else {
            HomeController.status = "updated";
            return credentialMapper.update(credential);
        }

    }

    public List<Credential> findAllCred(int userId) {
        return credentialMapper.findCredByUser(userId);
    }

    public Credential findCredById(Integer credentialId) {
        return credentialMapper.getCreById(credentialId);
    }

    public int delete(Integer credentialId) {
        return credentialMapper.delete(credentialId);
    }

    public String getDecryptedCredentials(Integer credentialId) {
        String decryptedPassword = credentialMapper.getDecryptedPassword(credentialId);
        return decryptedPassword;}
}
