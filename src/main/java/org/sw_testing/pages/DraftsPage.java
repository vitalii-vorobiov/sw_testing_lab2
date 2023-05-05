package org.sw_testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.sw_testing.models.MailsList;
import org.sw_testing.models.MainMenu;

public class DraftsPage extends BasePage{
    public MainMenu mainMenu;
    public MailsList mailsList;
    public DraftsPage(WebDriver driver) {
        super(driver);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//html/frameset/frame[2]")));

        this.mainMenu = PageFactory.initElements(driver, MainMenu.class);
        this.mailsList = PageFactory.initElements(driver, MailsList.class);
    }
}
