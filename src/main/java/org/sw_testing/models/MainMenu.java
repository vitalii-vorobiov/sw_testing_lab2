package org.sw_testing.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sw_testing.pages.BasePage;
import org.sw_testing.pages.FoldersPage;
import org.sw_testing.pages.InboxPage;
import org.sw_testing.pages.NewMessagePage;

public class MainMenu extends BasePage {
    @FindBy(xpath = "//div[@id='menu']/div[@class='leftFloat']/ul/li[1]/a")
    protected WebElement inboxButton;
    @FindBy(xpath = "//div[@id='menu']/div[@class='leftFloat']/ul/li[2]/a")
    protected WebElement newMessageButton;
    @FindBy(xpath = "//div[@id='menu']/div[@class='leftFloat']/ul/li[3]/a")
    protected WebElement foldersButton;

    public MainMenu(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//html/frameset/frame[2]")));
    }

    public InboxPage NavigateToInboxPage() {
        inboxButton.click();
        return PageFactory.initElements(driver, InboxPage.class);
    }

    public NewMessagePage NavigateToNewMessagePage() {
        newMessageButton.click();
        return PageFactory.initElements(driver, NewMessagePage.class);
    }

    public FoldersPage NavigateToFoldersPage() {
        foldersButton.click();
        return PageFactory.initElements(driver, FoldersPage.class);
    }
}
