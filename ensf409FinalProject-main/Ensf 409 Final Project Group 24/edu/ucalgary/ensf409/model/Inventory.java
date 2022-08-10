package edu.ucalgary.ensf409.model;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * This class is used to store an arraylist of all the records of all the tables
 * in the inventory.sql database
 * 
 * @author Shamis Ali
 *         <a href= "mailto:shamis.ali1@ucalgary.ca">shamis.ali1@ucalgary.ca</a>
 * @author Ahad Ali
 *         <a href="mailto:ahad.ali@ucalgary.ca">ahad.ali@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 */

public class Inventory {

    /**
     * The following are the class fields explained below:
     */

    /**
     * manufacturer field is for storage for Manufacturer information from
     * inventory.sql
     */

    private List<Manufacturer> manufacturer;
    /**
     * lamp field is for storage for Lamp information from inventory.sql
     */
    private List<Lamp> lamp;
    /**
     * chair field is for storage for Chair information from inventory.sql
     */
    private List<Chair> chair;
    /**
     * desk field is for storage for Desk information from inventory.sql
     */
    private List<Desk> desk;
    /**
     * filing field is for storage for Filing information from inventory.sql
     */
    private List<Filing> filing;
    /**
     * connect field of type Connecting is for establishing connection with database
     */
    private Connecting connect;
    /**
     * The fields DBURL,USERNAME,PASSWORD of type String are to store the username
     * and password as well as the database URL for the inventory.sql connection
     */
    private final String DBURL, USERNAME, PASSWORD;

    /**
     * This is the constructor for the class Inventory
     * 
     * @param dbUrl    Database URL for the inventory.sql
     * @param username Username
     * @param password Password
     */

    public Inventory(String dbUrl, String username, String password) {
        // initialize the database url and also username and password provided by user
        // via terminal
        this.DBURL = dbUrl;
        this.USERNAME = username;
        this.PASSWORD = password;
        // attempt to create a database connection on the database info just initialized
        connect = new Connecting(dbUrl, username, password);
        // if connection successfully made, populate all the arraylists which contains
        // all the data present in the inventory.sql database
        fillArrays(connect);

        // as per convention, close connection after done with getting all the data from
        // inventory.sql and to arrays in java
        connect.close();

    }

    /**
     * Getter for manufacturer
     * 
     * @return a list of manufacturer records
     */

    public List<Manufacturer> getManufacturer() {
        // return the manufacuter records
        return manufacturer;
    }

    /**
     * Getter for lamp
     * 
     * @return a list of lamp records
     */

    public List<Lamp> getLamp() {
        // return the lamp records
        return lamp;
    }

    /**
     * Getter for chair
     * 
     * @return a list of chair records
     */

    public List<Chair> getChair() {
        // return the chair records
        return chair;
    }

    /**
     * Getter for desk
     * 
     * @return a list of desk records
     */

    public List<Desk> getDesk() {
        // return the desk records
        return desk;
    }

    /**
     * Getter for filing
     * 
     * @return a list of filing records
     */

    public List<Filing> getFiling() {
        // return the filing records
        return filing;
    }

    /**
     * This method fills the List for the Manufacturer information storing
     * 
     * @param connection type Connecting object
     */

    private void manuFill(Connecting connection) {
        // try to make a connection and exclusively fill the manufacturer list from
        // inventory.sql
        try {
            // initialize arraylist
            this.manufacturer = new ArrayList<>();
            // get all the records from manufacturer table
            Statement myStmt = connection.getDbConnect().createStatement();
            String query = "SELECT * FROM " + " Manufacturer";
            String[] manufacturer = new String[4];
            String[] columns = { "ManuID", "Name", "Phone", "Province" };

            connection.setResults(myStmt.executeQuery(query));

            // after getting all the records from the inventory.sql database store in array
            // list
            while (connection.getResults().next()) {
                for (int i = 0; i < columns.length; i++) {
                    manufacturer[i] = connection.getResults().getString(columns[i]);
                }
                this.manufacturer
                        .add(new Manufacturer(manufacturer[0], manufacturer[1], manufacturer[2], manufacturer[3]));
            }
        } catch (SQLException ex) {
            // if a sql exception occurs print stack of errors
            ex.printStackTrace();
        }
    }

    /**
     * This method fills the List for the Lamp information storing
     * 
     * @param connection type Connecting object
     */

    private void lampFill(Connecting connection) {
        // try to make a connection and exclusively fill the lamp list from
        // inventory.sql
        try {
            // initialize arraylist
            this.lamp = new ArrayList<>();
            // get all the records from lamp table
            Statement myStmt = connection.getDbConnect().createStatement();
            String query = "SELECT * FROM " + " Lamp";
            String[] lamp = new String[5];
            int price;
            String[] columns = { "ID", "Type", "Base", "Bulb", "ManuID" };

            connection.setResults(myStmt.executeQuery(query));

            // after getting all the records from the inventory.sql database store in array
            // list
            while (connection.getResults().next()) {
                for (int i = 0; i < columns.length; i++) {
                    lamp[i] = connection.getResults().getString(columns[i]);
                }
                price = connection.getResults().getInt("Price");
                this.lamp.add(new Lamp(lamp[0], lamp[1], lamp[2], lamp[3], price, lamp[4]));
            }
        } catch (SQLException ex) {
            // if a sql exception occurs print stack of errors
            ex.printStackTrace();
        }
    }

