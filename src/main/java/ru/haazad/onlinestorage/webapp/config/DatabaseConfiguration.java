package ru.haazad.onlinestorage.webapp.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class DatabaseConfiguration {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public DatabaseConfiguration() {
    }

    @PostConstruct
    public void init() {
        Properties properties = new Properties();
        try (InputStream in = DatabaseConfiguration.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            properties.load(in);
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            throw  new IllegalArgumentException();
        }
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
