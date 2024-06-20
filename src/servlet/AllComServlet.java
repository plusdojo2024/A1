package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.AllComDAO;
import model.AllCom;

@WebServlet("/AllComServlet")
public class AllComServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータを取得する
        request.setCharacterEncoding("UTF-8");
        String allComContents = request.getParameter("data1");

        // 登録処理を行う
       AllComDAO acDao = new AllComDAO();
        boolean result = acDao.insert(new AllCom(0, allComContents, null));

        // レスポンスをJSON形式で返す準備
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse;

        if (result) {
            jsonResponse = mapper.writeValueAsString(new ResponseMessage("成功しました！"));
        } else {
           jsonResponse = mapper.writeValueAsString(new ResponseMessage("失敗しました。"));
        }

        response.getWriter().write(jsonResponse);
    }

    // レスポンスメッセージを保持するクラス
    static class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
           return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}