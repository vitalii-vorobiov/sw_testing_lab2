package org.sw_testing.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Mail {
    private final String to;
    private final String subject;
    private final String message;

    public Mail(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public Mail(WebElement mailWebElement) {
        this.to = "";
        this.subject = "";
        this.message = "";

        System.out.println(mailWebElement.findElement(By.xpath("td[5]")).getText());;
    }

    public String GetTo() {
        return this.to;
    }

    public String GetSubject() {
        return this.subject;
    }

    public String GetMessage() {
        return this.message;
    }
}
