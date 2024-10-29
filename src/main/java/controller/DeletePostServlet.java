package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.ThreadDao;

/**
 * Servlet implementation class DeletePostServlet
 */
@WebServlet("/home/deletePost")
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String strId=request.getParameter("id");
		int id=Integer.parseInt(strId);
		
		ThreadDao dao=DaoFactory.createThreadDao();
		boolean deleteThread=dao.deletePost(id);
		if(deleteThread) {
			System.out.println("削除完了");
			response.sendRedirect(request.getContextPath() + "/home/thread");
		}else {
			System.out.println("削除失敗");
			response.sendRedirect(request.getContextPath() + "/home/thread");
			return;
		}
		
	}

}
