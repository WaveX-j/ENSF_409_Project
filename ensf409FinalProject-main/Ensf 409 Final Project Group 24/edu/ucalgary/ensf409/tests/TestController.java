package edu.ucalgary.ensf409.tests;

import edu.ucalgary.ensf409.controller.Logic;
import edu.ucalgary.ensf409.model.*;
import edu.ucalgary.ensf409.view.*;
import org.junit.Assert;
import org.junit.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the UI class
 *
 * @author Harsh Sharma
 *         <a href="mailto:harshit.sharma@ucalgary.ca">harshit.sharma@ucalgary.ca</a>
 * @author Usman Khan
 *         <a href="mailto:muhammad.khan@ucalgary.ca">muhammad.khan@ucalgary.ca</a>
 * @version 1.5
 * @since 1.0
 */
public class TestController {

    /**
     * This method tests what happens if the wrong furniture category is passed to specific furniture method
     */
    @Test
    public void testIsSpecificFurnitureCategory() {
        Logic logic = new Logic();
        boolean expected = false;
        boolean actual = logic.specificFurniture("bed", "king", 1);
        assertEquals("Error, the specific furniture category did not work", expected, actual);
    }

    /**
     *  This method checks what happens if a correct category is passed to specific furniture
     */
    @Test
    public void testIsSpecificFurnitureType() {
        Logic logic = new Logic();
        Input INPUT = new Input("Chair", "Task", 1, "scm", "ensf409");
        logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        boolean expected = true;
        boolean actual = logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        assertEquals("Error, the specific furniture category method did not work", expected, actual);
    }

    /**
     *  This method checks if chair list is correct
     */
    @Before
    public void testReadChairData() {
        Logic logic = new Logic();
        boolean expected = true;
        Input INPUT = new Input("chair", "Mesh", 1, "scm", "ensf409");
        logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        List<Chair> expectedList = new ArrayList<>();
        expectedList.add(new Chair("C0942",	"Mesh",	"Y",	"N",	"Y",	"Y",	175,	"005"));
        expectedList.add(new Chair("C6748",	"Mesh",	"Y",	"N",	"N",	"N",	75,	"003"));
        expectedList.add(new Chair("C8138",	"Mesh",	"N",	"N",	"Y",	"N",	75,	"005"));
        expectedList.add(new Chair("C9890",	"Mesh",	"N",	"Y",	"N",	"Y",	50,	"003"));
        List<Chair> actualList = logic.readChairData("Mesh");
        boolean actual = actualList.equals(expectedList);
        assertEquals("Error, the chair fill method did not work", expected, actual);
    }

    /**
     * This method checks if chair list is correct
     */
    @Before
    public void testReadChairDataEmpty() {
        Logic logic = new Logic();
        boolean expected = true;
        Input INPUT = new Input("chair", "Mesh", 1, "scm", "ensf409");
        logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        List<Chair> expectedList = new ArrayList<>();
        List<Chair> actualList = logic.readChairData("Unknown");
        boolean actual = actualList.equals(expectedList);
        assertEquals("Error, the chair fill method did not work", expected, actual);
    }

    /**
     *  This method checks if desk list is correct
     */
    @Before
    public void testReadDeskData() {
        Logic logic = new Logic();
        boolean expected = true;
        Input INPUT = new Input("desk", "Standing", 1, "scm", "ensf409");
        logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        List<Desk> expectedList = new ArrayList<>();
        expectedList.add(new Desk("D1927",	"Standing",	"Y",	"N",	"Y",	200,	"005"));
        expectedList.add(new Desk("D2341",	"Standing",	"N",	"Y",	"N",	100,	"001"));
        expectedList.add(new Desk("D3820",	"Standing",	"Y",	"N",	"N",	150,	"001"));
        expectedList.add(new Desk("D4438",	"Standing",	"N",	"Y",	"Y",	150,	"004"));
        expectedList.add(new Desk("D9387",	"Standing",	"Y",	"Y",	"N",	250,	"004"));
        List<Desk> actualList = logic.readDeskData("Standing");
        boolean actual = true;
        for(int i=0;i<actualList.size();i++){
            if(!(actualList.get(i).getiD().equals(expectedList.get(i).getiD())&&
                    actualList.get(i).getType().equals(expectedList.get(i).getType())&&
                    actualList.get(i).getLegs().equals(expectedList.get(i).getLegs())&&
                    actualList.get(i).getTop().equals(expectedList.get(i).getTop())&&
                    actualList.get(i).getDrawer().equals(expectedList.get(i).getDrawer())&&
                    actualList.get(i).getManuID().equals(expectedList.get(i).getManuID())&&
                    actualList.get(i).getPrice()==expectedList.get(i).getPrice())
            ){
                actual=false;
                break;
            }
        }
        assertEquals("Error, the desk fill method did not work", expected, actual);
    }

