package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.AllComFavDAO;


@WebServlet("/AllComReServlet")
public class AllComReServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		//決まりの文章、とりあえず書くと覚えておこう
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		//文字コードの指定（これがないとJSPで文字化けする）
        response.setContentType("text/html;charser=UTF-8");

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		//いいねボタンが押されたときの処理
        if(request.getParameter("data1")!=null) {
        	String stUserId = request.getParameter("userId");
    		int userId = Integer.parseInt(stUserId);
    		String stAllComId = request.getParameter("AllComId");
    		int allComId = Integer.parseInt(stAllComId);

//        	int userId = 1;
//        	int allComId = 1;

			boolean ans = false;

			AllComFavDAO dao = new AllComFavDAO();
			int[] id = new int[2];
			id[0] = dao.selectId()[0];
			id[1] = dao.selectId()[1];

			if(id[0]==userId && id[1]==allComId) {
//				System.out.println("入ってるよ");
				ans = dao.delete(userId, allComId);
			}else {
				ans = dao.insert(userId, allComId);
			}

			//Jackson機能のmapperをインスタンス（実体）化
			ObjectMapper mapper = new ObjectMapper();
			try {
	            //JavaオブジェクトからJSONに変換
	            String testJson = mapper.writeValueAsString(ans);
	            System.out.println(testJson);
	            //JSONの出力
	            response.setContentType("application/json; charset=UTF-8");
	            response.getWriter().write(testJson);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }


		//１秒毎にいいねの数を取得するメソッド
        }else {
        	AllComFavDAO dao = new AllComFavDAO();
        	String stAllComId = request.getParameter("AllComId");
    		int allComId = Integer.parseInt(stAllComId);
			int count = dao.getCount(allComId);

			//Jackson機能のmapperをインスタンス（実体）化
			ObjectMapper mapper = new ObjectMapper();
			try {
	            //JavaオブジェクトからJSONに変換
	            String testJson = mapper.writeValueAsString(count);
	            System.out.println(testJson);
	            //JSONの出力
	            response.setContentType("application/json; charset=UTF-8");
	            response.getWriter().write(testJson);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
        }

//
////		// 結果ページにフォワードする
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/iine_suru.jsp");
//		dispatcher.forward(request, response);
	}
}


