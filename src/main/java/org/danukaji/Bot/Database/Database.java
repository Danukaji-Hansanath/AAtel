package org.danukaji.Bot.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Database {
    private static final String url = "jbdc:mysql://127.0.0.1:3306/mysql";
    private static String userName;
    private static String password;

    public void DatabaseConnection() {
        Properties prop = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("config.properties");
            prop.load(inputStream);

            userName = prop.getProperty("DB_USERNAME");
            password = prop.getProperty("DB_PASSWORD");
            System.out.println(userName + " " + password);
            try (Connection connection = DriverManager.getConnection(url, userName, password)) {
                connection.beginRequest();
                System.out.println("Database Connected");
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
