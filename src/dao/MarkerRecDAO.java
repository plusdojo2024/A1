package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MarkerRec;

public class MarkerRecDAO {
	public class AllComDAO {

		// 全体コメントをセレクト
		public List<MarkerRec> select(MarkerRec markerRec) {
			Connection conn = null;
			List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM marker_rec";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					MarkerRec record = new MarkerRec(
					rs.getInt("markerRecNumber"),
					rs.getInt("userId"),
					rs.getInt("markerId"),
					rs.getInt("flagVeryGood"),
					rs.getInt("flagGood"),
					rs.getInt("flagBad"),
					rs.getInt("flagVeryBad"),
					rs.getDate("markerRecDatetime")
					);
					markerRecList.add(record);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				markerRecList = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				markerRecList = null;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						markerRecList = null;
					}
				}
			}

			// 結果を返す
			return markerRecList;
		}
	}
}
