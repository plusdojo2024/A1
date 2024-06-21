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
//日付の値を取得する。
		request.setCharacterEncoding("UTF-8");
		String boardId=request.getParameter("boardId");
		int intboardId = Integer.parseInt(boardId);

//DAOに作業を任せる

		BlackBoardDAO dao = new BlackBoardDAO();
	    List<BlackBoard> bblist = dao.selectBoard(intboardId);

//結果をスコープに格納してjspに送る
	    request.setAttribute("blackBoardList", bblist);

		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/view_board_record.jsp");
		dispatcher.forward(request, response);
	}

}
