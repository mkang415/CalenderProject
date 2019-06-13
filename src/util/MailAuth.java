package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

// 이메일 발송을 위한 객체

public class MailAuth extends Authenticator {

	// 구글 SMTP ID
	private String smtp_username = "기본계정";

	// 구글 SMTP PW
	private String smtp_password = "기본패스워드";

	// 인증 객체
	private PasswordAuthentication pa;
	
	public MailAuth(String smtp_username, String smtp_password) {
		this.smtp_username = smtp_username;
		this.smtp_password = smtp_password;
		
		pa = new PasswordAuthentication(smtp_username, smtp_password);
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}
