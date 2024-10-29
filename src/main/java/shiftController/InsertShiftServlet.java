package shiftController;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.ShiftDao;

/**
 * Servlet implementation class InsertShiftServlet
 */
@WebServlet("/home/owner/insertShift")
public class InsertShiftServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ShiftDao dao = DaoFactory.createShiftDao();
		Map<String, String> nameIdMap = dao.getNameId();
		System.out.println("全従業員：" + nameIdMap);
		request.setAttribute("nameIdMap", nameIdMap);

		request.getRequestDispatcher("/WEB-INF/view/insertShift.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// データの受け取り
		String loginId = request.getParameter("loginId");
		String date = request.getParameter("date");
		String strShift = request.getParameter("shift");
		int shift = Integer.parseInt(strShift);
		ShiftDao dao = DaoFactory.createShiftDao();

		if (date == null || date.isEmpty()) {
			request.setAttribute("error", "日付が空欄です");
			Map<String, String> nameIdMap = dao.getNameId();
			request.setAttribute("nameIdMap", nameIdMap);
			request.getRequestDispatcher("/WEB-INF/view/insertShift.jsp").forward(request, response);
			return;
		}

		// delete処理
		if (shift == 5) {
			dao.deleteShift(loginId, date);
			request.setAttribute("msg", "休暇設定完了です");
			System.out.println("success");
			Map<String, String> nameIdMap = dao.getNameId();
			request.setAttribute("nameIdMap", nameIdMap);
			request.getRequestDispatcher("/WEB-INF/view/insertShift.jsp").forward(request, response);
			return;
		}
		boolean isUpdated=dao.updateShift(loginId, shift, date);
		
		if (isUpdated) {
			System.out.println("変更完了");
			request.setAttribute("msg", "シフトの変更完了です");
			Map<String, String> nameIdMap = dao.getNameId();
			request.setAttribute("nameIdMap", nameIdMap);
			request.getRequestDispatcher("/WEB-INF/view/insertShift.jsp").forward(request, response);
			return;
		} else if (dao.insertShift(loginId, date, shift)) {
			System.out.println("新規追加完了");
			request.setAttribute("msg", "登録完了です");
			Map<String, String> nameIdMap = dao.getNameId();
			request.setAttribute("nameIdMap", nameIdMap);
			request.getRequestDispatcher("/WEB-INF/view/insertShift.jsp").forward(request, response);
			return;
		} else {
			System.out.println("登録エラーです");
			request.setAttribute("error", "登録エラーです");
			Map<String, String> nameIdMap = dao.getNameId();
			request.setAttribute("nameIdMap", nameIdMap);
			request.getRequestDispatcher("/WEB-INF/view/insertShift.jsp").forward(request, response);
			return;
		}

	}

}
