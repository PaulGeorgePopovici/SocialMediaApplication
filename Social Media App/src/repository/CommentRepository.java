package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Comment;

public class CommentRepository {
    public CommentRepository() {
    }

    public int create(int postId, String mesaj, LocalDateTime createdAt) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1008);
            String template = "insert into comment (post_id, mesaj, an, luna, zi, ora, minut) values (%d,'%s',%d,%d,%d,%d,%d)";
            return st.executeUpdate(String.format(template, postId, mesaj, createdAt.getYear(),
                    createdAt.getMonthValue(), createdAt.getDayOfMonth(), createdAt.getHour(), createdAt.getMinute()));
        } catch (SQLException var7) {
            throw new RuntimeException(var7);
        }
    }

    public int update(int commentId, String mesajNou) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1008);
            String template = "update comment set mesaj = '%s' where id = %d";
            return st.executeUpdate(String.format(template, mesajNou, commentId));
        } catch (SQLException var6) {
            return 0;
        }
    }

    public int delete(int commentId) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1008);
            return st.executeUpdate("delete from comment where id = " + commentId);
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }

    public ArrayList<Comment> readAll() {
        ArrayList<Comment> allComments = new ArrayList();
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1007);
            ResultSet rs = st.executeQuery("select * from comment");

            while(rs.next()) {
                allComments.add(this.extractCommentFromResultSet(rs));
            }

            return allComments;
        } catch (SQLException var5) {
            throw new RuntimeException(var5);
        }
    }

    private Comment extractCommentFromResultSet(ResultSet rs) {
        try {
            return new Comment(rs.getInt("id"),
                    rs.getInt("post_id"),
                    rs.getInt("user_id"),
                    rs.getString("mesaj"),
                    LocalDateTime.of(rs.getInt("an"),
                            rs.getInt("luna"),
                            rs.getInt("zi"),
                            rs.getInt("ora"),
                            rs.getInt("minut")));
        } catch (SQLException var3) {
            return null;
        }
    }

    public Comment readById(int commentId) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1007);
            ResultSet rs = st.executeQuery("select * from comment where id = " + commentId);
            rs.next();
            return new Comment(rs.getInt("id"),
                    rs.getInt("post_id"),
                    rs.getInt("user_id"),
                    rs.getString("mesaj"),
                    LocalDateTime.of(rs.getInt("an"),
                            rs.getInt("luna"),
                            rs.getInt("zi"),
                            rs.getInt("ora"),
                            rs.getInt("minut")));
        } catch (SQLException var5) {
            return null;
        }
    }
}
