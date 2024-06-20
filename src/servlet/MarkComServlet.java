package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.MarkerComDAO;
import model.MarkerCom;

public class MarkComServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String markerComContents = request.getParameter("markerComContents");
        int markerId = Integer.parseInt(request.getParameter("markerId"));

        MarkerCom markerCom = new MarkerCom(0, markerComContents, markerId, null);
        MarkerComDAO markerComDao = new MarkerComDAO();
        boolean result = markerComDao.insert(markerCom);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(result ? "success" : "error");
        response.getWriter().write(jsonResponse);
    }
}

