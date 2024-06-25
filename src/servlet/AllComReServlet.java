package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.AllComFavDAO;


@WebServlet("/AllComReServlet")
public class AllComReServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "nocache");
        response.setContentType("text/html;charset=UTF-8");

        int userId = Integer.parseInt(request.getParameter("userId"));
        int allComId = Integer.parseInt(request.getParameter("AllComId"));

        AllComFavDAO dao = new AllComFavDAO();
        boolean liked = dao.toggleLike(userId, allComId);
        int likeCount = dao.getCount(allComId);

        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("liked", liked);
        jsonResponse.put("likeCount", likeCount);

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(jsonResponse));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int allComId = Integer.parseInt(request.getParameter("AllComId"));

        AllComFavDAO dao = new AllComFavDAO();
        boolean liked = dao.isLikedByUser(userId, allComId);
        int likeCount = dao.getCount(allComId);

        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("liked", liked);
        jsonResponse.put("likeCount", likeCount);

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(jsonResponse));
    }
}