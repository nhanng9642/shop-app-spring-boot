package com.example.demo.auth;

import com.example.demo.response.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.antlr.v4.runtime.misc.Utils.readFile;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public ApiResponse sendResetLink(String to, String link) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("nhann9642@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("Reset your password!");

        // Read the HTML template into a String variable
        String htmlTemplate = String.valueOf(
                readFile("src/main/resources/templates/reset-pw.html"));

        // Replace placeholders in the HTML template with dynamic values
        htmlTemplate = htmlTemplate.replace("[link]", link);

        System.out.println(htmlTemplate);
        // Set the email's content to be the HTML template
        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        mailSender.send(message);
        return ApiResponse
                .builder()
                .success(true)
                .message("Email sent successfully!")
                .build();

    }

}
