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
//-------------------(個数判別)--------------------------------------------------------------------------------------
//個人の理解度の個数判別(very_good)
		public List<MarkerRec> countVg(MarkerRec markerRec) {
			Connection conn = null;
			List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

				// SQL文を準備する
				String sql = "select count(mr.flag_very_good) as very_good from users "
						+ "inner join marker_rec as mr "
						+ "on users.user_id = mr.user_id join marker "
						+ "on mr.marker_id = marker.marker_id "
						+ "where users.user_id = ? and flag_very_good = 1;";

				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, markerRec.getUserId());

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

//個人の理解度の個数判別(good)
				public List<MarkerRec> countG(MarkerRec markerRec) {
					Connection conn = null;
					List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

						// SQL文を準備する
						String sql = "select count(mr.flag_very_good) as very_good from users "
								+ "inner join marker_rec as mr "
								+ "on users.user_id = mr.user_id join marker "
								+ "on mr.marker_id = marker.marker_id "
								+ "where users.user_id = ? and flag_good = 1;";

						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる
						pStmt.setInt(1, markerRec.getUserId());

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

//個人の理解度の個数判別(bad)
				public List<MarkerRec> countB(MarkerRec markerRec) {
					Connection conn = null;
					List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

						// SQL文を準備する
						String sql = "select count(mr.flag_very_good) as very_good from users "
								+ "inner join marker_rec as mr "
								+ "on users.user_id = mr.user_id join marker "
								+ "on mr.marker_id = marker.marker_id "
								+ "where users.user_id = ? and flag_bad = 1;";

						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる
						pStmt.setInt(1, markerRec.getUserId());

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

//個人の理解度の個数判別(very_good)
				public List<MarkerRec> countVb(MarkerRec markerRec) {
					Connection conn = null;
					List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

						// SQL文を準備する
						String sql = "select count(mr.flag_very_good) as very_good from users "
								+ "inner join marker_rec as mr "
								+ "on users.user_id = mr.user_id join marker "
								+ "on mr.marker_id = marker.marker_id "
								+ "where users.user_id = ? and flag_very_bad = 1;";

						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる
						pStmt.setInt(1, markerRec.getUserId());

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





//-------------------(個人理解度の一覧表示)--------------------------------------------------------------------------------------
//個人理解度の一覧表示(very_good)
		public List<MarkerRec> selectVg(MarkerRec markerRec) {
			Connection conn = null;
			List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

				// SQL文を準備する
				String sql = "select marker_contents from users inner join marker_rec as mr "
						+ "on users.user_id = mr.user_id join marker "
						+ "on mr.marker_id = marker.marker_id "
						+ "where users.user_id = ? "
						+ "and  flag_very_good = 1;";

				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, markerRec.getUserId());

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

		//個人理解度の一覧表示(good)
				public List<MarkerRec> selectG(MarkerRec markerRec) {
					Connection conn = null;
					List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

						// SQL文を準備する
						String sql = "select marker_contents from users inner join marker_rec as mr "
								+ "on users.user_id = mr.user_id join marker "
								+ "on mr.marker_id = marker.marker_id "
								+ "where users.user_id = ? "
								+ "and  flag_good = 1;";

						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる
						pStmt.setInt(1, markerRec.getUserId());

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

				//個人理解度の一覧表示(bad)
				public List<MarkerRec> selectB(MarkerRec markerRec) {
					Connection conn = null;
					List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

						// SQL文を準備する
						String sql = "select marker_contents from users inner join marker_rec as mr "
								+ "on users.user_id = mr.user_id join marker "
								+ "on mr.marker_id = marker.marker_id "
								+ "where users.user_id = ? "
								+ "and  flag_bad = 1;";

						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる
						pStmt.setInt(1, markerRec.getUserId());

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

				//個人理解度の一覧表示(very_bad)
				public List<MarkerRec> selectVb(MarkerRec markerRec) {
					Connection conn = null;
					List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

						// SQL文を準備する
						String sql = "select marker_contents from users inner join marker_rec as mr "
								+ "on users.user_id = mr.user_id join marker "
								+ "on mr.marker_id = marker.marker_id "
								+ "where users.user_id = ? "
								+ "and  flag_very_bad = 1;";

						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる
						pStmt.setInt(1, markerRec.getUserId());

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