    /**
     * This method fills the List for the Chair information storing
     * 
     * @param connection type Connecting object
     */

    private void chairFill(Connecting connection) {
        // try to make a connection and exclusively fill the chair list from
        // inventory.sql
        try {
            // initialize arraylist
            this.chair = new ArrayList<>();
            // get all the records from chair table
            Statement myStmt = connection.getDbConnect().createStatement();
            String query = "SELECT * FROM " + " Chair";
            String[] chair = new String[7];
            int price;
            String[] columns = { "ID", "Type", "Legs", "Arms", "Seat", "Cushion", "ManuID" };

            connection.setResults(myStmt.executeQuery(query));
            // after getting all the records from the inventory.sql database store in array
            // list
            while (connection.getResults().next()) {
                for (int i = 0; i < columns.length; i++) {
                    chair[i] = connection.getResults().getString(columns[i]);
                }
                price = connection.getResults().getInt("Price");
                this.chair.add(new Chair(chair[0], chair[1], chair[2], chair[3], chair[4], chair[5], price, chair[6]));
            }
        } catch (SQLException ex) {
            // if a sql exception occurs print stack of errors
            ex.printStackTrace();
        }

    }

    /**
     * This method fills the List for the Desk information storing
     * 
     * @param connection type Connecting object
     */

    private void deskFill(Connecting connection) {
        // try to make a connection and exclusively fill the desk list from
        // inventory.sql
        try {
            // initialize arraylist
            this.desk = new ArrayList<>();
            // try to make a connection and exclusively fill the desk list from
            // inventory.sql
            Statement myStmt = connection.getDbConnect().createStatement();
            String query = "SELECT * FROM " + " Desk";
            String[] desk = new String[6];
            int price;
            String[] columns = { "ID", "Type", "Legs", "Top", "Drawer", "ManuID" };

            connection.setResults(myStmt.executeQuery(query));

            // after getting all the records from the inventory.sql database store in array
            // list
            while (connection.getResults().next()) {
                for (int i = 0; i < columns.length; i++) {
                    desk[i] = connection.getResults().getString(columns[i]);
                }
                price = connection.getResults().getInt("Price");
                this.desk.add(new Desk(desk[0], desk[1], desk[2], desk[3], desk[4], price, desk[5]));
            }
        } catch (SQLException ex) {
            // if a sql exception occurs print stack of errors
            ex.printStackTrace();
        }
    }

    /**
     * This method fills the List for the Filing information storing
     * 
     * @param connection type Connecting object
     */

    private void filingFill(Connecting connection) {
        // try to make a connection and exclusively fill the filing list from
        // inventory.sql
        try {
            // initialize arraylist
            this.filing = new ArrayList<>();
            // get all the records from filing table
            Statement myStmt = connection.getDbConnect().createStatement();
            String query = "SELECT * FROM " + " Filing";
            String[] filing = new String[6];
            int price;
            String[] columns = { "ID", "Type", "Rails", "Drawers", "Cabinet", "ManuID" };

            connection.setResults(myStmt.executeQuery(query));

            // after getting all the records from the inventory.sql database store in array
            // list
            while (connection.getResults().next()) {
                for (int i = 0; i < columns.length; i++) {
                    filing[i] = connection.getResults().getString(columns[i]);
                }
                price = connection.getResults().getInt("Price");
                this.filing.add(new Filing(filing[0], filing[1], filing[2], filing[3], filing[4], price, filing[5]));
            }
        } catch (SQLException ex) {
            // if a sql exception occurs print stack of errors
            ex.printStackTrace();
        }

    }

    /**
     * This method is for updating the inventory.sql when a change happens in the
     * inventory of the stock. For example a stock is reduced and used up it removes
     * it from the table
     * 
     * @param table Name of the table you want to remove from
     * @param id    ID of the item
     * @throws IllegalArgumentException
     */

    public void update(String table, String id) throws IllegalArgumentException {
        // make sure table being modified is not manufacturer table
        if (table.equalsIgnoreCase("Manufacturer")) {
            throw new IllegalArgumentException("This table cannot be modified");
        }
        // make a connection to inventory.sql database from database info
        Connecting newConnect = new Connecting(this.DBURL, this.USERNAME, this.PASSWORD);
        // try to delete specific records from a specified table
        try {
            String query = "DELETE FROM " + table + " WHERE " + "ID = ?";
            PreparedStatement mystmt = newConnect.getDbConnect().prepareStatement(query);
            mystmt.setString(1, id);

            // update the arraylists and close connection
            mystmt.executeUpdate();
            fillArrays(newConnect);
            newConnect.close();

        } catch (SQLException ex) {
            // if a sql exception occurs print stack of errors
            ex.printStackTrace();
        }
    }

    /**
     * This method fills all the arrays by calling all the previous methods
     * 
     * @param connection
     */

    public void fillArrays(Connecting connection) {
        // upon call fills all the arraylists, one for each table in inventory.sql
        // database
        this.manuFill(connection);
        this.chairFill(connection);
        this.deskFill(connection);
        this.filingFill(connection);
        this.lampFill(connection);
    }
}