package edu.ucalgary.ensf409.view;

import java.io.FileWriter;
import java.util.*;
//imports all the classes from model
import edu.ucalgary.ensf409.model.*;

/**
 * This class is used to interact with the user by both receiving input and
 * providing output via the terminal and output file (named orderform.txt)
 * 
 * @author Ahad Ali
 *         <a href="mailto:ahad.ali@ucalgary.ca">ahad.ali@ucalgary.ca</a>
 * @version 2.4
 * @since 1.0
 */

/*  TODO: Use default db info for tests in test_model 
https://d2l.ucalgary.ca/d2l/le/356747/discussions/threads/1347269/View?threadNavFilters=unread

*/
public class UI {
    /**
     * User's choice from furnitureCategory array
     */
    private static String furnitureChoice;
    /**
     * User's choice from the corresponding furnitureChoice type array
     */
    private static String type;
    /**
     * Users requested number of items of the type and furntiture item they chose
     */
    private static int numberOfItems;
    /**
     * User's database username for SQL to connect to inventory database
     */
    private static String username;
    /**
     * User's database password for SQL to connect to inventory database
     */
    private static String password;
    /**
     * Array of possible furniture choices available to user / table names in
     * inventory database
     */
    private static String[] furnitureCategory = { "Chair", "Desk", "Filing", "Lamp" };
    /**
     * Array of possible chair types available to user / column names in chair
     * table, inventory database
     */
    private static String[] chairType = { "Ergonomic", "Executive", "Kneeling", "Mesh", "Task" };
    /**
     * Array of possible desk types available to user / column names in desk table,
     * inventory database
     */
    private static String[] deskType = { "Adjustable", "Standing", "Traditional" };
    /**
     * Array of possible filing types available to user / column names in filing
     * table, inventory database
     */
    private static String[] filingType = { "Small", "Medium", "Large" };
    /**
     * Array of possible lamp types available to user / column names in lamp table,
     * inventory database
     */
    private static String[] lampType = { "Desk", "Study", "Swing Arm" };

    /**
     * Scanner to read system input / input from user via terminal
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * This is the main method of the UI/view which is called from the Logic.java
     * class from controller (package) at the start of the program to get all of the
     * user inputs.
     * 
     * @return an Input object which conveniently stores all of the user inputs in a
     *         single object, which is then easily accessed from the
     *         controller/Logic.java class
     */
    public static Input start() {
        // Print an intro message
        printIntro();
        // Record user database username and password
        recordDbInfo();
        // Record user furniture choice / table name
        furnitureChoice = recordFurniture();
        // Record user type of corresponding furniture choice
        type = recordType();
        // Record user input of the number of items of the type of furniture choice
        // provided
        numberOfItems = recordNumOfItems();
        // return an Input object to Logic.java where this method is called
        return new Input(furnitureChoice, type, numberOfItems, username, password);
    }

    /**
     * This is a primary UI method which is called from the Logic.java class from
     * controller (package) upon an unsuccessful order.
     * 
     * @param manufacturers - string array containing the alternate manufacturers to
     *                      contact if want to purchase the user provided type and
     *                      furnitureChoice at the quantity requested
     */
    public static void outputFailed(String[] manufacturers) {
        // if output failed provide users which alternative manufacturers
        System.out.print("Order cannot be fulfilled based on current inventory. Suggested manufacturers are ");
        // iterate through and print manufacturers provided by Logic.java
        for (int i = 0; i < manufacturers.length - 1; i++) {
            System.out.print(manufacturers[i] + ", ");
        }
        System.out.print(manufacturers[manufacturers.length - 1] + ".");
    }

