package edu.ucalgary.ensf409.model;

/**
 * This class is used to hold all the attributes/column values for a record in
 * the lamp table from the inventory.sql database
 * 
 * @author Shamis Ali
 *         <a href= "mailto:shamis.ali1@ucalgary.ca">shamis.ali1@ucalgary.ca</a>
 * @author Ahad Ali
 *         <a href="mailto:ahad.ali@ucalgary.ca">ahad.ali@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 */

public class Lamp {
    /**
     * ID of type String that identifies each entry uniquely
     */
    private String iD;
    /**
     * Type of Lamp
     */
    private String type;
    /**
     * Y/N whether the base exist in String form
     */
    private String base;
    /**
     * Y/N whether the bulb exist in String form
     */
    private String bulb;
    /**
     * Price of the Lamp
     */
    private int price;
    /**
     * Manufacturing ID for the Lamp linking to that manufacturer
     */
    private String manuID;

    /**
     * Instantiating an Object of the Lamp which allows for successful extraction of
     * data from inventory.sql
     * 
     * @param id     Item ID
     * @param Type   Item Type
     * @param Base   Y/N base
     * @param Bulb   Y/N bulb
     * @param Price  Item price
     * @param manuID Item Manufacturer ID
     */

    public Lamp(String id, String Type, String Base, String Bulb, int Price, String manuID) {
        // initialized the lamp ID gotten from inventory.sql database record in lamp
        // table
        this.iD = id;
        // initialized the lamp type gotten from inventory.sql database record in lamp
        // table
        this.type = Type;
        // initialized the lamp base gotten from inventory.sql database record in lamp
        // table
        this.base = Base;
        // initialized the lamp bulb gotten from inventory.sql database record in lamp
        // table
        this.bulb = Bulb;
        // initialized the lamp price gotten from inventory.sql database record in lamp
        // table
        this.price = Price;
        // initialized the manufacturer ID gotten from inventory.sql database record in
        // lamp table
        this.manuID = manuID;
    }

    /**
     * Getter for Item ID
     * 
     * @return the ID of a lamp record/primary key which is a string
     */
    public String getiD() {
        // return lamp ID
        return iD;
    }

    /**
     * Getter for Item Type
     * 
     * @return the type of a lamp which is a string
     */

    public String getType() {
        // return lamp type
        return type;
    }

    /**
     * Getter for Item base
     * 
     * @return Y/N if there is a base for the specific lamp record which is a string
     */

    public String getBase() {
        // return Y/N if lamp has base or not
        return base;
    }

    /**
     * Getter for Item bulb
     * 
     * @return Y/N if there is a bulb for the specific lamp record which is a string
     */

    public String getBulb() {
        // return Y/N if lamp has bulb or not
        return bulb;
    }

    /**
     * Getter for item price
     * 
     * @return the price of a specific lamp which is an int
     */

    public int getPrice() {
        // return price of lamp
        return price;
    }

    /**
     * Getter for Item Manufacturer ID
     * 
     * @return the manufacturer's ID for lamp that created the lamp/ foreign key
     */

    public String getManuID() {
        // return the manufacturer id that made lamp
        return manuID;
    }
}
