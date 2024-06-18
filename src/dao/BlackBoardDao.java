package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.BlackBoard;

public class BlackBoardDao {

	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<BlackBoard> select(BlackBoard blackBoard){
		Connection conn = null;
		List<BlackBoard> blackBoardList = new ArrayList<BlackBoard>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM Bc WHERE name LIKE ? AND address LIKE ? ORDER BY number";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			if (card.getName() != null) {
				pStmt.setString(1, "%" + card.getName() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}
			if (card.getAddress() != null) {
				pStmt.setString(2, "%" + card.getAddress() + "%");
			}
			else {
				pStmt.setString(2, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Bc record = new Bc(
				rs.getInt("number"),
				rs.getString("company"),
				rs.getString("department"),
				rs.getString("position"),
				rs.getString("name"),
				rs.getString("ruby"),
				rs.getString("zipcode"),
				rs.getString("address"),
				rs.getString("phone"),
				rs.getString("fax"),
				rs.getString("email"),
				rs.getString("food"),
				new Date(rs.getDate("regi").getTime()), //こいつでSQLのDateをJavaのDateに変える。

				rs.getString("remarks")
				);

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date sqlDate =rs.getDate("regi");
				String formattedDate = formatter.format(sqlDate);
				Date utilDate = formatter.parse(formattedDate);
				System.out.println(utilDate+"ssss");
				record.setRegi(formatter.parse(formattedDate));

				cardList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;
	}

}
