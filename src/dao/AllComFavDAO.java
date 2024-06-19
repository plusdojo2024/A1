package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AllComFav;


public class AllComFavDAO {

	// 全体コメントをセレクト
	public List<AllComFav> select(AllComFav allComFav) {
		Connection conn = null;
		List<AllComFav> allComFavList = new ArrayList<AllComFav>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM all_com_fav";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				AllComFav record = new AllComFav(
				rs.getInt("allComFavNum"),
				rs.getInt("userId"),
				rs.getInt("allComId"),
				rs.getDate("allComDatetime")
				);
				allComFavList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			allComFavList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			allComFavList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					allComFavList = null;
				}
			}
		}

		// 結果を返す
		return allComFavList;
	}


	public boolean insert(AllComFav allComFav) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO all_com_fav VALUES (NULL, ?, ?, now())";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, allComFav.getUserId());
			pStmt.setInt(2, allComFav.getAllComId());

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

	public int getCount(AllComFav allComFav) {
		Connection conn = null;
		int count = 0;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "SELECT COUNT(*) as c FROM all_com_fav WHERE all_com_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, allComFav.getAllComId());

			// SQL文を実行する
			ResultSet rs = pStmt.executeQuery();
			//値を取り出してcountの中にいれる
			if (rs.next()) {
				count = rs.getInt("c");
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
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
		return count;

	}


public boolean delete(int number) {
	Connection conn = null;
	boolean result = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

		// SQL文を準備する
		String sql = "DELETE FROM all_com_fav WHERE all_com_fav_num=?";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる
		pStmt.setInt(1, number);

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


