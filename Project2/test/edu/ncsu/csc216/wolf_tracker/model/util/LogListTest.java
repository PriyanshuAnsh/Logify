package edu.ncsu.csc216.wolf_tracker.model.util;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class that contains unit tests for the LogList class.
 * This class uses JUnit to verify the behavior of the LogList implementation.
 * @author Priyanshu Dongres
 */
public class LogListTest {

	 /**
     * The LogList instance used for testing.
     * This field will be initialized before each test method to ensure a fresh instance.
     */
    private LogList<String> logList;

    /**
     * An array of testing strings representing various log entries.
     * This array is used to populate the LogList with sample data for testing purposes.
     */
    
	private String[] testingStrings = {"Cook", "Study", "Watch TV", "Scroll Instagram", "Work on Personal Project", "Return IPAD", 
										"Go on Date", "Go to GYM", "Dispose Trash", "Wash Dishes"};
	
	/**
     * Sets up a new LogList before each test.
     */
	@BeforeEach
	public void setUp() {
		logList = new LogList<>();
	}
	
	/**
     * Tests the addLog method to ensure logs are added correctly.
     * Verifies the size of the log list after adding logs.
     */
	@Test
	public void testAddLog() {
		for(String test: testingStrings) {
			logList.addLog(test);
		}
		
		assertEquals(10, logList.size());
		
		logList.addLog("Do Laundary");
		assertEquals(11, logList.size());
	}
	
	/**
     * Tests the getLog method to ensure logs are retrieved correctly.
     * Verifies that adding a null log throws a NullPointerException.
     * Also checks for proper handling of invalid indices.
     */
	@Test
	public void testGetLog() {
		
		NullPointerException exception = assertThrows(NullPointerException.class, () -> logList.addLog(null));
		
		assertEquals("Cannot add null element.", exception.getMessage());
		for(String log: testingStrings) {
			logList.addLog(log);
		}
		
		for(int i = 0; i < testingStrings.length; i++) {
			assertEquals(testingStrings[i], logList.getLog(i));
		}
		
		assertEquals("Dispose Trash", logList.getLog(8));
		
		IndexOutOfBoundsException exception2 = assertThrows(IndexOutOfBoundsException.class, () -> logList.getLog(12));
		assertEquals("Invalid index.", exception2.getMessage());
	}
	
	/**
     * Tests the removeLog method to ensure logs are removed correctly.
     * Verifies the size of the log list after removals and checks for proper handling of invalid indices.
     */
	@Test
	public void testRemoveLog() {
		assertEquals(0, logList.size());
		IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> logList.removeLog(0));
		assertEquals("Invalid index.", exception.getMessage());
		for(String log: testingStrings) {
			logList.addLog(log);
		}
		assertEquals(10, logList.size());
		int size = 10;
		
		for(int i = testingStrings.length - 1; i > -1; i--) {
			assertEquals(testingStrings[i], logList.removeLog(i));
			assertEquals(--size, logList.size());
		}
		
		assertEquals(0, logList.size());
		for(String log: testingStrings) {
			logList.addLog(log);
		}
		
		logList.removeLog(4);
		
		String[] afterRemovalStrings = {"Cook", "Study", "Watch TV", "Scroll Instagram", "Return IPAD", 
				"Go on Date", "Go to GYM", "Dispose Trash", "Wash Dishes"};
		
		assertEquals(9, logList.size());
		
		for(int i = 0; i < afterRemovalStrings.length; i++) {
			assertEquals(afterRemovalStrings[i], logList.getLog(i));
		}
		
		IndexOutOfBoundsException exception2 = assertThrows(IndexOutOfBoundsException.class, () -> logList.removeLog(-10));
		assertEquals("Invalid index.", exception2.getMessage());
		
		logList.addLog("Do Something");
		assertEquals(10, logList.size());
		assertEquals("Do Something", logList.removeLog(9));
		assertEquals(9, logList.size());
	}
	
	/**
     * Tests the setLog method to ensure logs can be updated correctly.
     * Verifies that setting a log at an invalid index or with a null value throws the appropriate exceptions.
     */
	@Test
	public void testSetLog() {
		IndexOutOfBoundsException e1 = assertThrows(IndexOutOfBoundsException.class, () -> logList.setLog(0, "hello"));
		assertEquals("Invalid index.", e1.getMessage());
		
		logList.addLog("Don't Waste Time");
		NullPointerException e2 = assertThrows(NullPointerException.class, () -> logList.setLog(0, null));
		assertEquals("Cannot add null element.", e2.getMessage());
		
		IndexOutOfBoundsException e3 = assertThrows(IndexOutOfBoundsException.class, () -> logList.setLog(1, "hello"));
		assertEquals("Invalid index.", e3.getMessage());
		
		logList.addLog("Clean Water Bottle");
		logList.setLog(0, "Waste Time");
		assertEquals("Waste Time", logList.getLog(0));
		assertEquals("Clean Water Bottle", logList.getLog(1));
		
	}
	
	

}
