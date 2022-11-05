package com.innowise.userserialization.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);

    private static final String CONNECTION_PROPERTIES_FILE_NAME = "databaseConnection.properties";
    private static final String DRIVER_CLASS = "db.driver.class";
    private static final String CONNECTION_URL = "db.conn.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";

    private final String databaseConnectionUrl;
    private final String databaseUsername;
    private final String databasePassword;

    public ConnectionFactory() throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONNECTION_PROPERTIES_FILE_NAME);
        properties.load(inputStream);
        String databaseDriverClass = properties.getProperty(DRIVER_CLASS);
        databaseConnectionUrl = properties.getProperty(CONNECTION_URL);
        databaseUsername = properties.getProperty(USERNAME);
        databasePassword = properties.getProperty(PASSWORD);
        LOGGER.info("Initialising " + databaseDriverClass);
        Class.forName(databaseDriverClass);
    }

    public Connection create() throws SQLException {
        LOGGER.info("Creating a database connection for url=" + databaseConnectionUrl + " name=" + databaseUsername + " password=" + databasePassword);
        return DriverManager.getConnection(databaseConnectionUrl, databaseUsername, databasePassword);
    }

}