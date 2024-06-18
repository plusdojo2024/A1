package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MarkerCom;


public class MarkerComDAO {

	// 全体コメントをセレクト
	public List<MarkerCom> select(MarkerCom markerCom) {
		Connection conn = null;
		List<MarkerCom> MarkerComList = new ArrayList<MarkerCom>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM marker_com";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				MarkerCom record = new MarkerCom(
				rs.getInt("markerComId"),
				rs.getString("markerComContents"),
				rs.getInt("markerId"),
				rs.getDate("markerComDatetime")
				);
				MarkerComList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			MarkerComList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			MarkerComList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					MarkerComList = null;
				}
			}
		}

		// 結果を返す
		return MarkerComList;
	}


	public boolean insert(MarkerCom markerCom) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "INSERT INTO marker_com VALUES (NULL, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, markerCom.getMarkerComContents());
			pStmt.setInt(1, markerCom.getMarkerId());
			pStmt.setDate(2, markerCom.getMarkerComDatetime());

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
