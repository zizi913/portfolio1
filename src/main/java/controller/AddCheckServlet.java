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
import domain.AddMember;

/**
 * Servlet implementation class AddCheckServlet
 */
@WebServlet("/home/owner/addCheck")
public class AddCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 特に必要ない
		HttpSession session = request.getSession();
		AddMember member = (AddMember) session.getAttribute("addmember");
		// System.out.println(member);

		request.setAttribute("addmember", member);
		request.getRequestDispatcher("/WEB-INF/view/addCheck.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションからmemberオブジェクトの取得
		HttpSession session = request.getSession();
		AddMember member = (AddMember) session.getAttribute("addmember");
		// System.out.println(member);

		if (member == null) {
			System.out.println("セッションからmemberオブジェクトが取得できません");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return;
		}

		if (member.getName() == null || member.getLoginId() == null || member.getEmail() == null
				|| member.getPass() == null || member.getTel() == null || member.getStatusId() == 0) {
			System.out.println("memberオブジェクトのフィールドにnullが含まれます");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return;
		}

		try {
			MemberDao dao = DaoFactory.createMemberDao();
			boolean shift = dao.addShift(member.getLoginId());

			if (shift == false) {
				request.setAttribute("msg", "一年間分のシフトの自動生成に失敗しました。");
				request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
				return;
			}

			// 挿入が成功した場合リダイレクト
			boolean insert = dao.insert(member);
			if (insert) {
				response.sendRedirect(request.getContextPath() + "/home/owner/addDone");
			} else {
				request.setAttribute("error", "従業員の追加に失敗しました。");
				request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("エラー発生");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
		}

	}

}
