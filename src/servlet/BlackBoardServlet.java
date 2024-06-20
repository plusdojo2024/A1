
package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BlackBoardDAO;
import model.BlackBoard;

@WebServlet("/TextUpdateServlet")
public class BlackBoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlackBoardDAO dao = new BlackBoardDAO();
        List<BlackBoard> blackBoardList = dao.select();

        // 最新の板書内容を取得
        String textContent = blackBoardList.isEmpty() ? "" : blackBoardList.get(blackBoardList.size() - 1).getBoardContents();

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(textContent);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String textContent = request.getParameter("textContent");

        BlackBoard blackBoard = new BlackBoard();
        blackBoard.setBoardContents(textContent);

        BlackBoardDAO dao = new BlackBoardDAO();
        dao.update(blackBoard);
    }
}