package edu.ucalgary.ensf409.controller;

//imports the util class so that arraylist and lists can be used
import java.util.*;
//imports all the classes from model
import edu.ucalgary.ensf409.model.*;
//imports the UI and Input class from view
import edu.ucalgary.ensf409.view.*;

/**
 * This class is the main class of the program which runs the entire program and
 * connects the model and view together
 * 
 * @author Harsh Sharma <a href="mailto:harshit.sharma@ucalgary.ca">harshit.sharma@ucalgary.ca</a>
 * @author Usman Khan <a href=
 *         "mailto:muhammad.khan@ucalgary.ca">muhammad.khan@ucalgary.ca</a>
 * @version 3.7
 * @since 1.0
 */
public class Logic {
    // Variables
    /** this variable holds the cheapest price based on the users input */
    public static int cheapestPrice;
    /** this is a private variable which holds the entire database */
    public static Inventory inventory;
    /**
     * these variables are initialized depending on the furniture choice requested
     * by the user this variable holds all the combinations that can make a full
     * chair from all partial chairs in the database
     */
    public static List<List<Chair>> chairCombinations;
    /**
     * this variable holds all the combinations that can make a full desk from all
     * partial desks in the database
     */
    public static List<List<Desk>> deskCombinations;
    /**
     * this variable holds all the combinations that can make a full lamp from all
     * partial lamps in the database
     */
    public static List<List<Lamp>> lampCombinations;
    /**
     * this variable holds all the combinations that can make a full filing cabinet
     * from all partial filing cabinets in the database
     */
    public static List<List<Filing>> filingCombinations;
    /**
     * this variable holds the final combination with all the required Ids to make
     * the cheapest amount of request furniture
     */
    public static List<String> combination;

