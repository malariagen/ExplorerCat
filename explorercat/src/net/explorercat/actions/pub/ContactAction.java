package net.explorercat.actions.pub;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import net.explorercat.application.ApplicationPropertyLookup;
import net.explorercat.util.misc.MailUtility;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ContactAction extends ActionSupport {

	private String name;
	private String email;
	private String message;
	private MailUtility mailUtility;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String execute() throws Exception {

		ApplicationPropertyLookup lookup = ApplicationPropertyLookup
				.getInstance();
		mailUtility = new MailUtility();
		mailUtility.setTo(lookup.getGlobalProperty("feedback.mail.to"));
		mailUtility
				.setHost(lookup.getGlobalProperty("feedback.mail.smtp.host"));
		mailUtility
				.setPort(lookup.getGlobalProperty("feedback.mail.smtp.port"));
		mailUtility.setSubject(lookup
				.getGlobalProperty("feedback.mail.subject"));
		mailUtility.setFrom(getEmail());
		mailUtility.setMsgText(getMessage() + "\nFrom: " + getName());

		if (mailUtility.sendMail()) {
			return SUCCESS;
		} else {
			return ERROR;
		}

	}
}
