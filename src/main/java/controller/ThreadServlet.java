package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.ThreadDao;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/home/thread")
public class ThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 今日と一か月後の日にちの取得
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		String todayWithTime = today + " 23:59:59";
		System.out.println(todayWithTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		Date month = calendar.getTime();
		String beforemonth = sdf.format(month);
		System.out.println("今日：" + today + "/一カ月前：" + beforemonth);

		// ページネーションの設定
		int postsPerPage = 10; // 1ページに表示する投稿数
		String pageParam = request.getParameter("page");
		int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
		int offset = (currentPage - 1) * postsPerPage;

		List<domain.Thread> thread = new ArrayList<>();

		try {
			ThreadDao dao = DaoFactory.createThreadDao();
			int totalThreads = dao.getTotalThreadCount(beforemonth, todayWithTime);
			int totalPages = (int) Math.ceil((double) totalThreads / postsPerPage);
			// 指定範囲の投稿を取得
			thread = dao.showAll(beforemonth, todayWithTime, postsPerPage, offset);

			//System.out.println(thread);
			HttpSession session=request.getSession();
			session.setAttribute("thread", thread);
			request.setAttribute("thread", thread);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPages", totalPages);
			request.getRequestDispatcher("/WEB-INF/view/thread.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String post = request.getParameter("post");

		ThreadDao dao = DaoFactory.createThreadDao();
		dao.insertThread(loginId, post);
		System.out.println("投稿完了");
		request.setAttribute("msg", "投稿完了しました。");

		response.sendRedirect(request.getContextPath() + "/home/thread");

	}

}
