package org.sw_testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewMessagePage extends BasePage {
    @FindBy(xpath = "//input[@name='to']")
    private WebElement toField;
    @FindBy(xpath = "//input[@name='subject']")
    private WebElement subjectField;
    @FindBy(xpath = "//textarea[@name='message']")
    private WebElement messageField;
    @FindBy(xpath = "//input[@name='btn_send_message']")
    private WebElement sendButton;
    @FindBy(xpath = "//input[@name='btn_save_draft']")
    private WebElement saveDraftButton;

    public NewMessagePage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//html/frameset/frame[2]")));
    }

    public void FillTo(String to) {
        toField.sendKeys(to);
    }

    public void FillSubject(String subject) {
        subjectField.sendKeys(subject);
    }

    public void FillMessage(String message) {
        messageField.sendKeys(message);
    }

    public InboxPage Send() {
        sendButton.click();
        return PageFactory.initElements(driver, InboxPage.class);
    }

    public InboxPage SaveDraft() {
        saveDraftButton.click();
        return PageFactory.initElements(driver, InboxPage.class);
    }
}
