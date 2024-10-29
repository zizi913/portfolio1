package controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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

import dao.DaoFactory;
import dao.MemberDao;
import domain.Member;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/home/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		System.out.println(loginId);// loginIdは取得できてる
		MemberDao dao = DaoFactory.createMemberDao();
		Member member = null;
		try {
			member = dao.findByLoginId(loginId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (member == null) {
			System.out.println("ユーザーが見つかりませんでした");
		} else {
			session.setAttribute("loginId", loginId);
			request.setAttribute("member", member);
		}
		request.getRequestDispatcher("/WEB-INF/view/contact.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	String to = request.getParameter("to");
	String from = request.getParameter("from");
	String subject = request.getParameter("subject");
	String body = request.getParameter("maintext");
	
	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.mail.me.com"); // iCloudのSMTPサーバー
	props.put("mail.smtp.port", "587"); // ポート番号
	props.put("mail.smtp.auth", "true"); // 認証
	props.put("mail.smtp.starttls.enable", "true"); // TLSを使用
	props.put("mail.smtp.ssl.trust", "smtp.mail.me.com"); // SSL証明書を信頼

	final String email = "zizi913@icloud.com"; 
	final String password = "mgcg-ebco-tgnf-bsga"; 
	System.out.println(email+"/"+password);

	// 認証情報
	Session session = Session.getInstance(props, new Authenticator() {
	    @Override
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(email, password); // アプリ専用パスワードを使用
	    }
	});

	try {
	    // メールの内容を設定
	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(from)); // 送信者のメールアドレス
	    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // 受信者のメールアドレス
	    message.setSubject(subject); // 件名
	    message.setText(body); // 本文

	    // メールを送信
	    Transport.send(message);

	    // 成功したら確認メッセージをセット
	    request.setAttribute("msg", "メールが送信されました");

	} catch (MessagingException e) {
	    e.printStackTrace();
	    request.setAttribute("msg", "メールの送信に失敗しました");
	}
	HttpSession httpsession = request.getSession();
	String loginId = (String) httpsession.getAttribute("loginId");
	System.out.println(loginId);// loginIdは取得できてる
	MemberDao dao = DaoFactory.createMemberDao();
	Member member = null;
	try {
		member = dao.findByLoginId(loginId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	if (member == null) {
		System.out.println("ユーザーが見つかりませんでした");
	} else {
		httpsession.setAttribute("loginId", loginId);
		request.setAttribute("member", member);
	}
	// 結果を表示するページにフォワード
	request.getRequestDispatcher("/WEB-INF/view/contact.jsp").forward(request, response);

	}
}
