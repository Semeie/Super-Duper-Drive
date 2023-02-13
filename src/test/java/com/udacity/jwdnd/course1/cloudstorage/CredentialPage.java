package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialPage {
    @FindBy(id = "nav-credentials-tab")
    WebElement navCredential;

    @FindBy(id = "add-credential")
    WebElement addCredential;

    @FindBy(id = "credential-url")
    WebElement credentialUrl;

    @FindBy(id = "credential-username")
    WebElement credentialUsername;

    @FindBy(id = "credential-password")
    WebElement credentialPassword;

    @FindBy(id = "credential-save")
    WebElement credentialSave;

    @FindBy(id = "credential-edit")
    WebElement credentialEdit;

    @FindBy(id = "credential-delete")
    WebElement credentialDelete;

    public CredentialPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void credentialNote(String url, String userName, String password) throws InterruptedException {
        credentialUrl.clear();
        credentialUsername.clear();
        credentialPassword.clear();

        credentialUrl.sendKeys(url);
        credentialUsername.sendKeys(userName);
        credentialPassword.sendKeys(password);
    }
//    public boolean checkCredentialPresent(String url, String userName, String password){
//        List<WebElement> webElementList=credentialTable.findElements(By.tagName("th"));
//        boolean credentialPresent=false;
//
//        if(webElementList.size()>=5){
//            WebElement thTitleName=webElementList.get(4);
//            //The innerHTML is an attribute of a WebElement which is equal to the content that is present between the starting and ending tag.
//            String urlforCred= thTitleName.getAttribute("innerHTML");
//            List<WebElement> credentialTableElements=credentialTable.findElements(By.tagName("td"));
//            String username=credentialTableElements.get(1).getAttribute("innerHTML");
//            credentialPresent = urlforCred.equals(url)&&username.equals(userName);//&&password1.equals(password);
//        }
//        return credentialPresent;
//    }

}
