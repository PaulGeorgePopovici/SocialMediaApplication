package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    static String url = "jdbc:mysql://localhost:3306/socialMediaAppDatabase";
    static String username = "root";
    static String password = "Razzor24";
    private static ConnectionSingleton instance = null;
    private Connection connection = null;

    private ConnectionSingleton() {
    }

    public static ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }

        return instance;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            try {
                this.connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException var2) {
                System.out.println("Date de conectare invalide!");
            }
        }

        return this.connection;
    }
}
