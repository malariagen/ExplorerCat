package net.explorercat.util.misc;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that uses Java Mail API to send user feedback email
 * 
 * @author Dushyanth Jyothi - dj6@sanger.ac.uk
 * @date 1 October 2012
 */

public class MailUtility {

	private String to;
	private String from;
	private String host;
	private String port;
	private String subject;
	private String msgText;
	private static final Log log = LogFactory.getLog(MailUtility.class);

	public void setTo(String to) {
		this.to = to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public String getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getSubject() {
		return subject;
	}

	public String getMsgText() {
		return msgText;
	}

	public boolean sendMail() {

		// create some properties and get the default Session
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.debug", "true");

		Session session = Session.getInstance(props, null);

		session.setDebug(true);

		try {
			log.info("Sendimg mail from: " + getFrom() + " to:" + getTo()
					+ " on host: " + getHost() + " subject: " + getSubject()
					+ " message:" + getMsgText());

			// create a message
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			// If the desired charset is known, you can use
			// setText(text, charset)
			msg.setText(msgText);
			Transport.send(msg);
			return true;
		} catch (MessagingException mex) {
			if (log.isDebugEnabled())
				log.error("MessagingException in MailUtility.java");
			mex.printStackTrace();
			Exception ex = mex;
			do {
				if (ex instanceof SendFailedException) {
					SendFailedException sfex = (SendFailedException) ex;
					Address[] invalid = sfex.getInvalidAddresses();
					if (invalid != null) {
						if (log.isDebugEnabled())
							log.error("    ** Invalid Addresses");
						for (int i = 0; i < invalid.length; i++)
							log.error("         " + invalid[i]);
					}
					Address[] validUnsent = sfex.getValidUnsentAddresses();
					if (validUnsent != null) {
						if (log.isDebugEnabled())
							log.error("    ** ValidUnsent Addresses");
						for (int i = 0; i < validUnsent.length; i++)
							if (log.isDebugEnabled())
								if (log.isDebugEnabled())
									log.error("         " + validUnsent[i]);
					}
					Address[] validSent = sfex.getValidSentAddresses();
					if (validSent != null) {
						if (log.isDebugEnabled())
							log.error("    ** ValidSent Addresses");
						for (int i = 0; i < validSent.length; i++)
							if (log.isDebugEnabled())
								log.error("         " + validSent[i]);
					}
				}
				if (ex instanceof MessagingException)
					ex = ((MessagingException) ex).getNextException();
				else
					ex = null;
			} while (ex != null);
			return false;
		}

	}
}
