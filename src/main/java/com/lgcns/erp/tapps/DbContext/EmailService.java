package com.lgcns.erp.tapps.DbContext;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.lgcns.erp.workflow.Mapper.AuthTokenMapper;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.joda.time.DateTime;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * Created by Muslimbek on 5/2/2017.
 */
public class EmailService {
    public static String generateToken(int userId, String redirectUrl) {
        String token = null;
        String secret = null;
        try {
            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            secret = bytes.toString();

            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("redirectUrl", redirectUrl)
                    .sign(algorithm);
            System.out.println("TOKEN: " + token);
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        DateTime expireDate = new DateTime().plusHours(168);
            /* Adding new token and expire date to DB */
        UserService.insertAuthToken(AuthTokenMapper.mapAuthToken(userId, token, expireDate, secret));
        return token;
    }

    public static void sendHtmlMail(int idTo, String subject, String msg) throws IOException {

        Properties props = new Properties();
        /*InputStream inputStream = EmailService.class.getResourceAsStream("app.properties");
        props.load(inputStream);*/
        props.put("mail.smtp.host", "mail.lgcns.uz");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtps.ssl.checkserveridentity", "true");
        props.put("mail.smtp.ssl.trust", "mail.lgcns.uz");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("subscription@lgcns.uz", "PkGv4614yBWn");
                    }
                });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("subscription@lgcns.uz"));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(UserService.getUserById(idTo).geteMail()));
                    message.setSubject(subject);
                    message.setContent(msg, "text/html");

                    Transport.send(message);

                    System.out.println("Done");

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


}
