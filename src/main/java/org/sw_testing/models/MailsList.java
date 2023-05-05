package org.sw_testing.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.sw_testing.pages.BasePage;
import org.sw_testing.pages.MailPage;
import org.sw_testing.pages.NewMessagePage;

import java.util.List;

public class MailsList extends BasePage {
    @FindBy(xpath = "//table[@class='messageList']/tbody/tr")
    private List<WebElement> mails;
    @FindBy(xpath = "//body/table[3]/tbody/tr/td/div[@class='leftFloat']/ul/li[1]")
    private WebElement deleteButton;
    @FindBy(xpath = "//body/table[3]/tbody/tr/td/div[@class='leftFloat']/ul/li[2]")
    private WebElement undeleteButton;
    @FindBy(xpath = "//body/table[2]/tbody/tr/td/div[2]/form/ul/li/a")
    private WebElement moveToButton;
    @FindBy(xpath = "//select[@id='flag1']")
    private WebElement markAsSelect;
    @FindBy(xpath = "//select[@id='targetMailbox1']")
    private WebElement moveToSelect;

    public MailsList(WebDriver driver) {
        super(driver);

        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//html/frameset/frame[2]")));
    }

    public WebElement GetMail(int index) {
        if (index >= mails.size()) {
            throw new IndexOutOfBoundsException("Mail index: " + index + " is out of bounds");
        }
        return mails.get(index);
    }

    public MailPage OpenMail(int index) {
        var mail = GetMail(index);
        mail.findElement(By.xpath("td[5]/div/a")).click();
        return PageFactory.initElements(driver, MailPage.class);
    }

    public NewMessagePage OpenDraft(int index) {
        var mail = GetMail(index);
        mail.findElement(By.xpath("td[5]/div/a")).click();
        return PageFactory.initElements(driver, NewMessagePage.class);
    }

    public void SelectMail(int index) {
        if (index < 1 || index >= mails.size()) {
            throw new IndexOutOfBoundsException("Mail index: " + index + " is out of bounds");
        }
        mails.get(index).findElement(By.xpath("td[1]/div/label/input")).click();
    }

    public void DeleteSelected() {
        deleteButton.click();
    }

    public void MoveSelected() {
        moveToButton.click();
    }

    public void SelectOption(String value) {
        var select = new Select(markAsSelect);
        select.selectByValue(value);
    }

    public void SelectMoveLocation(String value) {
        var select = new Select(moveToSelect);
        select.selectByValue(value);
    }
}
