/*
//板書の内容を持ってくる
select
//講師による板書への加筆を行えるようにする。
update
//板書履歴に日付一覧を表示するために、今までの日付を持ってくる。
selectDate
//板書履歴の日付をクリックした際に、過去板書の中身を持ってくる
selectBoard

*/

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

	//板書の内容を持ってくる
	public List<BlackBoard> select(){
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
				rs.getInt("board_id"),
				rs.getString("board_contents"),
				rs.getDate("board_date")
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
		String sql = "INSERT INTO black_board (board_contents) VALUES (?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setString(1, blackBoard.getBoardContents());

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
	public boolean update(String boardContents) {
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
			pStmt.setString(1 , boardContents);

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
public ArrayList<BlackBoard> selectDate() {
	Connection conn = null;

	ArrayList<BlackBoard> blackBoardList = new ArrayList<BlackBoard>();

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

		// SQL文を準備する

		String sql = "SELECT board_date , board_id FROM black_board order by board_date desc;";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		// 結果表をコレクションにコピーする
		while (rs.next()) {
			BlackBoard record = new BlackBoard();
			record.setBlackBoardDatetime(rs.getDate("board_date"));
			record.setBoardId(rs.getInt("board_id"));

			blackBoardList.add(record);
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
	return blackBoardList;
}


//板書履歴の日付をクリックした際に、過去板書の中身を持ってくる
public ArrayList<BlackBoard> selectBoard(int boardId) {
	Connection conn = null;
	ArrayList<BlackBoard> blackBoardList = new ArrayList<BlackBoard>();

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

		// SQL文を準備する

		String sql = "select board_id,board_date, board_contents from black_board where board_id = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setInt(1 , boardId);

		// SQL文を実行し、結果表を取得する
        ResultSet rs = pStmt.executeQuery();

        // 結果表をコレクションにコピーする
        while (rs.next()) {
        	BlackBoard blackBoard = new BlackBoard();
        	blackBoard.setBlackBoardDatetime(rs.getDate("board_date"));
        	blackBoard.setBoardContents(rs.getString("board_contents"));
        	blackBoardList.add(blackBoard);
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

	return blackBoardList;
}



//講師の画面：「板書切り替え」ボタンをクリックしたら新しい板書をINSERT
public boolean insertBoard() {
	Connection conn = null;
	boolean result = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

		// SQL文を準備する
		String sql = "INSERT INTO black_board(board_contents) Values('');";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を実行し、結果表を取得する
		int num  = pStmt.executeUpdate();

		// SQL文を実行する
		if (num == 1) {
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


//講師画面・受講生画面：1秒毎にUPDATE
public boolean updateBoard(int board_id, String board_contents) {
	Connection conn = null;
	boolean result = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

		// SQL文を準備する

		String sql = "UPDATE black_board SET board_contents = ? WHERE board_id = ?;";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setString(1 ,board_contents);
		pStmt.setInt(2 , board_id);

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





public BlackBoard selectLatest() {
    Connection conn = null;
    BlackBoard blackBoard = null;

    try {
        // JDBCドライバを読み込む
        Class.forName("org.h2.Driver");

        // データベースに接続する
        conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

        // SQL文を準備する
        String sql = "SELECT * FROM black_board WHERE board_id = (SELECT MAX(board_id) FROM black_board)";
        PreparedStatement pStmt = conn.prepareStatement(sql);

        // SQL文を実行し、結果表を取得する
        ResultSet rs = pStmt.executeQuery();

        // 結果表をオブジェクトにコピーする
        if (rs.next()) {
            blackBoard = new BlackBoard(
                rs.getInt("board_id"),
                rs.getString("board_contents"),
                rs.getDate("board_date")
            );
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        // データベースを切断
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return blackBoard;
}
}