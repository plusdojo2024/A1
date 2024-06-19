
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Marker;


public class MarkerDAO {

	// 全体コメントをセレクト
	public List<Marker> select(Marker marker) {
		Connection conn = null;
		List<Marker> markerList = new ArrayList<Marker>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT marker_contents, max(bord_id)AS bd_id"
					+ "FROM marker INNER JOIN black_bord AS bd"
					+ "ON marker.bord_id = bd.bord_id"
					+ "WHERE bord_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, marker.getBoardId());
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Marker record = new Marker(
				rs.getInt("markerId"),
				rs.getString("markerContents"),
				rs.getInt("boardId"),
				rs.getDate("allComDatetime")
				);
				markerList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			markerList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			markerList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					markerList = null;
				}
			}
		}

		// 結果を返す
		return markerList;
	}


	public boolean insert(Marker marker) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO all_com VALUES (NULL, ?, ?,?,)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, marker.getMarkerId());
			pStmt.setString(2, marker.getMarkerContents());
			pStmt.setInt(3, marker.getBoardId());
			pStmt.setDate(4, marker.getMarkerDatetime());
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