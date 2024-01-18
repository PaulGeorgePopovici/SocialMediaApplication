package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.User;

public class UserRepository {
    public UserRepository() {
    }

    public ArrayList<User> readAll() {
        ArrayList<User> allUsers = new ArrayList();
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1007);
            ResultSet rs = st.executeQuery("select * from user");

            while(rs.next()) {
                allUsers.add(extractUserFromResultSet(rs));
            }

            return allUsers;
        } catch (SQLException var5) {
            throw new RuntimeException(var5);
        }
    }

    public static User readById(int id) {
        User userCitit = null;
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1007);
            ResultSet rs = st.executeQuery("select * from user where id = " + id);
            rs.next();
            userCitit = extractUserFromResultSet(rs);
            return userCitit;
        } catch (SQLException var5) {
            return null;
        }
    }

    public boolean create(String nume, String prenume, String email, String numarTelefon) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1008);
            String template = "insert into user (nume,prenume,email,numar_telefon) values ('%s','%s','%s','%s')";
            int affectedRows = st.executeUpdate(String.format(template, nume, prenume, email, numarTelefon));
            return affectedRows > 0;
        } catch (SQLException var9) {
            System.out.println("Utilizatorul nu a fost salvat!");
            return false;
        }
    }

    public void modifyName(int id, String numeNou) {
        modifyColumn(id, numeNou, "nume");
    }

    public void modifyPrenume(int id, String prenumeNou) {
        modifyColumn(id, prenumeNou, "prenume");
    }

    public void modifyEmail(int id, String emailNou) {
        modifyColumn(id, emailNou, "email");
    }

    public void modifyNumarTelefon(int id, String numarTelefonNou) {
        modifyColumn(id, numarTelefonNou, "numar_telefon");
    }

    public boolean delete(int id) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1008);
            int affectedRows = st.executeUpdate("delete from user where id = " + id);
            return affectedRows > 0;
        } catch (SQLException var5) {
            return false;
        }
    }

    private static User extractUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"),
                rs.getString("nume"),
                rs.getString("prenume"),
                rs.getString("email"),
                rs.getString("numar_telefon"),
                new ArrayList());
    }

    private static void modifyColumn(int id, String valoareNoua, String columnName) {
        Connection c = ConnectionSingleton.getInstance().getConnection();

        try {
            Statement st = c.createStatement(1003, 1008);
            String template = "update user set %s = '%s' where id = '%d'";
            int affectedRows = st.executeUpdate(String.format(template, columnName, valoareNoua, id));
            System.out.println(columnName + (affectedRows > 0 ? " modificat" : " nemodificat"));
        } catch (SQLException var7) {
            System.out.println("Coloana nu a putut fii modificata!");
        }

    }
}
