package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BlackBoard;

public class BlackBoardDAO {

	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<BlackBoard> select(BlackBoard blackBoard){
		Connection conn = null;
		List<BlackBoard> blackBoardList = new ArrayList<BlackBoard>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM black_board ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				BlackBoard record = new BlackBoard(
				rs.getInt("boardId"),
				rs.getString("boardContents"),
				rs.getDate("boardDate")
				);
				blackBoardList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			blackBoardList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			blackBoardList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					blackBoardList = null;
				}
			}
		}

		// 結果を返す
		return blackBoardList;
	}

	public boolean insert(BlackBoard blackBoard) {
	Connection conn = null;
	boolean result = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

		// SQL文を準備する
		String sql = "INSERT INTO black_board VALUES (NULL, ?, CURRENT_TIMESTAMP)";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setString(1, blackBoard.getBoardContents());
		pStmt.setDate(2, blackBoard.getBlackBoardDatetime());

		// SQL文を実行する
		if (pStmt.executeUpdate() == 1) {
			result = true;
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 結果を返す
	return result;
}


//講師による板書への加筆を行えるようにする。
public boolean update(BlackBoard blackBoard) {
	Connection conn = null;
	boolean result = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

		// SQL文を準備する

		String sql = "UPDATE black_board SET board_contents = ? "
				+ "WHERE board_id = (SELECT MAX(board_id) FROM black_board);";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setString(0 , blackBoard.getBoardContents());

		// SQL文を実行する
		if (pStmt.executeUpdate() == 1) {
			result = true;
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 結果を返す
	return result;
}


//板書履歴に日付一覧を表示するために、今までの日付を持ってくる。
public boolean selectDate(BlackBoard blackBoard) {
	Connection conn = null;
	boolean result = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

		// SQL文を準備する

		String sql = "select board_date from black_board order by board_date desc;";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を実行する
		if (pStmt.executeUpdate() == 1) {
			result = true;
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 結果を返す
	return result;
}


//板書履歴の日付をクリックした際に、過去板書の中身を持ってくる
public boolean selectBoard(BlackBoard blackBoard) {
	Connection conn = null;
	boolean result = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

		// SQL文を準備する

		String sql = "select board_date, board_contents from black_board where board_date = ?;";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setDate(0 , blackBoard.getBlackBoardDatetime());

		// SQL文を実行する
		if (pStmt.executeUpdate() == 1) {
			result = true;
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 結果を返す
	return result;
}


}
