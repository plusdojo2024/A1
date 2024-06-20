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
import dao.MarkerComDAO;
import dao.MarkerDAO;
import model.AllCom;
import model.Marker;
import model.MarkerCom;
import model.Users;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        // 全体コメントの取得
        if (request.getParameter("data1") != null) {
            AllComDAO acDao = new AllComDAO();
            List<AllCom> allComList = acDao.getAllComments();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(allComList);
            response.getWriter().write(jsonResponse);
            return;
        }

        // マーカーコメントの取得
        if (request.getParameter("markerId") != null) {
            int markerId = Integer.parseInt(request.getParameter("markerId"));
            MarkerComDAO mcDao = new MarkerComDAO();
            List<MarkerCom> markerComList = mcDao.selectByMarkerId(markerId);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(markerComList);
            response.getWriter().write(jsonResponse);
            return;
        }

        // 全てのマーカーコメントの取得
        if (request.getParameter("allMarkerCom") != null) {
            MarkerComDAO mcDao = new MarkerComDAO();
            List<MarkerCom> markerComList = mcDao.selectAll();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(markerComList);
            response.getWriter().write(jsonResponse);
            return;
        }

        if (request.getParameter("allMarkerCom") != null) {
            MarkerComDAO mcDao = new MarkerComDAO();
            List<MarkerCom> markerComList = mcDao.selectAllWithMarkerContents();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(markerComList);
            response.getWriter().write(jsonResponse);
            return;
        }

        // マーカーの取得
        if (request.getParameter("markerData") != null) {
            MarkerDAO markerDao = new MarkerDAO();
            List<Marker> markerList = markerDao.selectMarkers();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(markerList);
            response.getWriter().write(jsonResponse);
            return;
        }

        // ログインチェックと画面遷移
        if (user == null) {
            response.sendRedirect("/A1/LoginServlet");
            return;
        }

        if (user.getCheckStudent() == 1) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/t_board.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/s_board.jsp");
            dispatcher.forward(request, response);
        }
    }
}