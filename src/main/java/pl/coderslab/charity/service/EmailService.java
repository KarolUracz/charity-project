package pl.coderslab.charity.service;

public interface EmailService {
    void simpleMessage(String to, String subject, String text);
    void sendConfirmationMail(String to, String token);
    void sendResetPasswordMail(String to, String token);
}
