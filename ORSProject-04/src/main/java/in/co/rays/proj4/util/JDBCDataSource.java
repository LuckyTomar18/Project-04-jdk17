
package in.co.rays.proj4.util;

import java.sql.*;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * JDBCDataSource is a Singleton class responsible for managing all database
 * connections using the C3P0 connection pooling library.
 * 
 *
 * 
 * This utility:
 * 
 * 
 * Loads database configuration from system.properties file
 * Creates and manages a shared connection pool
 * Provides static methods to acquire and close connections
 * Ensures efficient memory and resource usage
 * 
 *
 * 
 * Parameters loaded from the ResourceBundle:
 * 
 * 
 *     driver  JDBC driver class
 *     url � Database connection URL
 *     username � DB username
 *     password � DB password
 *     initialpoolsize � starting number of connections
 *     acquireincrement � connections added when pool is exhausted
 *     maxpoolsize � maximum allowed connections
 * 
 *
 * 
 * This class is used throughout the project to safely obtain and release
 * connections for all model classes.
 * 
 *
 * @author Lucky
 * @version 1.0
 */
public final class JDBCDataSource {

    /** Singleton instance of JDBCDataSource */
    private static JDBCDataSource jds = null;

    /** C3P0 connection pool instance */
    private ComboPooledDataSource cpds = null;

    /** Loads DB configuration from system.properties file */
    private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.proj4.bundle.System");

    /**
     * Private constructor to initialize connection pool.
     * This ensures Singleton implementation.
     */
    private JDBCDataSource() {
        try {
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass(rb.getString("driver"));
            cpds.setJdbcUrl(rb.getString("url"));
            cpds.setUser(rb.getString("username"));
            cpds.setPassword(rb.getString("password"));
            cpds.setInitialPoolSize(Integer.parseInt(rb.getString("initialpoolsize")));
            cpds.setAcquireIncrement(Integer.parseInt(rb.getString("acquireincrement")));
            cpds.setMaxPoolSize(Integer.parseInt(rb.getString("maxpoolsize")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Singleton instance of JDBCDataSource.
     *
     * @return JDBCDataSource instance
     */
    public static JDBCDataSource getInstance() {
        if (jds == null) {
            jds = new JDBCDataSource();
        }
        return jds;
    }

    /**
     * Returns a database connection from the C3P0 connection pool.
     *
     * @return Connection object or null if connection cannot be created
     */
    public static Connection getConnection() {
        try {
            return getInstance().cpds.getConnection();
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Safely closes ResultSet, Statement, and Connection objects.
     *
     * @param conn the Connection to close
     * @param stmt the Statement to close
     * @param rs   the ResultSet to close
     */
    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close(); // returns connection back to the pool
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Safely closes Statement and Connection objects.
     *
     * @param conn the Connection to close
     * @param stmt the Statement to close
     */
    public static void closeConnection(Connection conn, Statement stmt) {
        closeConnection(conn, stmt, null);
    }

    /**
     * Safely closes only the Connection object.
     *
     * @param conn the Connection to close
     */
    public static void closeConnection(Connection conn) {
        closeConnection(conn, null);
    }
}