    /**
     * This is a primary UI method which is called from the Logic.java class from
     * the controller (package) upon a successful order. In addition, to outputting
     * a message to terminal upon sucess it also writes to the output file.
     * 
     * @param combination - string list containing the IDs of the items (from
     *                    inventory.sql database) used to successfully fulfill the
     *                    order
     * @param price       - int value which represents the total price of all the
     *                    items from the combination list. AKA, the price of the
     *                    successful order
     */
    public static void outputSuccess(List<String> combination, int price) {
        // Upon successful order print original request
        System.out.println("Original Request: " + numberOfItems + " " + type + " " + furnitureChoice);
        // also print the items ordered/utilized to successfully fulfill order
        System.out.println("Items Ordered");
        for (int i = 0; i < combination.size(); i++) {
            System.out.println("ID: " + combination.get(i));
        }

        // finally print to terminal total price
        System.out.println("\nTotal Price: " + price);
        // convert arraylist to array
        String[] IDs = new String[combination.size()];
        for (int i = 0; i < IDs.length; i++) {
            IDs[i] = combination.get(i);
        }
        // call the writeOutputFile method to generate output file
        writeOutputFile(IDs, price);
    }

    /**
     * This method simply prints a short intro message upon the start of an order,
     * before asking for any of the user inputs
     */
    public static void printIntro() {
        // print intro message
        System.out.println("\nHello and welcome to our Supply Chain Management Application.\n");
    }

    /**
     * This is a primary UI method which requests for user input for the their
     * database username and password for their sql database account, to access the
     * inventory.sql database
     */
    public static void recordDbInfo() {
        // continuously ask for user database info. till a connection is made
        while (true) {
            // Ask user for database username and password
            System.out.println(
                    "-----------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(
                    "Please provide your username and password credentials to connect to the inventory database at url: jdbc:mysql://localhost/inventory\n");

            System.out.print("SQL Database Username (eg. scm): ");
            username = scanner.nextLine().trim();

            System.out.print("SQL Database Password (eg. ensf409): ");
            password = scanner.nextLine().trim();

            System.out.println("\n");

            // try to make a connection with user's provided username and password, if
            // successful break out of loop, otherwise continue asking
            try {
                Connecting temp = new Connecting("jdbc:mysql://localhost/inventory", username, password);

                temp.close();
                break;
            } catch (Exception e) {
                System.out.println("A connection to the Inventory database can not be established.\n"
                        + "You have entered an invalid username and password. Please try again.\n");
            }

        }

    }

    /**
     * This method simply prints a short outro message upon the end of an order,
     * regardless if it was successful or not.
     */
    public static void printOutro() {
        // print short outro message
        System.out.println(
                "\n\nThank you for using our Supply Chain Management Application.\n" + "Please come again soon!\n");
    }

    /**
     * This is a primary UI method which requests for user input for the furniture
     * choice which will be a table in the corresponding inventory.sql database
     * 
     * @return String - which is the furnitureChoice (inventory.sql database table
     *         name) user inputted
     */
    public static String recordFurniture() {
        // provide user with a table of possible furniture categories
        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("Please choose a furniture category (Enter a number between 1 and 4 included):\n");

        // loop until user provided valid input
        while (true) {
            System.out.println("Number\t\tFurniture Category");
            System.out.println("------\t\t------------------");

            for (int i = 0; i < furnitureCategory.length; i++) {
                System.out.println(i + 1 + "\t\t\t" + furnitureCategory[i]);
            }

            System.out.print("Furniture Category Choice: ");
            String input = scanner.nextLine().trim();

            // if valid input then break out of while loop, otherwise keep asking
            if (isNumeric(input)) {
                int i = Integer.parseInt(input);
                if (i > 0 && i < (furnitureCategory.length + 1)) {
                    return furnitureCategory[i - 1];
                }
            }

            System.out.println(
                    "You entered an invalid value. Please enter a valid number between 1 and 4 inclusive.\n\n");
        }

    }

