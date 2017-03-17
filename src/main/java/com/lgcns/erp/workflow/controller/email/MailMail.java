package com.lgcns.erp.workflow.controller.email;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailMail
{
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
		int i=0;

		if(idTo.length != 0) {
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

	public void sendHtmlMail(int[] idTo, String subject, String msg) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			String[] sentTo = new String[idTo.length];
			int i=0;

			if(idTo.length != 0) {
				for (int userId :
						idTo) {
					sentTo[i++] = UserService.getUserById(userId).geteMail();
				}
				message.setSubject(subject);
				MimeMessageHelper helper;
				helper = new MimeMessageHelper(message, true);
				helper.setFrom("subscription@lgcns.uz");
				helper.setTo(sentTo);
				helper.setText(msg, true);
				javaMailSender.send(message);
			}
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
	}
}
