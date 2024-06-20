package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarkerDAO;

@WebServlet("/MarkServlet")
public class MarkServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // t_board.jspに遷移する
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_board.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String markerContents = request.getParameter("markerContents");

        MarkerDAO dao = new MarkerDAO();
        int boardId = dao.selectBoardId();

        boolean success = dao.insert(markerContents, boardId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (success) {
            response.getWriter().write("{\"status\": \"success\"}");
        } else {
            response.getWriter().write("{\"status\": \"error\"}");
        }
    }
}