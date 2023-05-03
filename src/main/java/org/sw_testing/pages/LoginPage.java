package org.sw_testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='imapuser']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@id='pass']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@id='loginButton']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get("https://web-mail.uibk.ac.at/");
    }

    public InboxPage Login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return PageFactory.initElements(driver, InboxPage.class);
    }
}
