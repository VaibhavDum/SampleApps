package com.web.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    protected static Connection initializeDatabase(String dbDriver, String dbURL,
                                                   String dbName,String dbUsername,String dbPass)
            throws SQLException, ClassNotFoundException {


        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL + dbName,
                dbUsername,
                dbPass);
    }
}

