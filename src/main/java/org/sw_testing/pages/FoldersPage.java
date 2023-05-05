package org.sw_testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sw_testing.models.MainMenu;

public class FoldersPage extends BasePage {
    @FindBy(xpath = "//form[@name='fmanager']/div[1]/a")
    private WebElement inboxButton;
    @FindBy(xpath = "//form[@name='fmanager']/div[2]/a")
    private WebElement draftsButton;
    @FindBy(xpath = "//form[@name='fmanager']/div[3]/a")
    private WebElement sentButton;
    @FindBy(xpath = "//form[@name='fmanager']/div[4]/a")
    private WebElement spamButton;
    @FindBy(xpath = "//form[@name='fmanager']/div[5]/a")
    private WebElement trashButton;

    public MainMenu mainMenu;

    public FoldersPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//html/frameset/frame[2]")));

        this.mainMenu = PageFactory.initElements(driver, MainMenu.class);
    }

    public InboxPage NavigateToInbox() {
        inboxButton.click();
        return PageFactory.initElements(driver, InboxPage.class);
    }

    public DraftsPage NavigateToDrafts() {
        draftsButton.click();
        return PageFactory.initElements(driver, DraftsPage.class);
    }

    public SentPage NavigateToSent() {
        sentButton.click();
        return PageFactory.initElements(driver, SentPage.class);
    }

    public SpamPage NavigateToSpam() {
        spamButton.click();
        return PageFactory.initElements(driver, SpamPage.class);
    }

    public TrashPage NavigateToTrash() {
        trashButton.click();
        return PageFactory.initElements(driver, TrashPage.class);
    }
}
