package edu.ncsu.csc216.wolf_tracker.model.log;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the AllTasksLog.
 * This class contains unit tests to verify the functionality of the
 * AllTasksLog class, specifically focusing on the task log name.
 * 
 * @author Priyanshu Dongre
 */
class AllTasksLogTest {

	/** Instance of AllTasksLog used for testing */
    private AllTasksLog allTasks;

    /**
     * Sets up the AllTasksLog instance before each test.
     * Initializes the allTasks variable to a new instance of AllTasksLog.
     */


	@BeforeEach
	public void setUp() {
		allTasks = new AllTasksLog();
	}
	
	/**
     * Tests the setTaskLogName method in AllTasksLog.
     * This test verifies that the default name of the task log is "All Tasks"
     * and that attempting to change the name throws an IllegalArgumentException.
     */
	@Test
	public void testSetTaskLogName() {
		assertEquals("All Tasks", allTasks.getName());
		assertThrows(IllegalArgumentException.class, () -> allTasks.setTaskLogName("My Tasks"));
	}

}
