package com.flightfinder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final String username;
    private final String password;
    @Value("${email.username")
    String from;

    public EmailService(@Value("${email.username}") String username, @Value("${email.password}") String password) {
        this.username = username;
        this.password = password;
    }

    public void sendEmail(String recipientEmailId, String origin, String destination, Double currentPrice,
                          String currency, String departureDate, String airline) {

        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }

        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmailId));
            message.setSubject("Flight price reduced!");
            message.setText("The price of " + airline + " from " + origin + " to " + destination + " has been reduced" +
                    " to " + currency +
                    " " + currentPrice + " for date " + departureDate + "." +
                    " Book now to get the flights at this reduced price");

            logger.info("sending...");
            Transport.send(message);
            logger.info("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
