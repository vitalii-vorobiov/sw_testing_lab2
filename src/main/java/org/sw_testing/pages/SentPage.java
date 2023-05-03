package org.sw_testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentPage extends BasePage {
    @FindBy(xpath = "//table[@class='messageList']/tbody/tr[2]/td[5]/div/a")
    private WebElement latestSentMailButton;
    @FindBy(xpath = "//table[@class='messageList']/tbody/tr[2]/td/div/label/input")
    private WebElement latestSentMailCheck;
    @FindBy(xpath = "//body/table[3]/tbody/tr/td/div[@class='leftFloat']/ul/li[1]/a")
    private WebElement deleteButton;

    public SentPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//html/frameset/frame[2]")));
    }

    public MailPage NavigateToLatestSentMail() {
        latestSentMailButton.click();
        return PageFactory.initElements(driver, MailPage.class);
    }

    public void DeleteLatestSentMail() {
        latestSentMailCheck.click();
        deleteButton.click();
    }
}
