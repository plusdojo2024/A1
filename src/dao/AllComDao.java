package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AllCom;


public class AllComDao {
	// ログインできるならtrueを返す
	public List<AllCom> select(AllCom allCom) {
		Connection conn = null;
		List<AllCom> allComList = new ArrayList<AllCom>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM allCom";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				AllCom record = new AllCom(
				rs.getInt("allComId"),
				rs.getString("allComContents"),
				rs.getDate("allComDatetime")
				);
				allComList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			allComList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			allComList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					allComList = null;
				}
			}
		}

		// 結果を返す
		return allComList;
	}
}
