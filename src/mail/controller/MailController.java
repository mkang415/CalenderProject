package mail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mail.service.face.MailService;
import mail.service.impl.MailServiceImpl;
import util.ConfirmCode;
import util.MailAuth;

// 메일 발송 

@WebServlet("/send")
public class MailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MailService mailservice = new MailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(true==mailservice.confirm(req.getParameter("verifyemail"))) {
			req.setAttribute("result", "이미 가입되어있음");
			return;
		}
		
		
		// FROM
		final String FROM = "genenune@gmail.com"; // <<------------------------------수정하세요
		final String FROMNAME = "MatchingMaster"; // <<------------------------------수정하세요

		// TO
		final String TO = req.getParameter("verifyemail"); // <<------------------------------수정하세요

		// 메일 제목
		final String SUBJECT = "회원가입을 위한 이메일 인증 코드"; // <<------------------------------수정하세요

		// 메일 본문
		final String BODY = String.join(
				"<h1>회원가입을 위한 이메일 인증 코드 발송입니다.</h1>",
				"<p>인증 코드는 "+ req.getParameter("code") + " 입니다.</p>"); // <<------------------------------수정하세요

		// 인증 객체
		Authenticator auth = new MailAuth("genenune@gmail.com", "dudgus13"); // <<------------------------------수정하세요

		// 연결 설정
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		
		// 메일 세션 객체 생성
		Session session = Session.getDefaultInstance(props, auth);

		// 메시지 생성
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(FROM, FROMNAME));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			msg.setSubject(SUBJECT);
			msg.setContent(BODY, "text/html;charset=utf-8");

			System.out.println("Sending...");

			//메시지 보내기
			Transport.send(msg);
			
			System.out.println("Email sent!");

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			
		} catch (MessagingException e) {
			e.printStackTrace();
			
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + e.getMessage());
			
		} 		
		
	}
}
