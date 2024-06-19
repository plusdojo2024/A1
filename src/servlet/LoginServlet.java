package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;
import model.Users;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータを取得する
        request.setCharacterEncoding("UTF-8");
        String mail = request.getParameter("mail");
        String pw = request.getParameter("pw");

        // ログイン処理を行う
        UsersDAO uDao = new UsersDAO();
        Users user = uDao.isLoginOK(new Users(mail, pw));
        if (user != null) { // ログイン成功
            // セッションスコープにユーザー情報を格納する
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // MainServletにリダイレクト
            response.sendRedirect("/A1/MainServlet");
        } else { // ログイン失敗
            // リクエストスコープに、エラーメッセージを格納する
            request.setAttribute("error", "IDまたはPWに間違いがあります。");
            // ログインページにフォワードする
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}