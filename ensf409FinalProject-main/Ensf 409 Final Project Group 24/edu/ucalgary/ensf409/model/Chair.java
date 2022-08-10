package edu.ucalgary.ensf409.model;

/**
 * This class is used to hold all the attributes/column values for a record in
 * the chair table from the inventory.sql database
 * 
 * @author Shamis Ali
 *         <a href= "mailto:shamis.ali1@ucalgary.ca">shamis.ali1@ucalgary.ca</a>
 * @author Ahad Ali
 *         <a href="mailto:ahad.ali@ucalgary.ca">ahad.ali@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 */

public class Chair {

    /**
     * ID of type String that identifies each entry uniquely
     */
    private String iD;
    /**
     * Type of Chair
     */
    private String type;
    /**
     * Y/N whether the legs exist in String form
     */
    private String legs;
    /**
     * Y/N whether the arms exist in String form
     */
    private String arms;
    /**
     * Y/N whether the seats exist in String form
     */
    private String seat;
    /**
     * Y/N whether the cushion exist in String form
     */
    private String cushion;
    /**
     * Price of the Chair
     */
    private int price;
    /**
     * Manufacturing ID for the Chair linking to that manufacturer
     */
    private String manuID;

    /**
     * Instantiating an Object of the Chair which allows for successful extraction
     * of data from inventory.sql
     * 
     * @param iD      Item ID
     * @param type    Item Type
     * @param legs    Y/N legs
     * @param arms    Y/N arms
     * @param seat    Y/N seat
     * @param cushion Y/N cushion
     * @param price   Item Price
     * @param manuID  Item Manufacturer ID
     */

    public Chair(String iD, String type, String legs, String arms, String seat, String cushion, int price,
            String manuID) {
        // initialized the chair ID gotten from inventory.sql database record in chair
        // table
        this.iD = iD;
        // initialized the chair type gotten from inventory.sql database record in chair
        // table
        this.type = type;
        // initialized the chair legs gotten from inventory.sql database record in chair
        // table
        this.legs = legs;
        // initialized the chair arms gotten from inventory.sql database record in chair
        // table
        this.arms = arms;
        // initialized the chair seat gotten from inventory.sql database record in chair
        // table
        this.seat = seat;
        // initialized the chair cushion gotten from inventory.sql database record in
        // chair table
        this.cushion = cushion;
        // initialized the chair price gotten from inventory.sql database record in
        // chair table
        this.price = price;
        // initialized the chair manufacuter's ID gotten from inventory.sql database
        // record in chair table
        this.manuID = manuID;
    }

    /**
     * Getter for Item ID
     * 
     * @return the ID of a chair record/primary key which is a String
     */

    public String getiD() {
        // return chair ID
        return iD;
    }

    /**
     * Getter for Item Type
     * 
     * @return the type of chair record which is a string
     */

    public String getType() {
        // return chair type
        return type;
    }

    /**
     * Getter for Item Legs
     * 
     * @return Y/N if chair record has legs represented by string
     */

    public String getLegs() {
        // return Y/N if chair record has legs or not
        return legs;
    }

    /**
     * Getter for Item Arms
     * 
     * @return Y/N if chair record has arms represented by string
     */

    public String getArms() {
        // return Y/N if chair record has arms or not
        return arms;
    }

    /**
     * Getter for Item Seat
     * 
     * @return Y/N if chair record has seat represented by string
     */

    public String getSeat() {
        // return Y/N if chair record has seats or not
        return seat;
    }

    /**
     * Getter for Item cushion
     * 
     * @return Y/N if chair record has cushion represented by string
     */

    public String getCushion() {
        // return Y/N if chair record has cushion or not
        return cushion;
    }

    /**
     * Getter for Item Price
     * 
     * @return the price of chair record represented by int
     */

    public int getPrice() {
        // return price of chair record
        return price;
    }

    /**
     * Getter for Item Manufacturer ID
     * 
     * @return the manufacturer's ID for chair record represented by String
     */

    public String getManuID() {
        // return the manufacturer ID of chair record
        return manuID;
    }

    /**
     * Override object's default equal method to check if provided chair object
     * matches the this. object
     */
    @Override
    public boolean equals(Object obj) {
        // typecast provided object to Chair
        Chair obj1 = (Chair) obj;
        // check if all attributes are the same
        if (this.iD.equals(obj1.iD) && this.manuID.equals(obj1.manuID) && this.type.equals(obj1.type)
                && this.legs.equals(obj1.legs) && this.arms.equals(obj1.arms) && this.seat.equals(obj1.seat)
                && this.cushion.equals(obj1.cushion) && this.price == obj1.price) {
            // if so return true
            return true;
        }
        // else return false
        return false;
    }
}
