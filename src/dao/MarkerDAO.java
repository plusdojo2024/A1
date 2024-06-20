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

    // マーカーコンテンツを全て取得するメソッド
	public List<Marker> selectAllMarkers() {
        Connection conn = null;
        List<Marker> markerList = new ArrayList<>();

        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");

            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            // SQL文を準備する
            String sql = "SELECT * FROM marker";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // SQL文を実行し、結果表を取得する
            ResultSet rs = pStmt.executeQuery();

            // 結果表をコレクションにコピーする
            while (rs.next()) {
                Marker marker = new Marker();
                marker.setMarkerId(rs.getInt("marker_id"));
                marker.setMarkerContents(rs.getString("marker_contents"));
                marker.setBoardId(rs.getInt("board_id"));
                marker.setMarkerDatetime(rs.getDate("marker_datetime"));
                markerList.add(marker);
            }
        } catch (SQLException | ClassNotFoundException e) {
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
        return markerList;
    }
    public int selectBoardId() {
        Connection conn = null;
        int marker = 0;

        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");

            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            // SQL文を準備する
            String sql = "SELECT max(board_id) AS boardId FROM black_board";

            PreparedStatement pStmt = conn.prepareStatement(sql);

            // SQL文を実行し、結果表を取得する
            ResultSet rs = pStmt.executeQuery();

            // 結果表をコレクションにコピーする
            while (rs.next()) {
                marker = rs.getInt("boardId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            marker = 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            marker = 0;
        } finally {
            // データベースを切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    marker = 0;
                }
            }
        }

        // 結果を返す
        return marker;
    }

    public boolean insert(String markerContents, int boardId) {
        Connection conn = null;
        boolean result = false;

        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");

            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            // SQL文を準備する
            String sql = "INSERT INTO marker (marker_contents, board_id, marker_datetime) VALUES (?, ?, now())";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // SQL文を完成させる
            pStmt.setString(1, markerContents);
            pStmt.setInt(2, boardId);
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

        public List<Marker> selectMarkers() {
            Connection conn = null;
            List<Marker> markerList = new ArrayList<>();

            try {
                // JDBCドライバを読み込む
                Class.forName("org.h2.Driver");

                // データベースに接続する
                conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

                // SQL文を準備する
                String sql = "SELECT marker_id, marker_contents, board_id, marker_datetime FROM marker";
                PreparedStatement pStmt = conn.prepareStatement(sql);

                // SQL文を実行し、結果表を取得する
                ResultSet rs = pStmt.executeQuery();

                // 結果表をコレクションにコピーする
                while (rs.next()) {
                    Marker marker = new Marker(
                        rs.getInt("marker_id"),
                        rs.getString("marker_contents"),
                        rs.getInt("board_id"),
                        rs.getDate("marker_datetime")
                    );
                    markerList.add(marker);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                markerList = null;
            } finally {
                // データベースを切断
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        markerList = null;
                    }
                }
            }

            // 結果を返す
            return markerList;
        }

}