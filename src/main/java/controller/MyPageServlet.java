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
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/home/myPage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			session.setAttribute("loginId", loginId);
			request.setAttribute("member", member);			
		}
		request.getRequestDispatcher("/WEB-INF/view/myPage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
