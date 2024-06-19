
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AllMarkSearchServlet")
public class AllMarkSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/all_marker_record.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//値の取得
		request.setCharacterEncoding("UTF-8");

//
//		String name=request.getParameter("name");
//		String stAge1 = request.getParameter("age1");
//		int age1 = Integer.parseInt(stAge1);
//		String stDept = request.getParameter("dept");
//		int dept =Integer.parseInt(stDept);
//
//		//押されたボタンの種類によって処理をわける
//		//検索ボタンが押されたら
//		if(request.getParameter("bu").equals("検索")) {
//			//age2は検索でしか使わないのでここで取得
//			String stAge2 = request.getParameter("age2");
//			int age2 = Integer.parseInt(stAge2);
//			//DAOをインスタンス化して作業を丸投げ
//			TenDAO dao = new TenDAO();
//			ArrayList<UserEmpBeans> list = dao.select(age1, age2, name, dept);
//			//検索した結果をリクエストスコープにセットする
//			request.setAttribute("list",list);
//		//登録ボタンが押されたら
//		}else {
//			//値は上で取ってきているので、それを使う
//			//DAOをインスタンス化して作業を丸投げ
//			TenDAO dao = new TenDAO();
//			//insertの結果は何件登録できたかの件数で返ってくるのでintで受け取る
//			int result = dao.insert(name,age1,dept);
//			//１だったら登録できているということ
//			if(result==1) {
//				request.setAttribute("msg","1件の登録が完了しました！");
//			//0だと登録できていないってこと
//			}else {
//				request.setAttribute("msg","登録できませんでした！");
//			}
//		}

		// ten.jspに遷移する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ten.jsp");
		dispatcher.forward(request, response);
	}

}