    /**
     * This method checks if desk list is correct
     */
    @Before
    public void testReadDeskDataEmpty() {
        boolean expected = true;
        Input INPUT = new Input("desk", "Standing", 1, "scm", "ensf409");
        Logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        List<Desk> expectedList = new ArrayList<>();
        List<Desk> actualList = Logic.readDeskData("Unknown");
        boolean actual = actualList.equals(expectedList);
        assertEquals("Error, the desk fill method did not work", expected, actual);
    }

    /**
     *  This method checks if lamp list is correct
     */
    @Before
    public void testReadLampData() {
        Logic logic = new Logic();
        boolean expected = true;
        Input INPUT = new Input("lamp", "Desk", 1, "scm", "ensf409");
        logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        List<Lamp> expectedList = new ArrayList<>();
        expectedList.add(new Lamp("L013",	"Desk",	"Y",	"N",	18,	"004"));
        expectedList.add(new Lamp("L112",	"Desk",	"Y",	"N",	18,	"005"));
        expectedList.add(new Lamp("L132",	"Desk",	"Y",	"N",	18,	"005"));
        expectedList.add(new Lamp("L208",	"Desk",	"N",	"Y",	2,	"005"));
        expectedList.add(new Lamp("L342",	"Desk",	"N",	"Y",	2,	"002"));
        expectedList.add(new Lamp("L564",	"Desk",	"Y",	"Y",	20,	"004"));
        expectedList.add(new Lamp("L649",	"Desk",	"Y",	"N",	18,	"004"));
        List<Lamp> actualList = logic.readLampData("Desk");
        boolean actual = true;
        for(int i=0;i<actualList.size();i++){
            if(!(actualList.get(i).getiD().equals(expectedList.get(i).getiD())&&
                    actualList.get(i).getType().equals(expectedList.get(i).getType())&&
                    actualList.get(i).getBase().equals(expectedList.get(i).getBase())&&
                    actualList.get(i).getBulb().equals(expectedList.get(i).getBulb())&&
                    actualList.get(i).getManuID().equals(expectedList.get(i).getManuID())&&
                    actualList.get(i).getPrice()==expectedList.get(i).getPrice())
            ){
                actual=false;
                break;
            }
        }
        assertEquals("Error, the lamp fill method did not work", expected, actual);
    }

    /**
     * This method checks if lamp list is correct
     */
    @Before
    public void testReadLampDataEmpty() {
        boolean expected = true;
        Input INPUT = new Input("lamp", "Desk", 1, "scm", "ensf409");
        Logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        List<Lamp> expectedList = new ArrayList<>();
        List<Lamp> actualList = Logic.readLampData("Unknown");
        boolean actual = actualList.equals(expectedList);
        assertEquals("Error, the lamp fill method did not work", expected, actual);
    }



    /**
     * This method checks if the cheapest price for a chair equals what is expected
     */
    @Test
    public void checkCheapestChairPrice() {
        Logic logic = new Logic();
        int expected = 150;
        Input INPUT = new Input("chair", "Task", 1, "scm", "ensf409");
        logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        int actual = logic.cheapestPrice;
        assertEquals("Error, the cheapest chair method did not work", expected, actual);
    }

    /**
     * This method checks if the cheapest price for a desk equals what is expected
     */
    @Test
    public void checkCheapestDeskPrice() {
        Logic logic = new Logic();
        int expected = 100;
        Input INPUT = new Input("desk", "Traditional", 1, "scm", "ensf409");
        logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        int actual = logic.cheapestPrice;
        assertEquals("Error, the cheapest desk method did not work", expected, actual);
    }

    /**
     * This method checks if the cheapest price for a lamp equals what is expected
     */
    @Test
    public void checkCheapestLampPrice() {
        Logic logic = new Logic();
        int expected = 10;
        Input INPUT = new Input("lamp", "Study", 1, "scm", "ensf409");
        logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        int actual = logic.cheapestPrice;
        assertEquals("Error, the cheapest lamp method did not work", expected, actual);
    }

