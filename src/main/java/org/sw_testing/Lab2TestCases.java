package org.sw_testing;

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

//    @Test
//    public void Test1() {
//        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
//
//        InboxPage inboxPage = loginPage.Login(properties.get("mail.username").toString(), properties.get("mail.password").toString());
//
//        NewMessagePage newMessagePage = inboxPage.NewMessage();
//        newMessagePage.FillTo(properties.get("mail.to").toString());
//        newMessagePage.FillSubject(properties.get("mail.subject").toString());
//        newMessagePage.FillMessage(properties.get("mail.text").toString());
//        inboxPage = newMessagePage.Send();
//    }

    @Test
    public void Test() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

        InboxPage inboxPage = loginPage.Login(properties.get("mail.username").toString(), properties.get("mail.password").toString());

//        NewMessagePage newMessagePage = inboxPage.NewMessage();
//
//        newMessagePage.FillTo(properties.get("mail.to").toString());
//        newMessagePage.FillSubject(properties.get("mail.subject").toString());
//        newMessagePage.FillMessage(properties.get("mail.message").toString());
//
//        inboxPage = newMessagePage.Send();
//
//        FoldersPage foldersPage = inboxPage.NavigateToFolders();
//        SentPage sentPage = foldersPage.NavigateToSent();
//        MailPage mailPage = sentPage.NavigateToLatestSentMail();
//        Mail sentMail = mailPage.GetMail();
//
//        Assert.assertEquals(sentMail.GetTo(), properties.getProperty("mail.to"));
//        Assert.assertEquals(sentMail.GetSubject(), properties.getProperty("mail.subject"));
//        Assert.assertEquals(sentMail.GetMessage(), properties.getProperty("mail.message"));

        FoldersPage foldersPage = inboxPage.NavigateToFolders();
        SentPage sentPage = foldersPage.NavigateToSent();
        sentPage.DeleteLatestSentMail();

    }
}
