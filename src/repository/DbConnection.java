package repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    public static Connection get() throws IOException, SQLException {
        Connection conn;
        Properties props = new Properties();
        try (BufferedReader in = Files.newBufferedReader(Path.of("C:\\Users\\astar\\IdeaProjects\\CashManagement\\app.properties"))) {
            props.load(in);
        }
        String url = props.getProperty("urlAddress");
        String name = props.getProperty("userName");
        String pass = props.getProperty("password");
        conn = DriverManager.getConnection(url, name, pass);
        return conn;
    }
}
