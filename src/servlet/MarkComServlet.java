package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarkerComDAO;
import model.MarkerCom;

@WebServlet("/MarkerComServlet")
public class MarkComServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String markerComContents = request.getParameter("markerComContents");
        int markerId = Integer.parseInt(request.getParameter("markerId"));

        MarkerCom markerCom = new MarkerCom(0, markerComContents, markerId, null);

        MarkerComDAO dao = new MarkerComDAO();
        boolean result = dao.insert(markerCom);

        if (result) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("error");
        }
    }
}