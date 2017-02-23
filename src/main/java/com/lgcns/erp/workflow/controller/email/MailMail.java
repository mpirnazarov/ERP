package com.lgcns.erp.workflow.controller.email;

import com.lgcns.erp.tapps.DbContext.UserService;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.security.Principal;

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

	public void sendMail(String from, String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}


	public void sendMailApproval(int[] approvalsGlobal, Principal principal) {
		SimpleMailMessage message = new SimpleMailMessage();
		String[] sentTo = new String[approvalsGlobal.length];
		int i=0;
		for (int userId :
				approvalsGlobal) {
			sentTo[i++] = UserService.getUserById(userId).geteMail();
		}
		message.setFrom(UserService.getUserByUsername(principal.getName()).geteMail());
		message.setTo(sentTo);
		message.setSubject("Subject");
		message.setText("Text is send");
		mailSender.send(message);
	}

	public void sendMailReference(int[] referencesGlobal, Principal principal) {
		SimpleMailMessage message = new SimpleMailMessage();
		String[] sentTo = new String[referencesGlobal.length];
		int i=0;
		for (int userId :
				referencesGlobal) {
			sentTo[i++] = UserService.getUserById(userId).geteMail();
		}
		message.setFrom(UserService.getUserByUsername(principal.getName()).geteMail());
		message.setTo(sentTo);
		message.setSubject("Subject");
		message.setText("Text is send");
		mailSender.send(message);
	}

	public void sendMailExecutive(int[] executivesGlobal, Principal principal) {
		SimpleMailMessage message = new SimpleMailMessage();
		String[] sentTo = new String[executivesGlobal.length];
		int i=0;
		for (int userId :
				executivesGlobal) {
			sentTo[i++] = UserService.getUserById(userId).geteMail();
		}
		message.setFrom(UserService.getUserByUsername(principal.getName()).geteMail());
		message.setTo(sentTo);
		message.setSubject("Subject");
		message.setText("Text is send");
		mailSender.send(message);
	}
}
