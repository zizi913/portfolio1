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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/home/owner/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/delete.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginId=request.getParameter("loginId");
		
		MemberDao dao = DaoFactory.createMemberDao();
		try {
			Member member= dao.findByLoginId(loginId);
			System.out.println(member);
			if(member==null) {
				request.setAttribute("error", "入力されたIDの従業員は在籍していません。");
				request.getRequestDispatcher("/WEB-INF/view/delete.jsp").forward(request, response);
				return;
			}
			HttpSession session=request.getSession();
			session.setAttribute("deleteId", loginId);
			session.setAttribute("member", member);
			request.getRequestDispatcher("/WEB-INF/view/deleteInfo.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
