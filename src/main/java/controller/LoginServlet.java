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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ログインページ表示
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		ログインチェック
		String loginId=request.getParameter("loginId");
		String pass=request.getParameter("pass");
		//String email=request.getParameter("email");
		
		MemberDao dao=DaoFactory.createMemberDao();
		Member member=dao.findByLoginPass(loginId, pass);
		
//		ログイン失敗
		if(member==null) {
			request.setAttribute("error", "IDまたはパスワードが間違っています");
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		}
//		ログイン成功
		request.getSession().setAttribute("member", member);
		HttpSession session=request.getSession();
		session.setAttribute("loginId", loginId);
		String login = "ログイン状態です";
		session.setAttribute("login", login);
		response.sendRedirect(request.getContextPath() + "/home/main");
	}

}
