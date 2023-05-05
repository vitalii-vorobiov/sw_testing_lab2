package org.sw_testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sw_testing.models.Mail;
import org.sw_testing.models.MainMenu;

public class MailPage extends BasePage {
    @FindBy(xpath = "//div[@id='msgheaders']/table/tbody/tr[3]/td[2]/span/a")
    private WebElement to;
    @FindBy(xpath = "//div[@id='msgheaders']/table/tbody/tr[4]/td[2]")
    private WebElement subject;
    @FindBy(xpath = "//div[@id='messageBody']/table/tbody/tr[1]/td[1]/div")
    private WebElement message;
    @FindBy(xpath = "//div[@id='menu']/div[@class='leftFloat']/ul/li[3]/a")
    private WebElement foldersButton;

    public MainMenu mainMenu;

    public MailPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//html/frameset/frame[2]")));

        mainMenu = PageFactory.initElements(driver, MainMenu.class);
    }

    public Mail GetMail() {
        return new Mail(to.getText(), subject.getText(), message.getText());
    }

    public FoldersPage NavigateToFolders() {
        foldersButton.click();
        return PageFactory.initElements(driver, FoldersPage.class);
    }
}
