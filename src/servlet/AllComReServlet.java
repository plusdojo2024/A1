package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.AllComFavDAO;
import model.AllComFav;


public class AllComReServlet {
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
		String stUserId = request.getParameter("userId");
		int userId = Integer.parseInt(stUserId);
		String stAllComId = request.getParameter("AllComId");
		int allComId = Integer.parseInt(stAllComId);


		//いいねボタンが押されたときの処理
        if(request.getParameter("data1")!=null) {
//			// 送信されたデータの取得
//			String data1 = request.getParameter("data1");
//			int num = Integer.parseInt(data1);
//			//入力されたデータを表示
//			System.out.println(data1);

			boolean ans = false;

			AllComFavDAO dao = new AllComFavDAO();
			int id[] = dao.selectId();

			if(id[0]==userId && id[1]==allComId) {
				ans = dao.delete(userId, allComId);
			}else {
				ans = dao.insert(new AllComFav(0, userId, allComId, null));
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
			int count = dao.getCount(new AllComFav(0, 0, allComId, null));
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


		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}
}


