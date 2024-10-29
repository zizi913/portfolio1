package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import dao.ShiftDao;
import domain.Shift;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/home/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		@SuppressWarnings("static-access")
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		Date dateWeek = calendar.getTime();
		String afterWeek = sdf.format(dateWeek);

		try {
			ShiftDao dao = DaoFactory.createShiftDao();
			List<Shift> shiftList = dao.findByShift(loginId, today, afterWeek);
			request.setAttribute("shift", shiftList);
			System.out.println(shiftList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		System.out.println("start:" + start + "/end:" + end);

		ShiftDao dao = DaoFactory.createShiftDao();
		try {
			List<Shift> shiftList = dao.findByShift(loginId, start, end);
			// TODO strShiftがなぜかNULL
			request.setAttribute("shift", shiftList);
			System.out.println(shiftList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);
	}

}
