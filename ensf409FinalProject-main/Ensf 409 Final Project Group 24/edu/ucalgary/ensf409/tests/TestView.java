package edu.ucalgary.ensf409.tests;

import edu.ucalgary.ensf409.view.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class is used to test the UI class
 *
 * @author Ahad Ali
 *         <a href="mailto:ahad.ali@ucalgary.ca">ahad.ali@ucalgary.ca</a>
 * @version 2.4
 * @since 1.0
 */

public class TestView {

    /**
     * Since in UI, most methods are user input dependent, testing the helper/
     * utility methods. Check if the isNumeric() method in UI.java accounts for
     * string as not numeric
     */
    @Test
    public void testIsNumericInvalidString() {
        boolean expected = false;
        boolean actual = UI.isNumeric("abc");
        assertEquals("Error, the isNumeric() method did not work", expected, actual);
    }

    /**
     * Check if the isNumeric() method in UI.java accounts for float as not numeric/
     * integer
     */
    @Test
    public void testIsNumericInvalidFloat() {
        boolean expected = false;
        boolean actual = UI.isNumeric("1.5");
        assertEquals("Error, the isNumeric() method did not work", expected, actual);
    }

    /**
     * Check if the isNumeric() method in UI.java accounts for a positive whole
     * number as numeric
     */
    @Test
    public void testIsNumericValidPositive() {
        boolean expected = true;
        boolean actual = UI.isNumeric("123");
        assertEquals("Error, the isNumeric() method did not work", expected, actual);
    }

    /**
     * Check if the isNumeric() method in UI.java accounts for a negative whole
     * number as numeric
     */
    @Test
    public void testIsNumericValidNegative() {
        boolean expected = true;
        boolean actual = UI.isNumeric("-456");
        assertEquals("Error, the isNumeric() method did not work", expected, actual);
    }

    /**
     * Check if the getArray() method in UI.java works when furniture choice is
     * Chair which is a valid furniture choice
     */
    @Test
    public void testGetArrayChair() {
        String[] expected = { "Ergonomic", "Executive", "Kneeling", "Mesh", "Task" };
        String[] actual = UI.getArray("Chair");
        assertEquals("Error, the getArray() method did not work", expected, actual);
    }

    /**
     * Check if the getArray() method in UI.java works when furniture choice is Desk
     * which is a valid furniture choice
     */
    @Test
    public void testGetArrayDesk() {
        String[] expected = { "Adjustable", "Standing", "Traditional" };
        String[] actual = UI.getArray("Desk");
        assertEquals("Error, the getArray() method did not work", expected, actual);
    }

    /**
     * Check if the getArray() method in UI.java works when furniture choice is
     * Filing which is a valid furniture choice
     */
    @Test
    public void testGetArrayFiling() {
        String[] expected = { "Small", "Medium", "Large" };
        String[] actual = UI.getArray("Filing");
        assertEquals("Error, the getArray() method did not work", expected, actual);
    }

    /**
     * Check if the getArray() method in UI.java works when furniture choice is Lamp
     * which is a valid furniture choice
     */
    @Test
    public void testGetArrayLamp() {
        String[] expected = { "Desk", "Study", "Swing Arm" };
        String[] actual = UI.getArray("Lamp");
        assertEquals("Error, the getArray() method did not work", expected, actual);
    }

    /**
     * Check if the getArray() method in UI.java works when furniture choice is not
     * a valid choice which means getArray() should return null
     */
    @Test
    public void testGetArrayFail() {
        String[] expected = null;
        String[] actual = UI.getArray("Invalid choice");
        assertEquals("Error, the getArray() method did not work", expected, actual);
    }
}
