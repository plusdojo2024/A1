package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.AllComDAO;
import dao.MarkerDAO;
import model.AllCom;
import model.Marker;
import model.Users;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (request.getParameter("data1") != null) {
            AllComDAO acDao = new AllComDAO();
            List<AllCom> allComList = acDao.getAllComments();

            // デバッグ用
            for (AllCom comment : allComList) {
                System.out.println("Comment: " + comment.getAllComContents());
            }

            // JSON形式でコメントデータを返す
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(allComList);
            response.getWriter().write(jsonResponse);
        } else if (request.getParameter("markerData") != null) {
            MarkerDAO markerDao = new MarkerDAO();
            List<Marker> markerList = markerDao.selectAllMarkers();

            // JSON形式でマーカーデータを返す
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(markerList);
            response.getWriter().write(jsonResponse);
        } else {
            if (user == null) {
                // ログインページにリダイレクト
                response.sendRedirect("/A1/LoginServlet");
                return;
            }

            // CHECK_STUDENTの値に基づいて画面遷移を振り分ける
            if (user.getCheckStudent() == 1) {
                // CHECK_STUDENTが1の場合、s-board.jspにフォワード
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_board.jsp");
                dispatcher.forward(request, response);
            } else {
                // CHECK_STUDENTが0の場合、t-board.jspにフォワード
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/s_board.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}