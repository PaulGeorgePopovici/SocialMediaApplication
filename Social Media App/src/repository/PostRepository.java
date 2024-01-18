package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Post;

public class PostRepository {
    public PostRepository() {
    }

    public ArrayList<Post> readAll() {
        ArrayList<Post> allPosts = new ArrayList();
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1007);
            ResultSet rs = st.executeQuery("select * from post");

            while (rs.next()) {
                allPosts.add(this.extractPostsFromResultSet(rs));
            }

            return allPosts;
        } catch (SQLException var5) {
            throw new RuntimeException(var5);
        }
    }

    public ArrayList<Post> readPostsFromUserWithId(int userId) {
        ArrayList<Post> postarileUserului = new ArrayList();
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1007);
            ResultSet rs = st.executeQuery("select * from post where user_id = " + userId);

            while (rs.next()) {
                postarileUserului.add(this.extractPostsFromResultSet(rs));
            }

            return postarileUserului;
        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }

    private Post extractPostsFromResultSet(ResultSet rs) {
        try {
            return new Post(rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("mesaj"),
                    LocalDateTime.of(rs.getInt("an"),
                            rs.getInt("luna"),
                            rs.getInt("zi"),
                            rs.getInt("ora"),
                            rs.getInt("minut")),
                    new ArrayList());
        } catch (SQLException var3) {
            return null;
        }
    }

    public int create(int userId, String mesaj, LocalDateTime createdAt) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1008);
            String template = "insert into post (user_id, mesaj, an, luna, zi, ora, minut) values (%d,'%s',%d,%d,%d,%d,%d)";
            return st.executeUpdate(String.format(template, userId, mesaj, createdAt.getYear(),
                    createdAt.getMonthValue(), createdAt.getDayOfMonth(), createdAt.getHour(), createdAt.getMinute()));
        } catch (SQLException var7) {
            throw new RuntimeException(var7);
        }
    }

    public static Post readById(int postId) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1007);
            ResultSet rs = st.executeQuery("select * from post where id = " + postId);
            rs.next();
            return new Post(postId, rs.getInt("user_id"),
                    rs.getString("mesaj"),
                    LocalDateTime.of(rs.getInt("an"),
                            rs.getInt("luna"),
                            rs.getInt("zi"),
                            rs.getInt("ora"),
                            rs.getInt("minut")),
                    new ArrayList());
        } catch (SQLException var4) {
            return null;
        }
    }

    public int update(int postId, String mesajNou) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1008);
            String template = "update post set mesaj = '%s' where id = %d";
            return st.executeUpdate(String.format(template, mesajNou, postId));
        } catch (SQLException var6) {
            return 0;
        }
    }

    public int delete(int postId) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1008);
            return st.executeUpdate("delete from post where id = " + postId);
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }
}
