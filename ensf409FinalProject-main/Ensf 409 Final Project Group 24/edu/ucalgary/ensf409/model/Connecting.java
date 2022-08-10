package edu.ucalgary.ensf409.model;

import java.sql.*;

/**
 * This class is attempt to create a connection to the inventory.sql database
 * with the provided user inputted database username and password
 * 
 * @author Shamis Ali
 *         <a href= "mailto:shamis.ali1@ucalgary.ca">shamis.ali1@ucalgary.ca</a>
 * @author Ahad Ali
 *         <a href="mailto:ahad.ali@ucalgary.ca">ahad.ali@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 */

public class Connecting {

    /**
     * Username for the connection
     */
    private static String username;
    /**
     * Password for the connection
     */
    private static String password;
    /**
     * Database URL for inventory.sql
     */
    private static String dbUrl;
    /**
     * Connection object for the connection
     */
    private Connection dbConnect;
    /**
     * ResultSet type object for the results of the query
     */
    private ResultSet results;

    /**
     * Setter for the ResultSet object
     * 
     * @param results
     */

    public void setResults(ResultSet results) {
        this.results = results;
    }

    /**
     * Getter for the ResultSet object
     *
     */

    public ResultSet getResults() {
        return results;
    }

    /**
     * Constructor for the Connecting class that allows to make a connection
     * 
     * @param dburl    Database URL for the inventory.sql
     * @param username Username
     * @param password Password
     */

    public Connecting(String dburl, String username, String password) {
        Connecting.username = username;
        Connecting.password = password;
        Connecting.dbUrl = dburl;

        this.createConnection();

    }

    /**
     * Getter for the Connection object
     * 
     * @return
     */
    public Connection getDbConnect() {
        return dbConnect;
    }

    /**
     * Created the connection to the actual database
     */

    private void createConnection() {
        try {
            dbConnect = (Connection) DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            // e.printStackTrace(); - commented out as this is used for UI and exception is
            // caught in UI
        }

    }

    /**
     * Safely closes the connection made allowing for the data to be secure
     */

    public void close() {
        try {
            if (results != null) {
                results.close();
            }

            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
