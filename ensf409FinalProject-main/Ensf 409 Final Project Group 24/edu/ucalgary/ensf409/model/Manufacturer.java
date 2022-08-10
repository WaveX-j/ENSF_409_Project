package edu.ucalgary.ensf409.model;

/**
 * This class is used to hold all the attributes/column values for a record in
 * the manufacturer table from the inventory.sql database
 * 
 * @author Shamis Ali
 *         <a href= "mailto:shamis.ali1@ucalgary.ca">shamis.ali1@ucalgary.ca</a>
 * @author Ahad Ali
 *         <a href="mailto:ahad.ali@ucalgary.ca">ahad.ali@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 */
public class Manufacturer {
    /**
     * Manufacturing ID for that manufacturer
     */
    private String manuID;
    /**
     * Name of the manufacturer
     */
    private String name;
    /**
     * Contact information of that manufacturer
     */
    private String phone;
    /**
     * Location(province) of that manufacturer
     */
    private String province;

    /**
     * Getter for manufacturing ID
     * 
     * @return the manufacturing ID/primary key which is a string
     */

    public String getManuID() {
        // return manufacturer's id
        return manuID;
    }

    /**
     * Getter for manufacturer name
     * 
     * @return the manufacturer's name which is a string
     */

    public String getName() {
        // return name of manufacturer
        return name;
    }

    /**
     * Getter for manufacturer phone
     * 
     * @return the manufacturer's phone number in string representations
     */

    public String getPhone() {
        // return manufacturer's phone number
        return phone;
    }

    /**
     * Getter for manufacturer location
     * 
     * @return the province location of manufacturer which is a string
     */

    public String getProvince() {
        // return manufacturer's province
        return province;
    }

    /**
     * Instantiating an Object of the Chair which allows for successful extraction
     * of data from inventory.sql
     * 
     * @param ID       Manufacturer ID
     * @param Name     Manufacturer name
     * @param Phone    Manufacturer Phone
     * @param Province Manufacturer Province
     */

    public Manufacturer(String ID, String Name, String Phone, String Province) {
        // initialized the manufacturer ID gotten from inventory.sql database record in
        // manufacuturer table
        this.manuID = ID;
        // initialized the manufacturer name gotten from inventory.sql database record
        // in manufacuturer table
        this.name = Name;
        // initialized the manufacturer phone gotten from inventory.sql database record
        // in manufacuturer table
        this.phone = Phone;
        // initialized the manufacturer province gotten from inventory.sql database
        // record in manufacuturer table
        this.province = Province;
    }
}
