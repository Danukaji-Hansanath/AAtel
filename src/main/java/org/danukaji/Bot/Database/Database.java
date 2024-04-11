package org.danukaji.Bot.Database;

import org.apache.avro.reflect.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Statement;
import java.util.Properties;

public class Database {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    private static String USER;
    private static String PASSWORD;

    public void DatabaseConnection() {
        Properties prop = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("config.properties");
            prop.load(inputStream);

            USER = prop.getProperty("DB_USERNAME");
            PASSWORD = prop.getProperty("DB_PASSWORD");
            System.out.println(USER + " " + PASSWORD);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    System.out.println("Connected to the database!");
                    connection.setCatalog("dss-films");
                } catch (SQLException e) {
                    System.err.println("Connection failed!");
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found!");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

