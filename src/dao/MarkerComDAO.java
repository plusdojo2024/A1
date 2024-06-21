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
    public List<MarkerCom> selectAll() {
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
                    rs.getInt("marker_com_id"),
                    rs.getString("marker_com_contents"),
                    rs.getInt("marker_id"),
                    rs.getDate("marker_com_datetime")
                );
                MarkerComList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MarkerComList = null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            MarkerComList = null;
        } finally {
            // データベースを切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
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
            String sql = "INSERT INTO marker_com (marker_com_contents, marker_id, marker_com_datetime) VALUES (?, ?, now())";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // SQL文を完成させる
            pStmt.setString(1, markerCom.getMarkerComContents());
            pStmt.setInt(2, markerCom.getMarkerId());

            // SQL文を実行する
            if (pStmt.executeUpdate() == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // データベースを切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 結果を返す
        return result;
    }
    public List<MarkerCom> selectByMarkerId(int markerId) {
        Connection conn = null;
        List<MarkerCom> markerComList = new ArrayList<>();

        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");

            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            // SQL文を準備する
            String sql = "SELECT * FROM marker_com WHERE marker_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, markerId);

            // SQL文を実行し、結果表を取得する
            ResultSet rs = pStmt.executeQuery();

            // 結果表をコレクションにコピーする
            while (rs.next()) {
                MarkerCom record = new MarkerCom(
                    rs.getInt("marker_com_id"),
                    rs.getString("marker_com_contents"),
                    rs.getInt("marker_id"),
                    rs.getDate("marker_com_datetime")
                );
                markerComList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            markerComList = null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            markerComList = null;
        } finally {
            // データベースを切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    markerComList = null;
                }
            }
        }

        // 結果を返す
        return markerComList;
    }


    public List<MarkerCom> selectAllWithMarkerContents() {
        Connection conn = null;
        List<MarkerCom> markerComList = new ArrayList<MarkerCom>();

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            String sql = "SELECT mc.marker_com_id, mc.marker_com_contents, mc.marker_id, mc.marker_com_datetime, m.marker_contents " +
                         "FROM marker_com mc " +
                         "JOIN marker m ON mc.marker_id = m.marker_id " +
                         "JOIN (SELECT MAX(board_id) AS max_board_id FROM black_board) bb ON m.board_id = bb.max_board_id " +
                         "ORDER BY mc.marker_com_datetime DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                MarkerCom record = new MarkerCom(
                    rs.getInt("marker_com_id"),
                    rs.getString("marker_com_contents"),
                    rs.getInt("marker_id"),
                    rs.getDate("marker_com_datetime"),
                    rs.getString("marker_contents")
                );
                markerComList.add(record);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            markerComList = null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    markerComList = null;
                }
            }
        }

        return markerComList;
    }
}