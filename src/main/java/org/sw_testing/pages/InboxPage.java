package org.sw_testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InboxPage extends BasePage {
    @FindBy(xpath = "//div[@id='menu']/div[@class='leftFloat']/ul/li[2]/a")
    private WebElement newMessageButton;
    @FindBy(xpath = "//div[@id='menu']/div[@class='leftFloat']/ul/li[3]/a")
    private WebElement foldersButton;
    public InboxPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//html/frameset/frame[2]")));
    }

    public NewMessagePage NewMessage() {
        newMessageButton.click();
        return PageFactory.initElements(driver, NewMessagePage.class);
    }

    public FoldersPage NavigateToFolders() {
        foldersButton.click();
        return PageFactory.initElements(driver, FoldersPage.class);
    }
}
