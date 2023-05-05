package org.sw_testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.sw_testing.models.Mail;
import org.sw_testing.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Lab2TestCases {
    private Properties properties;
    private WebDriver driver;
    public Lab2TestCases() throws IOException {
        properties = new Properties();

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        if (in != null) {
            properties.load(in);
            in.close();

            System.setProperty("webdriver.chrome.driver", properties.get("webDriver.location").toString());

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
    }

    @Test
    public void Test1() {
        var loginPage = PageFactory.initElements(driver, LoginPage.class);
        var inboxPage = loginPage.Login(properties.get("mail.username").toString(), properties.get("mail.password").toString());
        NewMessagePage newMessagePage = inboxPage.mainMenu.NavigateToNewMessagePage();
        newMessagePage.FillTo(properties.get("mail.to").toString());
        newMessagePage.FillSubject(properties.get("mail.subject").toString());
        newMessagePage.FillMessage(properties.get("mail.message").toString());
        inboxPage = newMessagePage.Send();
        var foldersPage = inboxPage.mainMenu.NavigateToFoldersPage();
        var sentPage = foldersPage.NavigateToSent();
        var mailPage = sentPage.mailsList.OpenMail(1);
        var mail = mailPage.GetMail();
        Assert.assertEquals(properties.get("mail.to").toString(), mail.GetTo());
        Assert.assertEquals(properties.get("mail.subject").toString(), mail.GetSubject());
        Assert.assertEquals(properties.get("mail.message").toString(), mail.GetMessage());
        foldersPage = mailPage.NavigateToFolders();
        sentPage = foldersPage.NavigateToSent();
        sentPage.mailsList.SelectMail(1);
        sentPage.mailsList.DeleteSelected();
        sentPage.mailsList.SelectMail(1);
        sentPage.mailsList.SelectMoveLocation("Trash");
        sentPage.mailsList.MoveSelected();
    }

    @Test
    public void Test2() {
        var loginPage = PageFactory.initElements(driver, LoginPage.class);
        var inboxPage = loginPage.Login(properties.get("mail.username").toString(), properties.get("mail.password").toString());
        NewMessagePage newMessagePage = inboxPage.mainMenu.NavigateToNewMessagePage();
        newMessagePage.FillTo(properties.get("mail.to").toString());
        newMessagePage.FillSubject(properties.get("mail.subject").toString());
        newMessagePage.FillMessage(properties.get("mail.message").toString());
        inboxPage = newMessagePage.SaveDraft();
        var foldersPage = inboxPage.mainMenu.NavigateToFoldersPage();
        var draftsPage = foldersPage.NavigateToDrafts();
        newMessagePage = draftsPage.mailsList.OpenDraft(1);
        Assert.assertEquals(properties.get("mail.to").toString(), newMessagePage.GetTo());
        Assert.assertEquals(properties.get("mail.subject").toString(), newMessagePage.GetSubject());
        Assert.assertEquals(properties.get("mail.message").toString(), newMessagePage.GetMessage());
        newMessagePage.Send();
    }

    @Test
    public void Test3() {
        var loginPage = PageFactory.initElements(driver, LoginPage.class);
        var inboxPage = loginPage.Login(properties.get("mail.username").toString(), properties.get("mail.password").toString());
        var mailPage = inboxPage.mailsList.OpenMail(1);
        var mail1 = mailPage.GetMail();
        var mail2 = mailPage.NavigateToFolders().NavigateToInbox().mailsList.OpenMail(2).GetMail();
        var mail3 = mailPage.NavigateToFolders().NavigateToInbox().mailsList.OpenMail(3).GetMail();
        inboxPage = mailPage.mainMenu.NavigateToInboxPage();
        inboxPage.mailsList.SelectMail(1);
        inboxPage.mailsList.SelectMail(2);
        inboxPage.mailsList.SelectMail(3);
        inboxPage.mailsList.SelectMoveLocation("Spam");
        inboxPage.mailsList.MoveSelected();
        var foldersPage = inboxPage.mainMenu.NavigateToFoldersPage();
        var spamPage = foldersPage.NavigateToSpam();
        mailPage = spamPage.mailsList.OpenMail(1);
        var mail = mailPage.GetMail();
        Assert.assertEquals(mail1.GetTo(), mail.GetTo());
        Assert.assertEquals(mail1.GetSubject(), mail.GetSubject());
        Assert.assertEquals(mail1.GetMessage(), mail.GetMessage());
        mail = mailPage.NavigateToFolders().NavigateToSpam().mailsList.OpenMail(2).GetMail();
        Assert.assertEquals(mail2.GetTo(), mail.GetTo());
        Assert.assertEquals(mail2.GetSubject(), mail.GetSubject());
        Assert.assertEquals(mail2.GetMessage(), mail.GetMessage());
        mail = mailPage.NavigateToFolders().NavigateToSpam().mailsList.OpenMail(3).GetMail();
        Assert.assertEquals(mail3.GetTo(), mail.GetTo());
        Assert.assertEquals(mail3.GetSubject(), mail.GetSubject());
        Assert.assertEquals(mail3.GetMessage(), mail.GetMessage());
        spamPage = mailPage.NavigateToFolders().NavigateToSpam();
        spamPage.mailsList.SelectMail(1);
        spamPage.mailsList.SelectMail(2);
        spamPage.mailsList.SelectMail(3);
        spamPage.mailsList.DeleteSelected();
        spamPage.mailsList.SelectMail(1);
        spamPage.mailsList.SelectMail(2);
        spamPage.mailsList.SelectMail(3);
        spamPage.mailsList.SelectMoveLocation("Trash");
        spamPage.mailsList.MoveSelected();
        var trashPage = spamPage.mainMenu.NavigateToFoldersPage().NavigateToTrash();
        mailPage = trashPage.mailsList.OpenMail(1);
        mail = mailPage.GetMail();
    }

    @Test
    public void Test4() {
        var loginPage = PageFactory.initElements(driver, LoginPage.class);
        var inboxPage = loginPage.Login(properties.get("mail.username").toString(), properties.get("mail.password").toString());
        NewMessagePage newMessagePage = inboxPage.mainMenu.NavigateToNewMessagePage();
        newMessagePage.FillTo(properties.get("mail.to_wrong").toString());
        newMessagePage.FillSubject(properties.get("mail.subject").toString());
        newMessagePage.FillMessage(properties.get("mail.message").toString());
        inboxPage = newMessagePage.Send();
        Assert.assertFalse(driver.findElements(By.xpath("//body/ul[@class='notices']")).isEmpty());
        newMessagePage.FillTo(properties.get("mail.to").toString());
        newMessagePage.FillSubject(properties.get("mail.subject").toString());
        newMessagePage.FillMessage(properties.get("mail.message").toString());
        inboxPage = newMessagePage.Send();
        var mail = inboxPage.mainMenu.NavigateToFoldersPage().NavigateToSent().mailsList.OpenMail(1).GetMail();
        Assert.assertEquals(mail.GetTo(), properties.get("mail.to").toString());
        Assert.assertEquals(mail.GetSubject(), properties.get("mail.subject").toString());
        Assert.assertEquals(mail.GetMessage(), properties.get("mail.message").toString());
    }
}
