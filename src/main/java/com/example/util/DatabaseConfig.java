package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find database.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading database.properties", ex);
        }
    }

    public static String getUrl() {
        String url = properties.getProperty("jdbc.url");
        if (url == null) {
            throw new RuntimeException("jdbc.url not found in database.properties");
        }
        return url;
    }

    public static String getUsername() {
        String username = properties.getProperty("jdbc.username");
        if (username == null) {
            throw new RuntimeException("jdbc.username not found in database.properties");
        }
        return username;
    }

    public static String getPassword() {
        String password = properties.getProperty("jdbc.password");
        if (password == null) {
            throw new RuntimeException("jdbc.password not found in database.properties");
        }
        return password;
    }

    public static String getDriverClassName() {
        String driverClassName = properties.getProperty("jdbc.driver");
        if (driverClassName == null) {
            throw new RuntimeException("jdbc.driver not found in database.properties");
        }
        return driverClassName;
    }
}