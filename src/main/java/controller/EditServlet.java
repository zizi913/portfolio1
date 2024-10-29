package controller;

import java.io.IOException;

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
 * Servlet implementation class EditServlet
 */
@WebServlet("/home/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sessionの用意
		HttpSession session=request.getSession();

		String loginId=(String) session.getAttribute("loginId");
		System.out.println(loginId);//loginIdは取得できてる
		MemberDao dao=DaoFactory.createMemberDao();
		Member member = null;
		try {
			member = dao.findByLoginId(loginId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(member==null) {
			System.out.println("ユーザーが見つかりませんでした");
		}else {
			request.setAttribute("member", member);			
		}
		request.getRequestDispatcher("/WEB-INF/view/edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sessionの用意
		HttpSession session=request.getSession();
		//パラメーター取得
		String email=request.getParameter("email");
		String tel=request.getParameter("tel");
		String loginId=(String) session.getAttribute("loginId");
		//デバッグ用ログ
		System.out.println(loginId);
		System.out.println(email);
		System.out.println(tel);
		//バリデーションチェック
		if(loginId==null||loginId.isEmpty()) {
			System.out.println("error:loginId is null");
			return;
		}
		if(email==null||email.isEmpty()||tel==null||tel.isEmpty()) {
			System.out.println("errer: email or tel is null");
			return;
		}
		
		MemberDao dao=DaoFactory. createMemberDao();
		dao.update(loginId, email, tel);
		
		Member member=new Member();
		member.setEmail(email);
		member.setTel(tel);
		
		try {
			member = dao.findByLoginId(loginId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("member",member);
		System.out.println(member);
		
		request.getRequestDispatcher("/WEB-INF/view/myPage.jsp").forward(request, response);
		
	}

}
