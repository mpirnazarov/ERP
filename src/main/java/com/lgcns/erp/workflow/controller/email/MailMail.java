package com.lgcns.erp.workflow.controller.email;

import com.lgcns.erp.tapps.DbContext.UserService;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailMail {
    private MailSender mailSender;
    private JavaMailSender javaMailSender;
    private SimpleMailMessage simpleMailMessage;

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(int[] idTo, String subject, String msg) {


                SimpleMailMessage message = new SimpleMailMessage();
                String[] sentTo = new String[idTo.length];
                int i = 0;

                if (idTo.length != 0) {
                    for (int userId :
                            idTo) {
                        sentTo[i++] = UserService.getUserById(userId).geteMail();
                    }

                    message.setFrom("subscription@lgcns.uz");
                    message.setTo(sentTo);
                    message.setSubject(subject);
                    message.setText(msg);
                    mailSender.send(message);
                }
    }


    public void sendHtmlMail(int userId, String subject, String msg) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.lgcns.uz");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtps.ssl.checkserveridentity", "true");
        props.put("mail.smtp.ssl.trust", "mail.lgcns.uz");
        props.put("mail.smtp.port", "587");

        String sendTo =UserService.getUserById(userId).geteMail();
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("subscription@lgcns.uz","PkGv4614yBWn");
                    }
                });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("subscription@lgcns.uz"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(sendTo));
            message.setSubject(subject);
            message.setContent(msg, "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
