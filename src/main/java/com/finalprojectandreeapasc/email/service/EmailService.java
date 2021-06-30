package com.finalprojectandreeapasc.email.service;

import com.finalprojectandreeapasc.recipes.mapper.RecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    public void sendMail(String email) throws UnsupportedEncodingException {

        String decode = java.net.URLDecoder.decode(email, StandardCharsets.UTF_8.name()).replace("=", "");

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("pastryshopproject@gmail.com", "PastryShopPass1!");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pastryshopproject@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(decode));
            message.setSubject("Newsletter");

            String msg = "Thank for subscribing to our newsletter!";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File("photo.jpg"));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}