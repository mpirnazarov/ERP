package com.lgcns.erp.workflow.controller.email;

import com.lgcns.erp.tapps.DbContext.UserService;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailMail
{
	private MailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
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
}
