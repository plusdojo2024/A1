package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.MarkerRecDAO;
import model.Users;

@WebServlet("/MarkRegiServlet")
public class MarkRegiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        int userId = user.getUserId();

        int markerId = Integer.parseInt(request.getParameter("markerId"));
        String reaction = request.getParameter("reaction");

        MarkerRecDAO dao = new MarkerRecDAO();
        boolean success = false;

        switch (reaction) {
            case "vg":
                success = dao.insertOrUpdateVg(userId, markerId);
                break;
            case "g":
                success = dao.insertOrUpdateG(userId, markerId);
                break;
            case "b":
                success = dao.insertOrUpdateB(userId, markerId);
                break;
            case "vb":
                success = dao.insertOrUpdateVb(userId, markerId);
                break;
        }

        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(success));
    }
}