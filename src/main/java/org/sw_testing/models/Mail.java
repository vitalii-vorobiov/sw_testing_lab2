package org.sw_testing.models;

public class Mail {
    private final String to;
    private final String subject;
    private final String message;

    public Mail(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
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
