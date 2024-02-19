package com.example.blogapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public String sendPasswordResetEmail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("orxanrustamov1827@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
        return email;
    }
}
