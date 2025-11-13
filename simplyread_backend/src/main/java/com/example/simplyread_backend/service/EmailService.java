package com.example.simplyread_backend.service;

import com.example.simplyread_backend.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendOrderConfirmationEmail(String toEmail, String subject, String body) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("kalanamalshan98@gmail.com");
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            System.out.println("Email sent Successfully to: " + toEmail);
        }catch (Exception e){
            System.out.println("Email sent Failed to: " + toEmail);
        }

    }
}
