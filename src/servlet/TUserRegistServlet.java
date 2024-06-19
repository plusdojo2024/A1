package servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import model.Users;

@WebServlet("/TUserRegistServlet")
public class TUserRegistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_regist.jsp");
		dispatcher.forward(request, response);

	}

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // リクエストパラメータを取得する
	        request.setCharacterEncoding("UTF-8");
	        String userName = request.getParameter("id");
	        String mail = request.getParameter("mail");
	        String pw = request.getParameter("pw");
	        int jobflag = Integer.parseInt(request.getParameter("jobflag"));

	        // ユーザー情報を生成
	        Users newUser = new Users(0, userName, mail, pw, jobflag);

	        // ユーザー登録処理を行う
	        UsersDAO uDao = new UsersDAO();
	        if (uDao.insertUser(newUser)) {
	            // 登録成功
	            request.setAttribute("result", "ユーザー登録が成功しました。");
	        } else {
	            // 登録失敗
	            request.setAttribute("result", "ユーザー登録に失敗しました。");
	        }

	        // 結果ページにフォワードする
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
	        dispatcher.forward(request, response);
	    }
	}
