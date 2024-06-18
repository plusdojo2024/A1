package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AllComFav;


public class AllComFavDao {

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
				rs.getString("userId"),
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
			String sql = "INSERT INTO all_com_fav VALUES (NULL, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, allComFav.getUserId());
			pStmt.setInt(2, allComFav.getAllComId());
			pStmt.setDate(3, allComFav.getAllComFavDatetime());

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