    /**
     * this is the main class in the program which runs the entire program
     * 
     * @param args when the user first runs the program they are allowed to pass in
     *             a variable although in this scenario it doesn't actually do
     *             anything
     */
    public static void main(String[] args) {
        // This is a final variable which holds a list of all the manufacturers and
        // which furniture they produce
        final String[] CHAIRMANUFACTURERS = { "Office Furnishings", "Chairs R Us", "Furniture Goods",
                "Fine Office Supplies" },
                DESKMANUFACTURERS = { "Academic Desks", "Office Furnishings", "Furniture Goods",
                        "Fine Office Supplies" },
                LAMPMANUFACTURERS = { "Office Furnishings", "Furniture Goods", "Fine Office Supplies" },
                FILINGMANUFACTURERS = { "Office Furnishings", "Furniture Goods", "Fine Office Supplies" };
        // this variable is a final variable which cannot be altered and holds all the
        // inputs provided by the user
        // ui.start runs the introduction to the program which allows the user to input
        // their choices
        final Input INPUT = UI.start();
        // inventory is the model part of the program which holds and loads the entire
        // database
        // and updates it if necessary
        // input.getusername is a getter methods which gets username for the database
        // from the user
        // input.password gets the password for the database
        inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        // this if statement runs the necessary methods for the specific type of
        // furniture requested and returns true if
        // a combination was made and false if it was not
        // input.get password is the specific furniture
        // input.getype is the type of the specific furniture requested
        // input.getNumberOfItems returns the quantity of items
        if (specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems())) {
            // this calls the UI method called output success if a successful combination
            // was made
            updateDatabase(INPUT.getFurnitureChoice());
            UI.outputSuccess(combination, cheapestPrice);

        } else {
            // this is run if a successful combination was not made and it determines what
            // list of manufacturers to pass
            // depending on the furniture
            switch (INPUT.getFurnitureChoice().toLowerCase()) {
            // this case passes the chair manufacturers
            case "chair":
                UI.outputFailed(CHAIRMANUFACTURERS);
                break;
            // this case passes the desk manufacturers
            case "desk":
                UI.outputFailed(DESKMANUFACTURERS);
                break;
            // this case passes the lamp manufacturers
            case "lamp":
                UI.outputFailed(LAMPMANUFACTURERS);
                break;
            // this case passes the filing cabinet manufacturers
            case "filing":
                UI.outputFailed(FILINGMANUFACTURERS);
                break;
            // this case is run if the furniture choice expected is not part of the database
            default:
                // throws an illegal argument error
                throw new IllegalArgumentException("Specific Furniture manufacturer does not exist");
            }
        }
        // this calls the UI method called print outro just before the application is
        // terminated
        UI.printOutro();
    }

    /**
     * this methods determines the type fo specific furniture requested and runs teh
     * required method for that type and it returns true if a successful combination
     * was made and false if none were made
     * 
     * @param category this parameter holds the furniture requested
     * @param type     this parameter holds the specific furniture the user
     *                 requested
     * @param quantity this parameter holds the number of items requested
     * @return this methods returns true if a successful combination was made
     */
    public static boolean specificFurniture(String category, String type, int quantity) {
        // this switch statement determines what specific set of methods to run
        // depending on the furniture requested
        switch (category.toLowerCase()) {
        // this case runs for chair
        case "chair":
            // this variable is initialized to hold all the combinations together
            chairCombinations = new ArrayList<>();
            // this method fills the chairCombinations variable with all variables
            // the readChairData(type) returns all the chairs in the database of the
            // specific type
            uniqueCombinationChair(0, 0, 0, 0, 0, quantity, new Vector<>(), readChairData(type));
            // this method return true if the chair combination array is filled and if it
            // can find a price
            return cheapestChair(category);
        // this case runs for chair
        case "desk":
            // this variable is initialized to hold all the combinations together
            deskCombinations = new ArrayList<>();
            // this method fills the deskCombinations variable with all variables
            // the readDeskData(type) returns all the chairs in the database of the specific
            // type
            uniqueCombinationDesk(0, 0, 0, 0, quantity, new Vector<>(), readDeskData(type));
            // this method return true if the desk combination array is filled and if it can
            // find a price
            return cheapestDesk(category);
        // this case runs for chair
        case "lamp":
            // this variable is initialized to hold all the combinations together
            lampCombinations = new ArrayList<>();
            // this method fills the lampCombinations variable with all variables
            // the readLampData(type) returns all the lamps in the database of the specific
            // type
            uniqueCombinationLamp(0, 0, 0, quantity, new Vector<>(), readLampData(type));
            // this method return true if the lamp combination array is filled and if it can
            // find a price
            return cheapestLamp(category);
        // this case runs for chair
        case "filing":
            // this variable is initialized to hold all the combinations together
            filingCombinations = new ArrayList<>();
            // this method fills the filingCombinations variable with all variables
            // the readFilingData(type) returns all the filing cabinets in the database of
            // the specific type
            uniqueCombinationFiling(0, 0, 0, 0, quantity, new Vector<>(), readFilingData(type));
            // this method return true if the filing combination array is filled and if it
            // can find a price
            return cheapestFiling(category);
        // this is the default case if the furniture requested is not available in the
        // database
        default:
            // returns false if furniture category not found
            return false;
        }
    }

    /**
     * This method loads all the chairs in the database and the finds all the chairs
     * of a specific type requested
     * 
     * @param type this is the specific type of chair requested by the user
     * @return returns a list filled with all of the specific types of chairs
     */
    public static List<Chair> readChairData(String type) {
        // creating a temporary variable which will hold all the specific chairs
        // filling chairs variable with the get chairs from inventory
        List<Chair> specificChairs = new ArrayList<>(), chairs = inventory.getChair();
        // this for loop runs through the entire chairs array and if the a chair equals
        // the specific type requested
        // then it will be added to the specific chairs list
        for (Chair chair : chairs)
            if (chair.getType().equals(type))
                specificChairs.add(chair);
        // returns the list filled with specific chair type
        return specificChairs;
    }

    /**
     * This method loads all the desks in the database and the finds all the desks
     * of a specific type requested
     * 
     * @param type this is the specific type of desk requested by the user
     * @return returns a list filled with all of the specific types of desks
     */
    public static List<Desk> readDeskData(String type) {
        // creating a temporary variable which will hold all the specific desks
        // filling desks variable with the get desks from inventory
        List<Desk> specificDesks = new ArrayList<>(), desks = inventory.getDesk();
        // this for loop runs through the entire desks array and if the a desk equals
        // the specific type requested
        // then it will be added to the specific desks list
        for (Desk desk : desks)
            if (desk.getType().equals(type))
                specificDesks.add(desk);
        // returns the list filled with specific desk type
        return specificDesks;
    }

    /**
     * This method loads all the lamps in the database and the finds all the lamps
     * of a specific type requested
     * 
     * @param type this is the specific type of lamp requested by the user
     * @return returns a list filled with all of the specific types of lamps
     */
    public static List<Lamp> readLampData(String type) {
        // creating a temporary variable which will hold all the specific lamps
        // filling lamps variable with the get lamps from inventory
        List<Lamp> specificLamps = new ArrayList<>(), lamps = inventory.getLamp();
        // this for loop runs through the entire lamps array and if the a lamp equals
        // the specific type requested
        // then it will be added to the specific lamps list
        for (Lamp lamp : lamps)
            if (lamp.getType().equals(type))
                specificLamps.add(lamp);
        // returns the list filled with specific lamp type
        return specificLamps;
    }

    /**
     * This method loads all the filing cabinets in the database and the finds all
     * the filing cabinets of a specific type requested
     * 
     * @param type this is the specific type of filing cabinet requested by the user
     * @return returns a list filled with all of the specific types of filing
     *         cabinets
     */
    public static List<Filing> readFilingData(String type) {
        // creating a temporary variable which will hold all the specific filing
        // cabinets
        // filling filing cabinets variable with the get filing cabinets from inventory
        List<Filing> specificFilings = new ArrayList<>(), filings = inventory.getFiling();
        // this for loop runs through the entire filing cabinets array and if the a
        // filing cabinet equals the specific type requested
        // then it will be added to the specific filing cabinets list
        for (Filing filing : filings)
            if (filing.getType().equals(type))
                specificFilings.add(filing);
        // returns the list filled with specific filing cabinet type
        return specificFilings;
    }

    /**
     * This methods looks through the entire chair array list and tries to combines
     * them all to find all possible combinations
     * 
     * @param l            holds the index of the global chairs parameter
     * @param sum1         this is the total sum of all the legs
     * @param sum2         this is the total sum of all the arms
     * @param sum3         this is the total sum of all the seats
     * @param sum4         this is the total sum of all the cushions
     * @param quantity     this holds the number of items requested
     * @param localChairs  this parameter holds the current list needed for the
     *                     combination
     * @param globalChairs this variables holds all the filing chairs
     */
    public static void uniqueCombinationChair(int l, int sum1, int sum2, int sum3, int sum4, int quantity,
            List<Chair> localChairs, List<Chair> globalChairs) {
        // this method checks if the current combination has all the parts of the
        // furniture
        if (sum1 >= quantity && sum2 >= quantity && sum3 >= quantity && sum4 >= quantity) {
            // this method adds the current combinations to the global combination variable
            chairCombinations.add(new ArrayList<>(localChairs));
            // returns the method
            return;
        }

        for (int i = l; i < globalChairs.size(); i++) {
            // checks if the current filing cabinet is already added
            // if it is added then it skips the increment
            if (i > l && globalChairs.get(i) == globalChairs.get(i - 1))
                continue;

            // int variables that are 0 if the current furniture does not have the item and
            // 1 if it does
            int legs = 0, arm = 0, seat = 0, cushion = 0;
            // the following if statements adds one to the item if it equals Y
            if (globalChairs.get(i).getLegs().equals("Y"))
                legs = 1;
            if (globalChairs.get(i).getArms().equals("Y"))
                arm = 1;
            if (globalChairs.get(i).getSeat().equals("Y"))
                seat = 1;
            if (globalChairs.get(i).getCushion().equals("Y"))
                cushion = 1;

            // this method adds the current index furniture from the global list to the
            // local list
            localChairs.add(globalChairs.get(i));
            // recursive call to function
            uniqueCombinationChair(i + 1, sum1 + legs, sum2 + arm, sum3 + seat, sum4 + cushion, quantity, localChairs,
                    globalChairs);
            // removes the last element of the local list
            localChairs.remove(localChairs.size() - 1);
        }
    }

    /**
     * This methods looks through the entire chair array list and tries to combines
     * them all to find all possible combinations
     * 
     * @param l           this is current index of global cabinets
     * @param sum1        this is the total sum of all the legs
     * @param sum2        this is the total sum of all the tops
     * @param sum3        this is the total sum of all the drawers
     * @param quantity    this holds the number of items requested
     * @param localDesks  this parameter holds the current list needed for the
     *                    combination
     * @param globalDesks this variables holds all the filing desks
     */
    public static void uniqueCombinationDesk(int l, int sum1, int sum2, int sum3, int quantity, List<Desk> localDesks,
            List<Desk> globalDesks) {
        // this method checks if the current combination has all the parts of the
        // furniture
        if (sum1 >= quantity && sum2 >= quantity && sum3 >= quantity) {
            // this method adds the current combinations to the global combination variable
            deskCombinations.add(new ArrayList<>(localDesks));
            // returns the method
            return;
        }

        for (int i = l; i < globalDesks.size(); i++) {
            // checks if the current filing cabinet is already added
            // if it is added then it skips the increment
            if (i > l && globalDesks.get(i) == globalDesks.get(i - 1))
                continue;

            // int variables that are 0 if the current furniture does not have the item and
            // 1 if it does
            int legs = 0, top = 0, drawer = 0;
            // the following if statements adds one to the item if it equals Y
            if (globalDesks.get(i).getLegs().equals("Y"))
                legs = 1;
            if (globalDesks.get(i).getTop().equals("Y"))
                top = 1;
            if (globalDesks.get(i).getDrawer().equals("Y"))
                drawer = 1;

            // this method adds the current index furniture from the global list to the
            // local list
            localDesks.add(globalDesks.get(i));
            // recursive call to function
            uniqueCombinationDesk(i + 1, sum1 + legs, sum2 + top, sum3 + drawer, quantity, localDesks, globalDesks);
            // removes the last element of the local list
            localDesks.remove(localDesks.size() - 1);
        }
    }

    /**
     * This methods looks through the entire chair array list and tries to combines
     * them all to find all possible combinations
     * 
     * @param l           this is current index of global cabinets
     * @param sum1        this is the total sum of all the bases
     * @param sum2        this is the total sum of all the bulbs
     * @param quantity    this holds the number of items requested
     * @param localLamps  this parameter holds the current list needed for the
     *                    combination
     * @param globalLamps this variables holds all the filing lamps
     */
    public static void uniqueCombinationLamp(int l, int sum1, int sum2, int quantity, List<Lamp> localLamps,
            List<Lamp> globalLamps) {
        // this method checks if the current combination has all the parts of the
        // furniture
        if (sum1 >= quantity && sum2 >= quantity) {
            // this method adds the current combinations to the global combination variable
            lampCombinations.add(new ArrayList<>(localLamps));
            // returns the method
            return;
        }

        for (int i = l; i < globalLamps.size(); i++) {
            // checks if the current filing cabinet is already added
            // if it is added then it skips the increment
            if (i > l && globalLamps.get(i) == globalLamps.get(i - 1))
                continue;

            // int variables that are 0 if the current furniture does not have the item and
            // 1 if it does
            int base = 0, bulb = 0;
            // the following if statements adds one to the item if it equals Y
            if (globalLamps.get(i).getBase().equals("Y"))
                base = 1;
            if (globalLamps.get(i).getBulb().equals("Y"))
                bulb = 1;

            // this method adds the current index furniture from the global list to the
            // local list
            localLamps.add(globalLamps.get(i));
            // recursive call to function
            uniqueCombinationLamp(i + 1, sum1 + base, sum2 + bulb, quantity, localLamps, globalLamps);
            // removes the last element of the local list
            localLamps.remove(localLamps.size() - 1);
        }
    }

    /**
     * This methods looks through the entire filing array list and tries to combines
     * them all to find all possible combinations
     * 
     * @param l             this is current index of global cabinets
     * @param sum1          this is the total sum of all the rails
     * @param sum2          this is the total sum of all the drawers
     * @param sum3          this is the total sum of all the cabinets
     * @param quantity      this holds the number of items requested
     * @param localFilings  this parameter holds the current list needed for the
     *                      combination
     * @param globalFilings this variables holds all the filing cabinets
     */
    public static void uniqueCombinationFiling(int l, int sum1, int sum2, int sum3, int quantity,
            List<Filing> localFilings, List<Filing> globalFilings) {
        // this method checks if the current combination has all the parts of the
        // furniture
        if (sum1 >= quantity && sum2 >= quantity && sum3 >= quantity) {
            // this method adds the current combinations to the global combination variable
            filingCombinations.add(new ArrayList<>(localFilings));
            // returns the method
            return;
        }

        // this method runs a for loop through the entire global furniture list
        for (int i = l; i < globalFilings.size(); i++) {
            // checks if the current filing cabinet is already added
            // if it is added then it skips the increment
            if (i > l && globalFilings.get(i) == globalFilings.get(i - 1))
                continue;

            // int variables that are 0 if the current furniture does not have the item and
            // 1 if it does
            int rails = 0, drawers = 0, cabinet = 0;
            // the following if statements adds one to the item if it equals Y
            if (globalFilings.get(i).getRails().equals("Y"))
                rails = 1;
            if (globalFilings.get(i).getDrawers().equals("Y"))
                drawers = 1;
            if (globalFilings.get(i).getCabinet().equals("Y"))
                cabinet = 1;

            // this method adds the current index furniture from the global list to the
            // local list
            localFilings.add(globalFilings.get(i));
            // recursive call to function
            uniqueCombinationFiling(i + 1, sum1 + rails, sum2 + drawers, sum3 + cabinet, quantity, localFilings,
                    globalFilings);
            // removes the last element of the local list
            localFilings.remove(localFilings.size() - 1);
        }
    }

    /**
     * this method determines if a combination is possible and if it is, it assigns
     * the price to the cheapest price var this is specifically for filing
     * 
     * @param category this parameter holds the furniture category so it can be
     *                 passed to update database
     * @return this method return true if
     */
    public static boolean cheapestChair(String category) {
        // if the chair combinations has size equal 0 then it returns false
        if (chairCombinations.size() == 0)
            return false;

        // initialize the price array which holds all the prices
        ArrayList<Integer> prices = new ArrayList<>();
        // this variable holds the identification numbers
        ArrayList<ArrayList<String>> identification = new ArrayList<>();

        // this method runs through all the array lists of all the chairs

        for (List<Chair> chairs : chairCombinations) {
            // temp variable for holding the price
            int temp = 0;
            // temp list for holding the list of String ids
            ArrayList<String> sTemp = new ArrayList<>();
            // looping through the entire furniture array list
            for (Chair chair : chairs) {
                // adds the id to stemp
                sTemp.add(chair.getiD());
                // adds the price to temp
                temp += chair.getPrice();
            }
            // adds the sTemp array list of string to identification
            identification.add(sTemp);
            // adds the price integer to prices list
            prices.add(temp);
        }
        // this methods returns te cheapest array while keeping track of the ids and
        // adding them to the combination
        // string list
        cheapestPrice = cheapest(prices, identification);
        // this method updates the database by removing the id
//        updateDatabase(category);
        // returns true because a combination has been made
        return true;
    }

    /**
     * this method determines if a combination is possible and if it is, it assigns
     * the price to the cheapest price var this is specifically for filing
     * 
     * @param category this parameter holds the furniture category so it can be
     *                 passed to update database
     * @return this method return true if
     */
    public static boolean cheapestDesk(String category) {
        // if the desk combinations has size equal 0 then it returns false
        if (deskCombinations.size() == 0)
            return false;

        // initialize the price array which holds all the prices
        ArrayList<Integer> prices = new ArrayList<>();
        // this variable holds the identification numbers
        ArrayList<ArrayList<String>> identification = new ArrayList<>();

        // this method runs through all the array lists of all the desks

        for (List<Desk> desks : deskCombinations) {
            // temp variable for holding the price
            int temp = 0;
            // temp list for holding the list of String ids
            ArrayList<String> sTemp = new ArrayList<>();
            // looping through the entire furniture array list;
            for (Desk desk : desks) {
                // adds the id to stemp
                sTemp.add(desk.getiD());
                // adds the price to temp
                temp += desk.getPrice();
            }
            // adds the sTemp array list of string to identification
            identification.add(sTemp);
            // adds the price integer to prices list
            prices.add(temp);
        }

        // this methods returns te cheapest array while keeping track of the ids and
        // adding them to the combination
        // string list
        cheapestPrice = cheapest(prices, identification);
        // this method updates the database by removing the id
//        updateDatabase(category);
        // returns true because a combination has been made
        return true;
    }

    /**
     * this method determines if a combination is possible and if it is, it assigns
     * the price to the cheapest price var this is specifically for filing
     * 
     * @param category this parameter holds the furniture category so it can be
     *                 passed to update database
     * @return this method return true if
     */
    public static boolean cheapestLamp(String category) {
        // if the lamp combinations has size equal 0 then it returns false
        if (lampCombinations.size() == 0)
            return false;

        // initialize the price array which holds all the prices
        ArrayList<Integer> prices = new ArrayList<>();
        // this variable holds the identification numbers
        ArrayList<ArrayList<String>> identification = new ArrayList<>();

        // this method runs through all the array lists of all the lamps

        for (List<Lamp> lamps : lampCombinations) {
            // temp variable for holding the price
            int temp = 0;
            // temp list for holding the list of String ids
            ArrayList<String> sTemp = new ArrayList<>();
            // looping through the entire furniture array list
            for (Lamp lamp : lamps) {
                // adds the id to stemp
                sTemp.add(lamp.getiD());
                // adds the price to temp
                temp += lamp.getPrice();
            }
            // adds the sTemp array list of string to identification
            identification.add(sTemp);
            // adds the price integer to prices list
            prices.add(temp);
        }

        // this methods returns te cheapest array while keeping track of the ids and
        // adding them to the combination
        // string list
        cheapestPrice = cheapest(prices, identification);
        // this method updates the database by removing the id
//        updateDatabase(category);
        // returns true because a combination has been made
        return true;
    }

    /**
     * this method determines if a combination is possible and if it is, it assigns
     * the price to the cheapest price var this is specifically for filing
     * 
     * @param category this parameter holds the furniture category so it can be
     *                 passed to update database
     * @return this method return true if
     */
    public static boolean cheapestFiling(String category) {
        // if the filing combinations has size equal 0 then it returns false
        if (filingCombinations.size() == 0)
            return false;

        // initialize the price array which holds all the prices
        ArrayList<Integer> prices = new ArrayList<>();
        // this variable holds the identification numbers
        ArrayList<ArrayList<String>> identification = new ArrayList<>();

        // this method runs through all the array lists of all the filings
        for (List<Filing> filings : filingCombinations) {
            // temp variable for holding the price
            int temp = 0;
            // temp list for holding the list of String ids
            ArrayList<String> sTemp = new ArrayList<>();
            // looping through the entire furniture array list
            for (Filing filing : filings) {
                // adds the id to stemp
                sTemp.add(filing.getiD());
                // adds the price to temp
                temp += filing.getPrice();
            }
            // adds the sTemp array list of string to identification
            identification.add(sTemp);
            // adds the price integer to prices list
            prices.add(temp);
        }

        // this methods returns te cheapest array while keeping track of the ids and
        // adding them to the combination
        // string list
        cheapestPrice = cheapest(prices, identification);
        // this method updates the database by removing the id
//        updateDatabase(category);
        // returns true because a combination has been made
        return true;
    }

    /**
     * This method determines teh cheapest price combination from the input prices
     * array while keeping track of the identification for the furniture
     * 
     * @param prices         the prices list which holds all the prices
     * @param identification the identification list to track all the ids in the
     *                       list
     * @return this method returns the cheapest price out of all the combinations
     */
    public static int cheapest(ArrayList<Integer> prices, ArrayList<ArrayList<String>> identification) {
        int lowest = 0;
        for (int i = 0; i < prices.size(); i++) {
            if (lowest == 0) {
                lowest = prices.get(i);
            } else {
                lowest = Math.min(lowest, prices.get(i));
                combination = (List<String>) identification.get(i).clone();
            }
        }
        return lowest;
    }

    /**
     * This method calls the update method of inventory and removes the furniture
     * IDs used in combination
     * 
     * @param category this is the specific furniture
     */
    public static void updateDatabase(String category) {
        // this loops through the entire combination array
        for (String s : combination) {
            // removing the specific furniture item in the database
            inventory.update(category, s);
        }
    }
}