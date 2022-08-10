package edu.ucalgary.ensf409.view;

/**
 * This class is used to hold all the necessary inputs which are provided by the
 * user and it is called by the logic class and it is then called by the UI
 * class to be filled and passed to logic to used
 * 
 * @author Harsh Sharma <a href=
 *         "mailto:harshit.sharma@ucalgary.ca">harshit.sharma@ucalgary.ca</a>
 * @author Usman Khan <a href=
 *         "mailto:muhammad.khan@ucalgary.ca">muhammad.khan@ucalgary.ca</a>
 * @author Ahad Ali
 *         <a href="mailto:ahad.ali@ucalgary.ca">ahad.ali@ucalgary.ca</a>
 * @version 1.4
 * @since 1.0
 */
public class Input {

    /**
     * User's choice from the furnitureCategory array in UI.java
     */
    private final String FURNITURECHOICE;
    /**
     * User's choice from the corresponding furnitureChoice type array in UI.java
     */
    private final String TYPE;
    /**
     * User's requested number of items of said type and furniture item they chose
     * in UI.java
     */
    private final int NUMBEROFITEMS;
    /**
     * User's database username for SQL to connect to inventory database in UI.java
     */
    private final String USERNAME;
    /**
     * User's database password for SQL to connect to inventory database in UI.java
     */
    private final String PASSWORD;

    /**
     * Constructor which creates an input object which holds all the parameters the
     * user needs to provide in order for the controller to work properly
     * 
     * @param furnitureChoice this parameter determines what kind of furniture the
     *                        user is looking for
     * @param type            this parameter determines what specific type of
     *                        furniture the user is looking for
     * @param numberOfItems   this parameter holds the number of furniture the user
     *                        is looking
     * @param username        this parameter holds the username needed to run the
     *                        database and read in the data in model
     * @param password        this parameter holds the password needed to run the
     *                        database and read in the data in model
     */
    public Input(String furnitureChoice, String type, int numberOfItems, String username, String password) {
        // Initializing the variable
        // initializes the furniture choice requested from the user
        this.FURNITURECHOICE = furnitureChoice;
        // initializes the furniture type requested from the user
        this.TYPE = type;
        // initializes the number of choices requested from the user
        this.NUMBEROFITEMS = numberOfItems;
        // initializes the username for the database
        this.USERNAME = username;
        // initializes the password requested from the user
        this.PASSWORD = password;

        // // throws an illegal argument exception if the number of items is less than 1
        // if(numberOfItems < 1) {
        // throw new IllegalArgumentException(); // throws the exception
        // }
        //
        // //this if statement checks if the furniture choice does not equal one of the
        // types provided in the database
        // if(!furnitureChoice.equals("chair") && !furnitureChoice.equals("desk") &&
        // !furnitureChoice.equals("lamp")
        // && !furnitureChoice.equals("filing")) {
        // //throws the exception that the furniture requested is not part of the
        // database
        // throw new IllegalArgumentException("Furniture choice requested is not part of
        // the database"); // exception with the feedback necessary to fix the problem
        // }
        //
        // //this if statement checks if the furniture choice equals chair and if the
        // specific chair type requested is part of the database
        // if(furnitureChoice.equals("chair") && !type.equals("Task") &&
        // !type.equals("Mesh") && !type.equals("Kneeling")
        // && !type.equals("Executive")) {
        // // throws the exception that the furniture type requested is not part of the
        // database
        // throw new IllegalArgumentException(type + " " + furnitureChoice + " is not
        // part of the database"); // exception with the feedback necessary to fix the
        // problem
        // }
        //
        // //this if statement checks if the furniture choice equals chair and if the
        // specific chair type requested is part of the database
        // if(furnitureChoice.equals("desk") && !type.equals("Adjustable") &&
        // !type.equals("Standing") && !type.equals("Traditional")) {
        // // throws the exception that the furniture type requested is not part of the
        // database
        // throw new IllegalArgumentException(type + " " + furnitureChoice + " is not
        // part of the database"); // exception with the feedback necessary to fix the
        // problem
        // }
        //
        // //this if statement checks if the furniture choice equals desk and if the
        // specific chair type requested is part of the database
        // if(furnitureChoice.equals("lamp") && !type.equals("Desk") &&
        // !type.equals("Study") && !type.equals("Swing Arm")) {
        // // throws the exception that the furniture type requested is not part of the
        // database
        // throw new IllegalArgumentException(type + " " + furnitureChoice + " is not
        // part of the database"); // exception with the feedback necessary to fix the
        // problem
        // }
        //
        // //this if statement checks if the furniture choice equals filing and if the
        // specific chair type requested is part of the database
        // if(furnitureChoice.equals("filing") && !type.equals("Large") &&
        // !type.equals("Medium") && !type.equals("Small")) {
        // // throws the exception that the furniture type requested is not part of the
        // database
        // throw new IllegalArgumentException(type + " " + furnitureChoice + " is not
        // part of the database"); // exception with the feedback necessary to fix the
        // problem
        // }
    }

    /**
     * this is a getter method for the furniture choice as it cannot be accessed
     * otherwise
     * 
     * @return furniture choice from the user's input
     */
    public String getFurnitureChoice() {
        // return the furniture choice
        return FURNITURECHOICE;
    }

    /**
     * this is a getter method for the type choice as it cannot be accessed
     * otherwise
     * 
     * @return type choice from the user's input
     */
    public String getType() {
        // return the type choice
        return TYPE;
    }

    /**
     * this is a getter method for the furniture choice as it cannot be accessed
     * otherwise
     * 
     * @return NumberOfItems from the user's input
     */
    public int getNumberOfItems() {
        // return the number of items
        return NUMBEROFITEMS;
    }

    /**
     * this is a getter method for the username as it cannot be accessed otherwise
     * 
     * @return username from the user's input
     */
    public String getUsername() {
        // return the username
        return USERNAME;
    }

    /**
     * this is a getter method for the password as it cannot be accessed otherwise
     * 
     * @return furniture choice from the user's input
     */
    public String getPassword() {
        // return the password
        return PASSWORD;
    }

}
