package edu.ucalgary.ensf409.tests;

import edu.ucalgary.ensf409.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to test all the classes in the model package
 * @author Shamis Ali
 *         <a href="mailto:shamis.ali1@ucalgary.ca">shamis.ali@ucalgary.ca</a>
 * @version 2.1
 * @since 1.0
 */

public class TestModel {

    /**
     * The tests below namely: evaluateEntryCorrect() and evaluateEntryIncorrect()
     * are both testing 3 methods in Inventory. Firstly,its constructor to check whether a successful
     * connection is made.Secondly, it is checking its ability to fill the arrays. Now since for this the methods
     * fillArrays() is called so in turn 5 methods are called. We are testing manuFill() to test whether all the
     * arrays are correctly filled. Now when this is done we test the getters in Manufacturer class to
     * match with the manually created list.
     *
     * Now the evaluateEntryCorrect() purpose is to correctly check whether the two match correctly
     *
     * The evaluateEntryIncorrect() purpose is to prove that when we change something in the manually
     * created list something doesn"t match and thus test passes by failing
     */

    @Test
    public void evaluateEntryCorrect() {
        Inventory testInvent = new Inventory("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        List<Manufacturer> testManu = testInvent.getManufacturer();
        List<Manufacturer> manu = new ArrayList<>();
        manu.add(new Manufacturer("001", "Academic Desks", "236-145-2542", "BC"));
        manu.add(new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB"));
        manu.add(new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON"));
        manu.add(new Manufacturer("004", "Furniture Goods", "306-512-5508", "SK"));
        manu.add(new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB"));
        boolean match=true;
        for(int i=0;i<manu.size();i++){
            if(!(manu.get(i).getManuID().equals(testManu.get(i).getManuID()) &&
                    manu.get(i).getName().equals(testManu.get(i).getName()) &&
                    manu.get(i).getPhone().equals(testManu.get(i).getPhone()) &&
                    manu.get(i).getProvince().equals(testManu.get(i).getProvince())
            )){
                match=false;
                break;
            }
        }
        Assert.assertTrue("This test has failed because the correct lists do not match",match);


    }

    @Test
    public void evaluateEntryIncorrect() {
        Inventory testInvent = new Inventory("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        List<Manufacturer> testManu = testInvent.getManufacturer();
        List<Manufacturer> manu = new ArrayList<>();
        manu.add(new Manufacturer("001", "Academic Desks", "236-145-2542", "BC"));
        manu.add(new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB"));
        manu.add(new Manufacturer("003", "Chairs R Not Us", "705-667-9481", "ON"));
        manu.add(new Manufacturer("007", "Furniture Goods", "306-512-5508", "SK"));
        manu.add(new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB"));
        boolean match=true;
        for(int i=0;i<manu.size();i++){
            if(!(manu.get(i).getManuID().equals(testManu.get(i).getManuID()) &&
                    manu.get(i).getName().equals(testManu.get(i).getName()) &&
                    manu.get(i).getPhone().equals(testManu.get(i).getPhone()) &&
                    manu.get(i).getProvince().equals(testManu.get(i).getProvince())
            )){
                match=false;
                break;
            }
        }
        Assert.assertFalse("This test has failed because incorrect lists matched",match);


    }

    /**
     * The purpose of this test is to check whether the table is being correctly updated.
     * So what we do is we remove an entry and cross match both the lists to see if that entry
     * is removed or not
     */

    @Test
    public void update() {
        Inventory testInvent = new Inventory("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        List<Chair> testChairOne = testInvent.getChair();
        testInvent.update("Chair","C7268");
        List<Chair> testChairTwo = testInvent.getChair();
        boolean match=testChairOne.equals(testChairTwo);

        Assert.assertFalse("This test has failed because ID is incorrect and/or is already removed",match);


    }

    /**
     * The tests below namely: evaluateChairEntry()
     * are both testing 3 methods in Inventory. Firstly,its constructor to check whether a successful
     * connection is made.Secondly, it is checking its ability to fill the arrays. Now since for this the methods
     * fillArrays() is called so in turn 5 methods are called. We are testing chairFill() to test whether all the
     * arrays are correctly filled. Now when this is done we test the getters in Chair class to
     * match with the manually created list.
     *
     * Now the evaluateChairEntry() purpose is to correctly check whether the two match correctly
     *
     */

    @Test
    public void evaluateChairEntry(){
        Inventory testInvent = new Inventory("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        List<Chair> testChair = testInvent.getChair();
        List<Chair> chair = new ArrayList<>();
        chair.add(new Chair("C1320",	"Kneeling",	"Y",	"N",	"N",	"N",	50,	"002"));
        chair.add(new Chair("C3405",	"Task",	"Y",	"Y",	"N",	"N",	100,	"003"));
        chair.add(new Chair("C9890",	"Mesh",	"N",	"Y",	"N",	"Y",	50,	"003"));
        chair.add(new Chair("C7268",	"Executive",	"N",	"N",	"Y",	"N",	75,	"004"));
        chair.add(new Chair("C0942",	"Mesh",	"Y",	"N",	"Y",	"Y",	100,	"005"));
        chair.add(new Chair("C4839",	"Ergonomic",	"N",	"N",	"N",	"Y",	50,	"002"));
        chair.add(new Chair("C2483",	"Executive",	"Y",	"Y",	"N",	"N",	175,	"002"));
        chair.add(new Chair("C5789",	"Ergonomic",	"Y",	"N",	"N",	"Y",	125,	"003"));
        chair.add(new Chair("C3819",	"Kneeling",	"N",	"N",	"Y",	"N",	75,	"005"));
        chair.add(new Chair("C5784",	"Executive",	"Y",	"N",	"N",	"Y",	150,	"004"));
        chair.add(new Chair("C6748",	"Mesh",	"Y",	"N",	"N",	"N",	75,	"003"));
        chair.add(new Chair("C0914",	"Task",	"N",	"N",	"Y",	"Y",	50,	"002"));
        chair.add(new Chair("C0914",	"Task",	"N",	"N",	"Y",	"Y",	50,	"002"));
        chair.add(new Chair("C1148",	"Task",	"Y",	"N",	"Y",	"Y",	125,	"003"));
        chair.add(new Chair("C5409",	"Ergonomic",	"Y",	"Y",	"Y",	"N",	200,	"003"));
        chair.add(new Chair("C8138",	"Mesh",	"N",	"N",	"Y",	"N",	75,	"005"));
        boolean match=true;
        for(int i=0;i<chair.size();i++){
            if(!(chair.get(i).getArms().equals(testChair.get(i).getArms())&&
                    chair.get(i).getCushion().equals(testChair.get(i).getCushion())&&
                    chair.get(i).getiD().equals(testChair.get(i).getiD())&&
                    chair.get(i).getLegs().equals(testChair.get(i).getLegs())&&
                    chair.get(i).getSeat().equals(testChair.get(i).getSeat())&&
                    chair.get(i).getType().equals(testChair.get(i).getType())&&
                    chair.get(i).getPrice()==testChair.get(i).getPrice()&&
                    chair.get(i).getManuID().equals(testChair.get(i).getManuID()))){
                match=false;
                break;

            }
        }
        Assert.assertFalse("This test has failed because incorrect lists matched",match);

    }

    /**
     * The tests below namely: evaluateLampEntry()
     * are both testing 3 methods in Inventory. Firstly,its constructor to check whether a successful
     * connection is made.Secondly, it is checking its ability to fill the arrays. Now since for this the methods
     * fillArrays() is called so in turn 5 methods are called. We are testing lampFill() to test whether all the
     * arrays are correctly filled. Now when this is done we test the getters in Lamp class to
     * match with the manually created list.
     *
     * Now the evaluateLampEntry() purpose is to correctly check whether the two match correctly
     *
     */

    @Test
    public void evaluateLampEntry(){
        Inventory testInvent = new Inventory("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        List<Lamp> testLamp = testInvent.getLamp();
        List<Lamp> lamp = new ArrayList<>();
        lamp.add(new Lamp("L132",	"Desk",	"Y",	"N",	18,	"005"));
        lamp.add(new Lamp("L980",	"Study",	"N",	"Y",	2,	"004"));
        lamp.add(new Lamp("L487",	"Swing Arm",	"Y",	"N",	27,	"002"));
        lamp.add(new Lamp("L564",	"Desk",	"Y",	"Y",	20,	"004"));
        lamp.add(new Lamp("L342",	"Desk",	"N",	"Y",	2,	"002"));
        lamp.add(new Lamp("L982",	"Study",	"Y",	"N",	8,	"002"));
        lamp.add(new Lamp("L879",	"Swing Arm",	"N",	"Y",	3,	"005"));
        lamp.add(new Lamp("L208",	"Desk",	"N",	"Y",	2,	"005"));
        lamp.add(new Lamp("L223",	"Study",	"N",	"Y",	2,	"005"));
        lamp.add(new Lamp("L928",	"Study",	"Y",	"Y",	10,	"002"));
        lamp.add(new Lamp("L013",	"Desk",	"Y",	"N",	18,	"004"));
        lamp.add(new Lamp("L053",	"Swing Arm",	"Y",	"N",	27,	"002"));
        lamp.add(new Lamp("L112",	"Desk",	"Y",	"N",	18,	"005"));
        lamp.add(new Lamp("L649",	"Desk",	"Y",	"N",	18,	"004"));
        lamp.add(new Lamp("L096",	"Swing Arm",	"N",	"Y",	3,	"002"));
        boolean match=true;
        for(int i=0;i<lamp.size();i++){
            if(!(lamp.get(i).getiD().equals(testLamp.get(i).getiD())&&
                    lamp.get(i).getType().equals(testLamp.get(i).getType())&&
                    lamp.get(i).getBase().equals(testLamp.get(i).getBase())&&
                    lamp.get(i).getBulb().equals(testLamp.get(i).getBulb())&&
                    lamp.get(i).getManuID().equals(testLamp.get(i).getManuID())&&
                    lamp.get(i).getPrice()==testLamp.get(i).getPrice())
            ){
                match=false;
                break;
            }
        }
        Assert.assertFalse("This test has failed because incorrect lists matched",match);


    }

    /**
     * The tests below namely: evaluateDeskEntry()
     * are both testing 3 methods in Inventory. Firstly,its constructor to check whether a successful
     * connection is made.Secondly, it is checking its ability to fill the arrays. Now since for this the methods
     * fillArrays() is called so in turn 5 methods are called. We are testing deskFill() to test whether all the
     * arrays are correctly filled. Now when this is done we test the getters in Desk class to
     * match with the manually created list.
     *
     * Now the evaluateDeskEntry() purpose is to correctly check whether the two match correctly
     *
     */

    @Test
    public void evaluateDeskEntry(){
        Inventory testInvent = new Inventory("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        List<Desk> testDesk = testInvent.getDesk();
        List<Desk> desk = new ArrayList<>();
        desk.add(new Desk("D3820",	"Standing",	"Y",	"N",	"N",	150,	"001"));
        desk.add(new Desk("D4475",	"Adjustable",	"N",	"Y",	"Y",	200,	"002"));
        desk.add(new Desk("D0890",	"Traditional",	"N",	"N",	"Y",	25,	"002"));
        desk.add(new Desk("D2341",	"Standing",	"N",	"Y",	"N",	100,	"001"));
        desk.add(new Desk("D9387",	"Standing",	"Y",	"Y",	"N",	250,	"004"));
        desk.add(new Desk("D7373",	"Adjustable",	"Y",	"Y",	"N",	350,	"005"));
        desk.add(new Desk("D2746",	"Adjustable",	"Y",	"N",	"Y",	250,	"004"));
        desk.add(new Desk("D9352",	"Traditional",	"Y",	"N",	"Y",	75,	"002"));
        desk.add(new Desk("D4231",	"Traditional",	"N",	"Y",	"Y",	50,	"005"));
        desk.add(new Desk("D8675",	"Traditional",	"Y",	"Y",	"N",	75,	"001"));
        desk.add(new Desk("D1927",	"Standing",	"Y",	"N",	"Y",	200,	"005"));
        desk.add(new Desk("D1030",	"Adjustable",	"N",	"Y",	"N",	150,	"002"));
        desk.add(new Desk("D4438",	"Standing",	"N",	"Y",	"Y",	150,	"004"));
        desk.add(new Desk("D5437",	"Adjustable",	"Y",	"N",	"N",	50,	"001"));
        desk.add(new Desk("D3682",	"Adjustable",	"N",	"N",	"Y",	50,	"005"));
        boolean match=true;
        for(int i=0;i<desk.size();i++){
            if(!(desk.get(i).getiD().equals(testDesk.get(i).getiD())&&
                    desk.get(i).getType().equals(testDesk.get(i).getType())&&
                    desk.get(i).getLegs().equals(testDesk.get(i).getLegs())&&
                    desk.get(i).getTop().equals(testDesk.get(i).getTop())&&
                    desk.get(i).getDrawer().equals(testDesk.get(i).getDrawer())&&
                    desk.get(i).getManuID().equals(testDesk.get(i).getManuID())&&
                    desk.get(i).getPrice()==testDesk.get(i).getPrice())
            ){
                match=false;
                break;
            }
        }
        Assert.assertFalse("This test has failed because incorrect lists matched",match);


    }

    /**
     * The tests below namely: evaluateFilingEntry()
     * are both testing 3 methods in Inventory. Firstly,its constructor to check whether a successful
     * connection is made.Secondly, it is checking its ability to fill the arrays. Now since for this the methods
     * fillArrays() is called so in turn 5 methods are called. We are testing filingFill() to test whether all the
     * arrays are correctly filled. Now when this is done we test the getters in Filing class to
     * match with the manually created list.
     *
     * Now the evaluateFilingEntry() purpose is to correctly check whether the two match correctly
     *
     */

    @Test
    public void evaluateFilingEntry(){
        Inventory testInvent = new Inventory("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        List<Filing> testfiling = testInvent.getFiling();
        List<Filing> filing = new ArrayList<>();
        filing.add(new Filing("F001",	"Small",	"Y",	"N",	"Y",	50,	"005"));
        filing.add(new Filing("F002",	"Medium",	"N",	"Y",	"N",	100,	"004"));
        filing.add(new Filing("F003",	"Large",	"N",	"Y",	"N",	150,	"002"));
        filing.add(new Filing("F004",	"Small",	"N",	"Y",	"Y",	75,	"004"));
        filing.add(new Filing("F005",	"Small",	"Y",	"Y",	"N",	75,	"005"));
        filing.add(new Filing("F006",	"Small",	"Y",	"N",	"Y",	50,	"005"));
        filing.add(new Filing("F007",	"Medium",	"N",	"Y",	"Y",	150,	"002"));
        filing.add(new Filing("F008",	"Medium",	"Y",	"N",	"N",	50,	"005"));
        filing.add(new Filing("F009",	"Medium",	"Y",	"N",	"Y",	150,	"004"));
        filing.add(new Filing("F010",	"Large",	"Y",	"Y",	"N",	225,	"002"));
        filing.add(new Filing("F011",	"Large",	"N",	"Y",	"Y",	225,	"005"));
        filing.add(new Filing("F012",	"Large",	"N",	"N",	"Y",	75,	"005"));
        filing.add(new Filing("F013",	"Small",	"N",	"Y",	"N",	50,	"002"));
        filing.add(new Filing("F014",	"Medium",	"Y",	"Y",	"Y",	200,	"002"));
        filing.add(new Filing("F015",	"Large",	"Y",	"N",	"N",	75,	"004"));
        boolean match=true;
        for(int i=0;i<filing.size();i++){
            if(!(filing.get(i).getiD().equals(testfiling.get(i).getiD())&&
                    filing.get(i).getType().equals(testfiling.get(i).getType())&&
                    filing.get(i).getRails().equals(testfiling.get(i).getRails())&&
                    filing.get(i).getCabinet().equals(testfiling.get(i).getCabinet())&&
                    filing.get(i).getDrawers().equals(testfiling.get(i).getDrawers())&&
                    filing.get(i).getManuID().equals(testfiling.get(i).getManuID())&&
                    filing.get(i).getPrice()==testfiling.get(i).getPrice())
            ){
                match=false;
                break;
            }
        }
        Assert.assertFalse("This test has failed because incorrect lists matched",match);

    }


}
