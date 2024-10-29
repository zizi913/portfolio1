package shiftController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.ShiftDao;

/**
 * Servlet implementation class ShowShift
 */
@WebServlet("/home/owner/showShift")
public class ShowShiftServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//今日から一週間分のシフトを一覧表示
		
		//今日の日付を取得
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date);
		//一週間後の日付を取得
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		Date week =calendar.getTime();
		String afterweek=sdf.format(week);
		System.out.println("今日："+today+"一週間後："+afterweek);
		
		//今日から一週間分のシフト一覧表示
		try {
			ShiftDao dao=DaoFactory.createShiftDao();
			Map<Date, Map<String, String>> shiftList=((ShiftDao) dao).findAllShift(today,afterweek);
			request.setAttribute("shiftList", shiftList);
			//名前取得
			List<String> nameList=dao.findNameList(today, afterweek);
			System.out.println("名前一覧："+nameList);//TODO　取得できない
			request.setAttribute("nameList", nameList);
			System.out.println("全員分のシフト:"+shiftList);//OK
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/view/showShift.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//postで受け取った期間のシフトを表示
		
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		System.out.println(start+"~"+end);
		
		ShiftDao dao=DaoFactory.createShiftDao();
		try {
			Map<Date, Map<String, String>> shiftList=((ShiftDao) dao).findAllShift(start,end);
			request.setAttribute("shiftList", shiftList);
			//名前取得
			List<String> nameList=dao.findNameList(start, end);
			System.out.println("名前一覧："+nameList);//TODO　取得できない
			request.setAttribute("nameList", nameList);
			System.out.println("全員分のシフト:"+shiftList);//OK
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/view/showShift.jsp").forward(request, response);
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
