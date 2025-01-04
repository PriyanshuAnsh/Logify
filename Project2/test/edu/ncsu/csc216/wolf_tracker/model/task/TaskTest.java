package edu.ncsu.csc216.wolf_tracker.model.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tracker.model.log.CategoryLog;

/**
 * Test class for the Task class.
 * This class contains unit tests to verify the functionality of the
 * Task class, including task creation, category management, and
 * exception handling.
 * 
 * @author Priyanshu Dongre
 */
public class TaskTest {

	/** Instance of Task used for testing */
    private Task task;
    
    /**
     * Sets up the Task instance before each test.
     * Initializes the task variable to a new instance of Task
     * with the title "Finish Project", duration 90, and details
     * "Incomplete.".
     */
	
	@BeforeEach
	public void setUp() {
		task = new Task("Finish Project", 90, "Incomplete.");
	}

	/**
     * Tests the getter methods of the Task class.
     * Verifies that the task title, duration, and details are
     * correctly retrieved.
     */
	@Test
	public void testSetters() {
		assertEquals("Finish Project", task.getTaskTitle());
		assertEquals(90, task.getTaskDuration());
		assertEquals("Incomplete.", task.getTaskDetails());
	}
	
	/**
     * Tests exception handling in the Task class.
     * Verifies that creating a task with invalid parameters (null title,
     * negative duration, or null category) throws an
     * IllegalArgumentException with the appropriate message.
     */
	@Test
	public void testExceptions() {
		IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> new Task(null, -1, "") );
		assertEquals("Incomplete task information.", e1.getMessage());
		IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> new Task("temp", 90, "Hello").addCategory(null));
		assertEquals("Incomplete task information.", e2.getMessage());
	}
	
	/**
     * Tests the addCategory method of the Task class.
     * Verifies that a category can be added to the task and that
     * adding the same category again throws an IllegalArgumentException.
     */
	@Test
	public void testAddCategory() {
		assertEquals("", task.getCategoryName());
		CategoryLog c = new CategoryLog("Testing");
		try {
			task.addCategory(c);
			assertEquals("Testing", task.getCategoryName());
		} catch(IllegalArgumentException ie) {
			fail("Unexpected Exception.");
		}
		
		assertThrows(IllegalArgumentException.class, () -> task.addCategory(c));
	}
	
	 /**
     * Tests the toString method of the Task class.
     * Verifies that the string representation of the task is correctly
     * formatted, both before and after adding a category.
     */
	@Test
	public void testToString() {
		assertEquals("* Finish Project,90,\nIncomplete.", task.toString());
		CategoryLog c = new CategoryLog("Testing");
		task.addCategory(c);
		assertEquals("* Finish Project,90,Testing\nIncomplete.", task.toString());
	}
}
