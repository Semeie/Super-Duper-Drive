package com.udacity.jwdnd.course1.cloudstorage.Utils;

public final class Constants {
    // Success/Error Credential
    public static final String SUCCESS_CREDENTIAL_DELETE = "Credential successfully deleted!";
    public static final String ERROR_CREDENTIAL_DELETE = "Deleting the Credential was unsuccessfully!";
    public static final String SUCCESS_CREDENTIAL_CREATE = "Credential added successfully";
    public static final String SUCCESS_CREDENTIAL_UPDATE = "Credential updated successfully";


    // Success/Error File
    public static final String SUCCESS_FILE_UPLOAD = "File uploaded successfully";
    public static final String SUCCESS_FILE_DELETE = "File successfully deleted!";
    public static final String ERROR_FILE_DELETE = "Deleting the file was unsuccessfully!";
    public static final String ERROR_FILE_EMPTY = "Please select a file!";
    public static final String ERROR_FILE_SIZE = "File size exceeds limit";
    public static final String ERROR_FILE_ALREADY_EXISTS = "File name already exists! Please select another file!";
    public static final String ERROR_FILE_EXCEPTION = "File Not FoundException";

    // Success/Error Note
    public static final String SUCCESS_NOTE_DELETE = "Note successfully deleted!";
    public static final String SUCCESS_NOTE_CREATE = "Note added successfully";
    public static final String SUCCESS_NOTE_UPDATE = "Note successfully updated";
    public static final String ERROR_NOTE_DELETE = "Deleting the Note was unsuccessfully !";
    public static final String ERROR_NOTE_DUPLICATE = "This note already exists on database.";


    // Success/Error Signup
    public static final String ERROR_SIGNUP_GENERAL = "There was an error signing you up. Please try again.";
    public static final String ERROR_SIGNUP_USERNAME_ALREADY_EXISTS = "The username already exists";
    public static final String SUCCESS_SIGNUP = "You successfully signed up!";

    // Error General
    public static final String ERROR_GENERAL = "There was an error. Please try again.";

    // Error Credential
//    public static final String ERROR_CREDENTIAL_DUPLICATE = "This username/url already exists on database.";
//    public static final String LOCAL_HOST = "http://localhost:";
//    public static final String SIGNUP_SLASH = "/signup";
//    public static final String LOGIN_SLASH = "/login";
//    public static final String HOME_SLASH = "/home";
//    public static final String RESULT_SLASH = "/result";
//    public static final String HOME = "home";
//    public static final String LOGIN = "login";
//    public static final String SIGNUP = "signup";


    /**
     * The constructor is set as private so that the class can't be instantiated
     */
    private Constants() {
    }
}
