package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AllComFav;


public class AllComFavDAO {

    public ArrayList<AllComFav> select() {
        Connection conn = null;
        ArrayList<AllComFav> allComFavList = new ArrayList<AllComFav>();

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            String sql = "SELECT * FROM all_com_fav";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                AllComFav record = new AllComFav(
                    rs.getInt("allComFavNum"),
                    rs.getInt("userId"),
                    rs.getInt("allComId"),
                    rs.getDate("allComDatetime")
                );
                allComFavList.add(record);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            allComFavList = null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    allComFavList = null;
                }
            }
        }
        return allComFavList;
    }

    public boolean isLikedByUser(int userId, int allComId) {
        Connection conn = null;
        boolean liked = false;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");
            String sql = "SELECT COUNT(*) FROM all_com_fav WHERE user_id = ? AND all_com_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userId);
            pStmt.setInt(2, allComId);

            ResultSet rs = pStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                liked = true;
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
        return liked;
    }


    public boolean insert(int userId, int allComId) {
        Connection conn = null;
        boolean result = false;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            String sql = "INSERT INTO all_com_fav (user_id, all_com_id) VALUES (?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userId);
            pStmt.setInt(2, allComId);

            if (pStmt.executeUpdate() == 1) {
                result = true;
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

    public int getCount(int allComId) {
        Connection conn = null;
        int count = 0;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            String sql = "SELECT COUNT(*) as c FROM all_com_fav WHERE all_com_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, allComId);

            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("c");
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
        return count;
    }

    public boolean delete(int userId, int allComId) {
        Connection conn = null;
        boolean result = false;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            String sql = "DELETE FROM all_com_fav WHERE user_id = ? and all_com_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userId);
            pStmt.setInt(2, allComId);

            if (pStmt.executeUpdate() == 1) {
                result = true;
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

    public boolean toggleLike(int userId, int allComId) {
        if (isLiked(userId, allComId)) {
            return delete(userId, allComId);
        } else {
            return insert(userId, allComId);
        }
    }
    public boolean isLiked(int userId, int allComId) {
        Connection conn = null;
        boolean liked = false;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A1", "sa", "");

            String sql = "SELECT COUNT(*) FROM all_com_fav WHERE user_id = ? AND all_com_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userId);
            pStmt.setInt(2, allComId);

            ResultSet rs = pStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                liked = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return liked;
    }




}
