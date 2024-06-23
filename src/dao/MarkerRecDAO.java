package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.MarkerRec;

public class MarkerRecDAO {
	private final String JDBC_URL = "jdbc:h2:file:C:/pleiades/workspace/data/A1";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public boolean insertOrUpdateVg(int userId, int markerId) {
		return insertOrUpdateReaction(userId, markerId, 1, 0, 0, 0);
	}

	public boolean insertOrUpdateG(int userId, int markerId) {
		return insertOrUpdateReaction(userId, markerId, 0, 1, 0, 0);
	}

	public boolean insertOrUpdateB(int userId, int markerId) {
		return insertOrUpdateReaction(userId, markerId, 0, 0, 1, 0);
	}

	public boolean insertOrUpdateVb(int userId, int markerId) {
		return insertOrUpdateReaction(userId, markerId, 0, 0, 0, 1);
	}

	private boolean insertOrUpdateReaction(int userId, int markerId, int vg, int g, int b, int vb) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String selectSql = "SELECT COUNT(*) FROM marker_rec WHERE user_id = ? AND marker_id = ?";
			PreparedStatement selectStmt = conn.prepareStatement(selectSql);
			selectStmt.setInt(1, userId);
			selectStmt.setInt(2, markerId);

			ResultSet rs = selectStmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);

			if (count > 0) {
				String updateSql = "UPDATE marker_rec SET flag_very_good = ?, flag_good = ?, flag_bad = ?, flag_very_bad = ? WHERE user_id = ? AND marker_id = ?";
				PreparedStatement updateStmt = conn.prepareStatement(updateSql);
				updateStmt.setInt(1, vg);
				updateStmt.setInt(2, g);
				updateStmt.setInt(3, b);
				updateStmt.setInt(4, vb);
				updateStmt.setInt(5, userId);
				updateStmt.setInt(6, markerId);
				result = updateStmt.executeUpdate() == 1;
			} else {
				String insertSql = "INSERT INTO marker_rec (user_id, marker_id, flag_very_good, flag_good, flag_bad, flag_very_bad) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement insertStmt = conn.prepareStatement(insertSql);
				insertStmt.setInt(1, userId);
				insertStmt.setInt(2, markerId);
				insertStmt.setInt(3, vg);
				insertStmt.setInt(4, g);
				insertStmt.setInt(5, b);
				insertStmt.setInt(6, vb);
				result = insertStmt.executeUpdate() == 1;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public Map<String, Integer> getChartData(int markerId) {
		Connection conn = null;
		Map<String, Integer> chartData = new HashMap<>();

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "SELECT " +
					"SUM(flag_very_good) AS veryGood, " +
					"SUM(flag_good) AS good, " +
					"SUM(flag_bad) AS bad, " +
					"SUM(flag_very_bad) AS veryBad " +
					"FROM marker_rec WHERE marker_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, markerId);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				chartData.put("veryGood", rs.getInt("veryGood"));
				chartData.put("good", rs.getInt("good"));
				chartData.put("bad", rs.getInt("bad"));
				chartData.put("veryBad", rs.getInt("veryBad"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return chartData;
	}

	public String getUserReaction(int userId, int markerId) {
		Connection conn = null;
		String reaction = null;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "SELECT flag_very_good, flag_good, flag_bad, flag_very_bad FROM marker_rec WHERE user_id = ? AND marker_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setInt(2, markerId);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("flag_very_good") == 1) {
					reaction = "vg";
				} else if (rs.getInt("flag_good") == 1) {
					reaction = "g";
				} else if (rs.getInt("flag_bad") == 1) {
					reaction = "b";
				} else if (rs.getInt("flag_very_bad") == 1) {
					reaction = "vb";
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return reaction;
	}

	public List<MarkerRec> countVb(int UserId) {
		Connection conn = null;
		List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "select count(mr.flag_very_bad) as very_bad from users "
					+ "inner join marker_rec as mr "
					+ "on users.user_id = mr.user_id join marker "
					+ "on mr.marker_id = marker.marker_id "
					+ "where users.user_id = ? and flag_very_bad = 1;";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, UserId);

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
						rs.getDate("markerRecDatetime"));
				markerRecList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			markerRecList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			markerRecList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					markerRecList = null;
				}
			}
		}

		// 結果を返す
		return markerRecList;
	}

	public List<MarkerRec> selectVg(int UserId) {
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
			pStmt.setInt(1, UserId);

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
						rs.getDate("markerRecDatetime"));
				markerRecList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			markerRecList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			markerRecList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					markerRecList = null;
				}
			}
		}

		// 結果を返す
		return markerRecList;
	}

	//個人理解度の一覧表示(good)
	public List<MarkerRec> selectG(int UserId) {
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
			pStmt.setInt(1, UserId);

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
						rs.getDate("markerRecDatetime"));
				markerRecList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			markerRecList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			markerRecList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					markerRecList = null;
				}
			}
		}

		// 結果を返す
		return markerRecList;
	}

	//個人理解度の一覧表示(bad)
	public List<MarkerRec> selectB(int UserId) {
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
			pStmt.setInt(1, UserId);

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
						rs.getDate("markerRecDatetime"));
				markerRecList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			markerRecList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			markerRecList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					markerRecList = null;
				}
			}
		}

		// 結果を返す
		return markerRecList;
	}

	//個人理解度の一覧表示(very_bad)
	public List<MarkerRec> selectVb(int UserId) {
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
			pStmt.setInt(1, UserId);

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
						rs.getDate("markerRecDatetime"));
				markerRecList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			markerRecList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			markerRecList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					markerRecList = null;
				}
			}
		}

		// 結果を返す
		return markerRecList;
	}

	public List<MarkerRec> warning(int UserId) {
		Connection conn = null;
		List<MarkerRec> markerRecList = new ArrayList<MarkerRec>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT marker.marker_id, marker.marker_contents FROM marker "
					+ "INNER JOIN marker_rec AS lr ON marker.marker_id = lr.marker_id "
					+ "WHERE lr.user_id = <> ?;";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, UserId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				MarkerRec record = new MarkerRec();
				record.setMarkerId(rs.getInt("marker_id"));
				record.setMarkerContents(rs.getString("marker_contents"));
				markerRecList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			markerRecList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			markerRecList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					markerRecList = null;
				}
			}
		}

		// 結果を返す
		return markerRecList;
	}

}
