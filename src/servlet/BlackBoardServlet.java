package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BlackBoardDAO;

@WebServlet("/BlackBoardServlet")
	public class BlackBoardServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			//値の取得
			//リクエストパラメータを取得
			request.setCharacterEncoding("UTF-8");
			String stBId = request.getParameter("board_id");
			int board_id = Integer.parseInt(stBId);
			String bContents = request.getParameter("board_contents");

//			//「板書切り替え」ボタンが押されたら…
//			if(request.getParameter("bu").contentEquals("切り返し")) {
			BlackBoardDAO bbDao = new BlackBoardDAO();
//			boolean result = bbDao.insert(new BlackBoard(0, blackBoardComents, null));
			bbDao.insertBoard();

//			//板書を一定間隔(1秒間)で保存(UPDATE)し続ける
//			//講師による板書への加筆
			BlackBoardDAO blbDao = new BlackBoardDAO();
			String boId = request.getParameter("board_id");
			int boardId = Integer.parseInt(boId);
			String boardContents = request.getParameter("board_contents");

			String BoardContents = request.getParameter("bContents");
			blbDao.updateBoard("board_id", "board_contents");

		}
}
