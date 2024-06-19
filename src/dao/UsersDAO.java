package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Users;

public class UsersDAO {

    // ログインチェックメソッド
    public Users isLoginOK(Users users) {
        Connection conn = null;
        Users loggedInUser = null;

        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");

            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            // SELECT文を準備する
            String sql = "SELECT * FROM users WHERE mail = ? AND pass = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, users.getMail());
            pStmt.setString(2, users.getPass());

            // SELECT文を実行し、結果表を取得する
            ResultSet rs = pStmt.executeQuery();

            // ユーザーID(メールアドレス)とパスワードが一致するユーザーがいたかどうかをチェックする
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String mail = rs.getString("mail");
                String pass = rs.getString("pass");
                int checkStudent = rs.getInt("check_student");

                loggedInUser = new Users(userId, userName, mail, pass, checkStudent);
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

        // ログインしたユーザー情報を返す
        return loggedInUser;
    }

    // ユーザー登録メソッド
    public boolean insertUser(Users user) {
        Connection conn = null;
        boolean result = false;

        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");

            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            // SQL文を準備する
            String sql = "INSERT INTO users (user_name, mail, pass, check_student) VALUES (?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // SQL文を完成させる
            pStmt.setString(1, user.getUserName());
            pStmt.setString(2, user.getMail());
            pStmt.setString(3, user.getPass());
            pStmt.setInt(4, user.getCheckStudent());

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

public boolean isEmailExists(String email) {
    Connection conn = null;
    boolean exists = false;

    try {
        // JDBCドライバを読み込む
        Class.forName("org.h2.Driver");

        // データベースに接続する
        conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

        // SELECT文を準備する
        String sql = "SELECT COUNT(*) FROM users WHERE mail = ?";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, email);

        // SELECT文を実行し、結果表を取得する
        ResultSet rs = pStmt.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            exists = true;
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

    return exists;
}
}