package edu.ucalgary.ensf409.model;

/**
 * This class is used to hold all the attributes/column values for a record in
 * the filing table from the inventory.sql database
 * 
 * @author Shamis Ali
 *         <a href= "mailto:shamis.ali1@ucalgary.ca">shamis.ali1@ucalgary.ca</a>
 * @author Ahad Ali
 *         <a href="mailto:ahad.ali@ucalgary.ca">ahad.ali@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 */

public class Filing {
    /**
     * ID of type String that identifies each entry uniquely
     */
    private String iD;
    /**
     * Type of Filing
     */
    private String type;
    /**
     * Y/N whether the rails exist in String form
     */
    private String rails;
    /**
     * Y/N whether the drawers exist in String form
     */
    private String drawers;
    /**
     * Y/N whether the cabinet exist in String form
     */
    private String cabinet;
    /**
     * Price of the Filing cabinet
     */
    private int price;
    /**
     * Manufacturing ID for the Filing cabinet linking to that manufacturer
     */
    private String manuID;

    /**
     * Instantiating an Object of the Chair which allows for successful extraction
     * of data from inventory.sql
     * 
     * @param iD      Item ID
     * @param type    Item Type
     * @param rails   Y/N rails
     * @param cabinet Y/N cabinet
     * @param drawers Y/N drawers
     * @param price   Item price
     * @param manuID  Item Manufacturer ID
     */

    public Filing(String iD, String type, String rails, String cabinet, String drawers, int price, String manuID) {
        // initialized the filing ID gotten from inventory.sql database record in filing
        // table
        this.iD = iD;
        // initialized the filing type gotten from inventory.sql database record in
        // filing table
        this.type = type;
        // initialized the filing rails gotten from inventory.sql database record in
        // filing table
        this.rails = rails;
        // initialized the filing cabinet gotten from inventory.sql database record in
        // filing table
        this.cabinet = cabinet;
        // initialized the filing drawers gotten from inventory.sql database record in
        // filing table
        this.drawers = drawers;
        // initialized the filing price gotten from inventory.sql database record in
        // filing table
        this.price = price;
        // initialized the filing manufacturer ID gotten from inventory.sql database
        // record in filing table
        this.manuID = manuID;
    }

    /**
     * Getter for Item ID
     * 
     * @return the primary key/ ID of filing record which is a string
     */

    public String getiD() {
        // return filing ID
        return iD;
    }

    /**
     * Getter for Item Type
     * 
     * @return the type of filing record which is a string
     */

    public String getType() {
        // return filing type
        return type;
    }

    /**
     * Getter for Item Rails
     * 
     * @return Y/N if filing record has rails represented by string
     */

    public String getRails() {
        // return Y/N if filing record has rails or not
        return rails;
    }

    /**
     * Getter for Item Cabinet
     * 
     * @return Y/N if filing record has cabinet represented by string
     */

    public String getCabinet() {
        // return Y/N if filing record has cabinet or not
        return cabinet;
    }

    /**
     * Getter for Item Drawers
     * 
     * @return Y/N if filing record has drawer represented by string
     */

    public String getDrawers() {
        // return Y/N if filing record has drawers or not
        return drawers;
    }

    /**
     * Getter for item price
     * 
     * @return the price of filing record which is an int
     */

    public int getPrice() {
        // return the price of filing record
        return price;
    }

    /**
     * Getter for Item Manufacturer ID
     * 
     * @return the manufacturing ID/ foreign key
     */

    public String getManuID() {
        // return the manufacturer's ID that made filing
        return manuID;
    }
}
