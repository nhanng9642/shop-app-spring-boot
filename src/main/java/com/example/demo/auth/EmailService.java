package com.example.demo.auth;

import com.example.demo.response.ApiResponse;
import io.swagger.v3.core.util.OpenAPI30To31;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static org.antlr.v4.runtime.misc.Utils.readFile;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final String EMAIL_FROM = "nhann9642@gmail.com";
    private final String EMAIL_RESET_PW = "src/main/resources/templates/send-link.html";

    public void sendEmail(String to, String subject, String template,
                                 Map<String, String> map) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(EMAIL_FROM));
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);

        String htmlTemplate = String.valueOf(readFile(template));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            htmlTemplate = htmlTemplate.replace("[" + entry.getKey() + "]", entry.getValue());
        }

        message.setContent(htmlTemplate, "text/html; charset=utf-8");
        mailSender.send(message);
    }

    public void sendResetLink(String to, String link) throws MessagingException, IOException {
        sendEmail(to, "Reset Password", EMAIL_RESET_PW, Map.of("link", link));
    }

    public void sendVerifyEmail(String to, String link) throws MessagingException, IOException {
        sendEmail(to, "Verify Email", EMAIL_RESET_PW, Map.of("link", link));
    }

}