    /**
     * This method checks if the cheapest price for a filing equals what is expected
     */
    @Test
    public void checkCheapestFilingPrice() {
        Logic logic = new Logic();
        int expected = 100;
        Input INPUT = new Input("filing", "Small", 1, "scm", "ensf409");
        logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        int actual = logic.cheapestPrice;
        assertEquals("Error, the cheapest filing method did not work", expected, actual);
    }

    /**
     * The method check what happens chair is possible
     */
    @Test
    public void testCheapestChairMesh() {
        Input INPUT = new Input("chair", "Mesh", 1, "scm", "ensf409");
        Logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        boolean expected = true;
        boolean actual = Logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        assertEquals("Error, the cheapest chair method did not work", expected, actual);
    }

    /**
     *  This method checks what happens if chair is not possible
     */
    @Test
    public void testCheapestChairKneeling() {
        Input INPUT = new Input("chair", "Kneeling", 1, "scm", "ensf409");
        Logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        boolean expected = false;
        boolean actual = Logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        assertEquals("Error, the cheapest chair method did not work", expected, actual);
    }

    /**
     * The method check what happens desk is possible
     */
    @Test
    public void testCheapestDeskEmpty() {
        Input INPUT = new Input("desk", "Adjustable", 2, "scm", "ensf409");
        Logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        boolean expected = true;
        boolean actual = Logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        assertEquals("Error, the cheapest desk method did not work", expected, actual);
    }

    /**
     *  This method checks what happens if desk is not possible
     */
    @Test
    public void testCheapestDeskFilled() {
        Input INPUT = new Input("desk", "Standing", 5, "scm", "ensf409");
        Logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        boolean expected = false;
        boolean actual = Logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        assertEquals("Error, the cheapest desk method did not work", expected, actual);
    }

    /**
     * The method check what happens lamp is possible
     */
    @Test
    public void testCheapestLampEmpty() {
        Input INPUT = new Input("lamp", "Desk", 1, "scm", "ensf409");
        Logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        boolean expected = true;
        boolean actual = Logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        assertEquals("Error, the cheapest lamp method did not work", expected, actual);
    }

    /**
     *  This method checks what happens if lamp is not possible
     */
    @Test
    public void testCheapestLampFilled() {
        Input INPUT = new Input("lamp", "Study", 3, "scm", "ensf409");
        Logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        boolean expected = false;
        boolean actual = Logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        assertEquals("Error, the cheapest lamp method did not work", expected, actual);
    }

    /**
     * The method check what happens filing is possible
     */
    @Test
    public void testCheapestFilingEmpty() {
        Input INPUT = new Input("filing", "Medium", 1, "scm", "ensf409");
        Logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        boolean expected = true;
        boolean actual = Logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        assertEquals("Error, the cheapest filing method did not work", expected, actual);
    }

    /**
     * This method checks what happens if filing is not possible
     */
    @Test
    public void testCheapestFilingFilled() {
        Input INPUT = new Input("filing", "Small", 4, "scm", "ensf409");
        Logic.inventory = new Inventory("jdbc:mysql://localhost/inventory", INPUT.getUsername(), INPUT.getPassword());
        boolean expected = false;
        boolean actual = Logic.specificFurniture(INPUT.getFurnitureChoice(), INPUT.getType(), INPUT.getNumberOfItems());
        assertEquals("Error, the cheapest filing method did not work", expected, actual);
    }



    /**
     * This method updates the database by removing the lamp whose id's are in combination
     */
    @Test
    public void testUpdateDatabase() {
        boolean expected = false;
        Logic.combination = new ArrayList<>();
        Logic.combination.add("F002");
        List<Filing> expectedList = new ArrayList<>();
        expectedList.add(new Filing("F007",	"Medium",	"N",	"Y",	"Y",	150,	"002"));
        expectedList.add(new Filing("F008",	"Medium",	"Y",	"N",	"N",	50,	"005"));
        expectedList.add(new Filing("F009",	"Medium",	"Y",	"N",	"Y",	150,	"004"));
        expectedList.add(new Filing("F014",	"Medium",	"Y",	"Y",	"Y",	200,	"002"));
        Logic.updateDatabase("Lamp");
        List<Filing> actualList = Logic.readFilingData("Medium");
        boolean actual = actualList.equals(expectedList);;
        assertEquals("Error, the update database method did not work", expected, actual);
    }
}
