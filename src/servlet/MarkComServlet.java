package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MarkerComDAO;
import model.MarkerCom;

public class MarkComServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();


		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String markComContents = request.getParameter("markComContents");
		String markerId = request.getParameter("markerId");
		int markerIdint = Integer.parseInt(markerId);



		// 登録処理を行う
		MarkerComDAO mcDao = new MarkerComDAO();
		if (mcDao.insert(new MarkerCom(0,markComContents,markerIdint,null))) {
		}
		else {
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}
}


