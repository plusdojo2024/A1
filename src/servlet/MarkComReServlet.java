package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MarkerComFavDAO;
import model.MarkerComFav;


public class MarkComReServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();


		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String markerComId = request.getParameter("markerComId");
		int userIdint = Integer.parseInt(userId);
		int markerComIdint = Integer.parseInt(markerComId);

		// 登録処理を行う
		MarkerComFavDAO mcfDao = new MarkerComFavDAO();
		if (mcfDao.insert(new MarkerComFav(0,userIdint,markerComIdint))) {
		}
		else {
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}
}

