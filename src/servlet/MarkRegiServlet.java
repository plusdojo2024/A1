package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MarkerRecDAO;
import model.Users;


/**
 * Servlet implementation class NineServlet
 */
@WebServlet("/MarkRegiServlet")
public class MarkRegiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// t_board.jspに遷移する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_board.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//値の取得
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		int userId = user.getUserId();

		String stMarkerId = request.getParameter("markerId");
		int markerId = Integer.parseInt(stMarkerId);

		MarkerRecDAO dao = new MarkerRecDAO();

		if(request.getParameter("bu").equals("よくわかる")) {
			dao.insertVg(userId, markerId);
		}else if(request.getParameter("bu").equals("わかる")) {
			dao.insertG(userId, markerId);
		}else if(request.getParameter("bu").equals("わからない")) {
			dao.insertB(userId, markerId);
		}else if(request.getParameter("bu").equals("よくわからない")) {
			dao.insertVb(userId, markerId);
		}

		// t_board.jspに遷移する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_board.jsp");
		dispatcher.forward(request, response);
	}
}

