package com.app.epolice.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailNotification {

    private JavaMailSender javaMailSender;
    public EmailNotification(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * This method is using accepting email ID and message to send mail through JavaMailSender(SMTP)
     * @Author "Kamran"
     * @CreatedDate "14-10-2021
     * @param email
     * @param message
     */
    public void sendMail(String email, String message) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email, email);
        msg.setSubject("Account Verification Token");
        msg.setText(message);
        javaMailSender.send(msg);
    }
}
