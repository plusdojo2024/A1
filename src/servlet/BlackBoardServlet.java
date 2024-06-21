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
			if(request.getParameter("保存") != null) {
				BlackBoardDAO bbDao = new BlackBoardDAO();
				bbDao.insertBoard();
			}else {
	//			//板書を一定間隔(1秒間)で保存(UPDATE)し続ける
	//			//講師による板書への加筆
				request.setCharacterEncoding("UTF-8");
				BlackBoardDAO blbDao = new BlackBoardDAO();
				String boardContents = request.getParameter("board_contents");


				blbDao.update(boardContents);
			}

		}
}