package repository.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    public static Connection getConnection() {
        Connection conn = null;
        Properties props = new Properties();
        try (BufferedReader in = Files.newBufferedReader(Path.of("app.properties"))) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = props.getProperty("urlAddress");
        String name = props.getProperty("userName");
        String pass = props.getProperty("password");
        try {
            conn = DriverManager.getConnection(url, name, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
}