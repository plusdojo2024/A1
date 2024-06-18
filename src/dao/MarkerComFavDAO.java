package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MarkerComFav;


public class MarkerComFavDAO {

	// 全体コメントをセレクト
	public List<MarkerComFav> select(MarkerComFav markerComFav) {
		Connection conn = null;
		List<MarkerComFav> markerComFavList = new ArrayList<MarkerComFav>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM marker_com_fav";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				MarkerComFav record = new MarkerComFav(
				rs.getInt("markerComNum"),
				rs.getString("userId"),
				rs.getInt("markerComId")
				);
				markerComFavList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			markerComFavList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			markerComFavList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					markerComFavList = null;
				}
			}
		}

		// 結果を返す
		return markerComFavList;
	}


	public boolean insert(MarkerComFav markerComFav) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO all_com_fav VALUES (NULL, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, markerComFav.getUserId());
			pStmt.setInt(2, markerComFav.getMarkerComId());

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
