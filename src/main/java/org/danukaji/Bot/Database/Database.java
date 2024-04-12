package org.danukaji.Bot.Database;

import org.apache.avro.reflect.Nullable;

import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/dss_film";
    private static String USER;
    private static String PASSWORD;

    static {
        Properties prop = new Properties();
        try (InputStream inputStream = new FileInputStream("config.properties")) {
            prop.load(inputStream);
            USER = prop.getProperty("DB_USERNAME");
            PASSWORD = prop.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //Database search method
    public ResultSet search(String query) {
        ResultSet results = null;
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            String exq = query;
            try (ResultSet resultSet = statement.executeQuery(query)) {
                results = resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    //Database insert data
    public boolean insert(String query) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