    /**
     * This is a primary UI method which requests for user input for the type of the
     * corresponding furniture choice provided by user via terminal input
     * 
     * @return String - which is the type choice of corresponding furniture choice
     *         user inputted
     */
    public static String recordType() {
        // ask user for their corresponding furniture choice type by asking them their selection from provided table
        String[] type = getArray(furnitureChoice);

        String line = "Please choose a " + furnitureChoice + " type (Enter a number between 1 and " + type.length
                + " included):\n";

        System.out.println();

        for (int i = 0; i < line.length(); i++) {
            System.out.print("-");
        }

        System.out.println("\n" + line);

        // keep looping till user input is valid
        while (true) {
            String output = furnitureChoice + " Type";
            System.out.println("Number\t\t" + output);
            System.out.print("------\t\t");

            for (int i = 0; i < output.length(); i++) {
                System.out.print("-");
            }
            System.out.println();

            for (int i = 0; i < type.length; i++) {
                System.out.println(i + 1 + "\t\t\t" + type[i]);
            }

            System.out.print(furnitureChoice + " Type: ");
            String input = scanner.nextLine().trim();

            // if user input is valid then break out of loop, otherwise keep asking.
            if (isNumeric(input)) {
                int i = Integer.parseInt(input);
                if (i > 0 && i < (type.length + 1)) {
                    return type[i - 1];
                }
            }

            System.out.println("You entered an invalid value. Please enter a valid number between 1 and " + type.length
                    + " inclusive.\n\n");
        }
    }

    /**
     * This is a primary UI method which requests for user input for the number
     * items of the corresponding furniture choice and type provided by user via
     * terminal input.
     * 
     * @return int - which is the number of items user inputted
     */
    public static int recordNumOfItems() {

        //Ask user for number of items of the provided type of the provided furniture choice
        String line = "Please choose the number of " + type + " " + furnitureChoice + "s you would like to purchase:\n";

        System.out.println();

        for (int i = 0; i < line.length(); i++) {
            System.out.print("-");
        }

        System.out.println("\n" + line);

        // keep looping till user input is valid
        while (true) {
            System.out.print("Number of " + type + " " + furnitureChoice + "s Requested: ");
            String input = scanner.nextLine().trim();

            // if number of items is a number and greater than or equal to 1 exit function, otherwise keep asking
            if (isNumeric(input)) {
                int i = Integer.parseInt(input);
                if (i > 0) {
                    return i;
                }
            }

            System.out.println("You entered an invalid value. Please enter a valid number that is at least 1.\n\n");
        }
    }

    /**
     * This is a helper method that gets called after user provides furniture choice
     * and ask user for corresponding type of their furniture choice. So this method
     * returns the corresponding array of furniture choice received by user via
     * terminal input.
     * 
     * @return String Array - which is the corresponding array of furniture choice
     */
    public static String[] getArray(String furnitureChoice) {
        // upon furniture choice user inputted get corresponding array of types of that furniture choice
        if (furnitureChoice.equals("Chair"))
            return chairType;
        if (furnitureChoice.equals("Desk"))
            return deskType;
        if (furnitureChoice.equals("Filing"))
            return filingType;
        if (furnitureChoice.equals("Lamp"))
            return lampType;
        // return null if not one of the above
        return null;
    }

    /**
     * This is a helper method used when asking for user input to check if the
     * required numeric suggestion read as a string input is an integer
     * 
     * @param input - string input inputted by user
     * @return boolean - true if numeric, false otherwise
     */
    public static boolean isNumeric(String input) {
        // if input is null/empty, then not numeric
        if (input == null) {
            return false;
        }
        // if input can be parsed as an int then successful and is numeric, otherwise it isn't
        try {
            int i = Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Upon a successful order, this method is called to print the necessary output
     * to a file called orderform.txt displaying the items ordered
     * 
     * @param IDs   - array of IDs representing items purchased
     * @param price - int value of price of said items purchased
     */
    public static void writeOutputFile(String[] IDs, int price) {
        // try creating a new file called orderform.txt from where user runs (root project directory)
        try {
            FileWriter outputFile = new FileWriter("orderform.txt");

            // display a output file generated text as shown on project handout
            outputFile.write("Furniture Order Form\n\n" + "Faculty Name:\n" + "Contact:\n" + "Date:\n\n");

            outputFile.write("Original Request: " + type + " " + furnitureChoice + ", " + numberOfItems + "\n\n");

            outputFile.write("Items Ordered\n");
            
            // iterate through and print to orderform.txt file the IDs of items utilized to successfully fulfill order
            for (int i = 0; i < IDs.length; i++) {
                outputFile.write("ID: " + IDs[i] + "\n");
            }

            outputFile.write("\nTotal Price: $" + price);

            outputFile.close();
        } catch (Exception e) {
            // in case orderform.txt file can not be created
            e.printStackTrace();
        }
    }

}
