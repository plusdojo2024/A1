package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AllComFavDAO;
import model.AllComFav;


public class AllComReServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();


		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String allComId = request.getParameter("AllComId");
		int userIdint = Integer.parseInt(userId);
		int allComIdint = Integer.parseInt(allComId);


		// 登録処理を行う
		AllComFavDAO acfDao = new AllComFavDAO();
		if (acfDao.insert(new AllComFav(0,userIdint,allComIdint,null))) {
		}
		else {
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}
}


