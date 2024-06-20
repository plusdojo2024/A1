package servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BlackBoardDAO;
import model.BlackBoard;

@WebServlet("/RecordViewServlet")
public class RecordViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BlackBoardDAO dao = new BlackBoardDAO();
	    List<BlackBoard> list = dao.select();
	    request.setAttribute("blackBoardList", list);
	    
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/view_board_record.jsp");
		dispatcher.forward(request, response);
	}

}
