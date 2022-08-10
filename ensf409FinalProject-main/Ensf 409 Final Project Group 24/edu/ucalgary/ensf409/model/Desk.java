package edu.ucalgary.ensf409.model;

/**
 * This class is used to hold all the attributes/column values for a record in
 * the desk table from the inventory.sql database
 * 
 * @author Shamis Ali
 *         <a href= "mailto:shamis.ali1@ucalgary.ca">shamis.ali1@ucalgary.ca</a>
 * @author Ahad Ali
 *         <a href="mailto:ahad.ali@ucalgary.ca">ahad.ali@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 */

public class Desk {
    /**
     * ID of type String that identifies each entry uniquely
     */
    private String iD;
    /**
     * Type of Desk
     */
    private String type;
    /**
     * Y/N whether the legs exist in String form
     */
    private String legs;
    /**
     * Y/N whether the top exist in String form
     */
    private String top;
    /**
     * Y/N whether the drawer exist in String form
     */
    private String drawer;
    /**
     * Price of the Desk
     */
    private int price;
    /**
     * Manufacturing ID for the Desk linking to that manufacturer
     */
    private String manuID;

    /**
     * Instantiating an Object of the Desk which allows for successful extraction of
     * data from inventory.sql
     * 
     * @param iD     Item ID
     * @param type   Item Type
     * @param legs   Y/N legs
     * @param top    Y/N top
     * @param drawer Y/N drawer
     * @param price  Item Price
     * @param manuID Item Manufacturer ID
     */

    public Desk(String iD, String type, String legs, String top, String drawer, int price, String manuID) {
        // initialized the desk ID gotten from inventory.sql database record in desk
        // table
        this.iD = iD;
        // initialized the desk type gotten from inventory.sql database record in desk
        // table
        this.type = type;
        // initialized the desk legs gotten from inventory.sql database record in desk
        // table
        this.legs = legs;
        // initialized the desk top gotten from inventory.sql database record in desk
        // table
        this.top = top;
        // initialized the desk drawer gotten from inventory.sql database record in desk
        // table
        this.drawer = drawer;
        // initialized the desk price gotten from inventory.sql database record in desk
        // table
        this.price = price;
        // initialized the desk manufacturer's ID gotten from inventory.sql database
        // record in desk table
        this.manuID = manuID;
    }

    /**
     * Getter for Item ID
     * 
     * @return the primary key/ ID of desk record which is a string
     */

    public String getiD() {
        // return desk ID
        return iD;
    }

    /**
     * Getter for Item Type
     * 
     * @return the type of desk record which is a string
     */

    public String getType() {
        // return desk type
        return type;
    }

    /**
     * Getter for Item Legs
     * 
     * @return Y/N if desk record has legs represented by string
     */

    public String getLegs() {
        // return Y/N if desk record has legs or not
        return legs;
    }

    /**
     * Getter for Item Top
     * 
     * @return Y/N if desk record has top represented by string
     */

    public String getTop() {
        // return Y/N if desk record has top or not
        return top;
    }

    /**
     * Getter for Item Drawer
     * 
     * @return Y/N if desk record has drawer represented by string
     */

    public String getDrawer() {
        // return Y/N if desk record has drawer or not
        return drawer;
    }

    /**
     * Getter for Item Price
     * 
     * @return the price of desk record represented by int
     */

    public int getPrice() {
        // return price of desk record
        return price;
    }

    /**
     * Getter for Item Manufacturer ID
     * 
     * @return the manufacuter ID of desk record/foreign key represented by String
     */

    public String getManuID() {
        // return the manufacturer ID of desk record
        return manuID;
    }
}
