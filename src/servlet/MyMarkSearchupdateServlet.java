package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyMarkSearchupdateServlet")
public class MyMarkSearchupdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String markerId = request.getParameter("markerId");
        String markerContents = request.getParameter("markerContents");

        // Here, you can process the markerId and markerContents as needed.
        // For example, you can store them in the database, or perform any other required action.

        // Assuming the processing is successful, you can send a success response.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"status\":\"success\"}");
    }
}