package controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import domain.AddMember;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/home/owner/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// フォームデータの取得
		String name = request.getParameter("name");
		String addloginId = request.getParameter("addloginId");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String confirmPass = request.getParameter("confirmPass");
		String tel = request.getParameter("tel");
		String strStatusId = request.getParameter("statusId");
		System.out.println(strStatusId);
		// strStatusIdをintに変換
		int statusId;
		try {
			statusId = Integer.parseInt(strStatusId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("error", "無効な雇用形態が選択されました。");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return; // これ以上の処理を行わないためにreturnを追加
		}
		
		// 受け取ったstatusIdに基づいて雇用形態をstatusに格納
		String status;
		switch (statusId) {
		case 1:
			status = "管理者";
			break;
		case 2:
			status = "社員";
			break;
		default:
			status = "アルバイト";
			break;
		}
	
		// パスワードをハッシュ値にしてからmemberオブジェクトに格納
		String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
		
		// Memberオブジェクト生成
		AddMember addmember = new AddMember();
		addmember.setName(name);
		addmember.setLoginId(addloginId);
		addmember.setEmail(email);
		addmember.setPass(hashedPass);
		addmember.setTel(tel);
		addmember.setStatusId(statusId);
		addmember.setStatus(status);
		
		// 確認ページ用にmemberをsessionに格納
		HttpSession session = request.getSession();
		session.setAttribute("addmember", addmember);
		session.setAttribute("pass", pass);
		System.out.println(addmember);// OK！
		
		// 必須項目がnullまたは空かどうかをチェック
		if (name == null || name.isEmpty() || addloginId == null || addloginId.isEmpty() || email == null || email.isEmpty()
				|| pass == null || pass.isEmpty() || confirmPass == null || confirmPass.isEmpty() || tel == null
				|| tel.isEmpty() || strStatusId == null || strStatusId.isEmpty()) {
			request.setAttribute("msg", "必須項目が未入力です。");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return;
		}

		// バリデーション
		// 名前
		String regex = "^[\\u3040-\\u309F\\u30A0-\\u30FF\\u4E00-\\u9FFF]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			request.setAttribute("msg", "名前の入力形式が誤っています。");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return;
		}

		// ログインID
		if (addloginId.length() < 5) {
			request.setAttribute("msg", "ログインIDは5文字以上の英数で入力してください。エラー1");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return;
		}
		regex = "^[a-zA-Z0-9]+$";
		pattern=Pattern.compile(regex);
		matcher = pattern.matcher(addloginId);
		if (!matcher.matches()) {
			request.setAttribute("msg", "ログインIDは5文字以上の英数で入力してください。");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return;
		}

		// メールアドレス
		regex = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$";
		pattern=Pattern.compile(regex);
		matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			request.setAttribute("msg", "メールアドレスの形式が誤っています。");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return;
		}

		// パスワード
		if (pass.length() < 10) {
			request.setAttribute("msg", "パスワードは10文字以上の英数で入力してください。");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return;
		}
		regex = "^[a-zA-Z0-9]+$";
		pattern=Pattern.compile(regex);
		matcher = pattern.matcher(pass);
		if (!matcher.matches()) {
			request.setAttribute("msg", "パスワード10文字以上の英数で入力してください。");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return;
		}

		// 電話番号
		regex = "0\\d{9,12}";
		pattern=Pattern.compile(regex);
		matcher = pattern.matcher(tel);
		if (!matcher.matches()) {
			request.setAttribute("msg", "電話番号はハイフンを含まない形で入力してください。");
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return;
		}


		// パスワードのバリデーション
		if (!pass.equals(confirmPass)) {
			// エラーメッセージをリクエストスコープに格納
			request.setAttribute("passerror", "パスワードが一致しません。");

			// 同じページにフォワードして再表示
			request.getRequestDispatcher("/WEB-INF/view/insert.jsp").forward(request, response);
			return; // これ以上の処理を行わないためにreturnを追加
		}

		request.getRequestDispatcher("/WEB-INF/view/addCheck.jsp").forward(request, response);

	}

}
