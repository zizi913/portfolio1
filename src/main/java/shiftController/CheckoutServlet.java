package shiftController;

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
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/home/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");

		// バリデーション
		
		ShiftDao dao = DaoFactory.createShiftDao();
		String checkout=dao.checkOutCheck(today, loginId);
		System.out.println(checkout);
		if (checkout != null) {
			System.out.println("退勤済みです。");
			session.setAttribute("check", "退勤済みです");
			response.sendRedirect(request.getContextPath() + "/home/main");
			return;
		}

		@SuppressWarnings("static-access")
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		Date dateWeek = calendar.getTime();
		String afterWeek = sdf.format(dateWeek);

		// checkinを受け取り、今の時間を格納

		Date day = new Date();
		String Date = sdf.format(day);

		try {
			dao.checkout(loginId, Date);
			System.out.println("チェックアウト完了");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 以下Mainのコピペ
		// checkoutを格納したあと、jspで再度表示できるように
		try {
			dao = DaoFactory.createShiftDao();
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
