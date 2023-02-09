package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotePage {
    @FindBy(id = "nav-notes-tab")
    WebElement noteTab;

    @FindBy(id = "add-note")
    WebElement addNoteButton;

    @FindBy(id = "note-title")
    WebElement noteTitle;

    @FindBy(id = "note-description")
    WebElement noteDescription;

    @FindBy(id = "note-save")
    WebElement noteSubmit;

    @FindBy(id = "note-edit")
    WebElement noteEdit;

    @FindBy(id = "note-delete")
    WebElement noteDelete;

    public NotePage(WebDriver driver) { PageFactory.initElements(driver, this);}


    public void postNote(String title, String description) throws InterruptedException {
        noteTitle.clear();
        noteDescription.clear();
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
    }


}
