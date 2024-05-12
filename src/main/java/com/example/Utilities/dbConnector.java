package com.example.Utilities;
import java.sql.*;

public class dbConnector {
    /** The Oracle driver. */
    public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    /** The connection URL. */
    public static final String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
    /** The username used to connect to the database. */
    public static final String username = "system";
    /** The password used to connect to the database. */
    public static final String password = "Neonael2004";

    public static Connection getConnection() {
        try {
            Class.forName(ORACLE_DRIVER).newInstance();
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error has occurred. Driver not found." + ex.getMessage());
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("Error has occurred. Cannot create a database instance." + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error has occurred. Cannot connect to the database." + ex.getMessage());
        }
        return null;
    }
}