package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AllComDAO;
import model.AllCom;


public class AllComServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();


		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String allComContents = request.getParameter("allComContents");




		// 登録処理を行う
		AllComDAO acDao = new AllComDAO();
		if (acDao.insert(new AllCom(0,allComContents,null))) {
		}
		else {
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}
}


