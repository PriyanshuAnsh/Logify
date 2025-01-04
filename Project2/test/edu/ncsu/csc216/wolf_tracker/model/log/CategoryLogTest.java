package edu.ncsu.csc216.wolf_tracker.model.log;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tracker.model.task.Task;

/**
 * Test class for the CategoryLog.
 * This class contains unit tests to verify the functionality of the
 * CategoryLog class, including task management and duration calculations.
 * 
 * @author Priyanshu Dongre
 */
public class CategoryLogTest {
	/** Instance of CategoryLog used for testing */
    private CategoryLog category;
    
    /** Sample task 1 used for testing */
    private Task task1;
    /** Sample task 2 used for testing */
    private Task task2;
    /** Sample task 3 used for testing */
    private Task task3;

    /**
     * Sets up the CategoryLog instance and sample tasks before each test.
     * Initializes the category variable to a new instance of CategoryLog
     * and creates three sample Task instances.
     */
	@BeforeEach
	public void setUp() {
		category = new CategoryLog("Testing");
		task1 = new Task("Task1", 90, "Test1");
		task2 = new Task("Task2", 90, "Test2");
		task3 = new Task("Task3", 90, "Test3");
	}
	
	/**
     * Tests the constructor of the CategoryLog class.
     * Verifies that the category name is set correctly and that
     * an exception is thrown when a null name is provided.
     */
	@Test
	public void testConstructor() {
		assertEquals("Testing", category.getName());
		assertEquals("Testing", category.getTaskLogName());
		
	
		assertThrows(IllegalArgumentException.class, () -> new CategoryLog(null));
	}

	/**
     * Tests the compareTo method of the CategoryLog class.
     * Verifies that the comparison between two CategoryLog instances
     * works as expected based on their names.
     */
	@Test
	public void testCompareTo() {
		CategoryLog test = new CategoryLog("Test");
		
		assertTrue(category.compareTo(test) > 0);
		assertFalse(category.compareTo(test) < 0);
		
		test = new CategoryLog("Almighty");
		assertTrue(category.compareTo(test) > 0);
		assertFalse(category.compareTo(test) < 0);
	}
	
	/**
     * Tests the addTask method of the CategoryLog class.
     * Verifies that tasks can be added to the category and that
     * the task count is updated correctly. Also tests the setTask method.
     */
	@Test
	public void testAddTask() {
		 for(int i = 1; i < 11; i++) {
			 Task task = new Task("Task" + i, 10 * i + 10, "Testing Task " + i);
			 category.addTask(task);
			 assertEquals(task, category.getTasks().getLog(i - 1));
		 }
		 
		 assertEquals(10, category.getTaskCount());
		 
		 Task overWriteTask = new Task("Overwritten Task", 90, "OverWritten the 6th task from the list.");
		 category.setTask(6, overWriteTask);
		 
		 assertEquals(overWriteTask, category.getTask(6));
		 
	}
	
	/**
     * Tests the removeTask method of the CategoryLog class.
     * Verifies that tasks can be removed from the category and that
     * the correct task is returned. Also checks for exceptions when
     * trying to remove a task from an invalid index.
     */
	@Test
	public void testRemoveTask() {
		Task testingTask = null;
		assertThrows(IndexOutOfBoundsException.class, () -> category.removeTask(0));
		for(int i = 0; i < 10; i++) {
			Task task = new Task("Task " + i, 10 * i + 10, "Dummy Task " + i);
			category.addTask(task);
			if(i == 9) {
				testingTask = task;
			}
		}
		assertEquals(10, category.getTaskCount());
		
		Task removedTask = category.removeTask(9);
		assertEquals(testingTask, removedTask);
	}
	
	/**
     * Tests the getTasksAsArray method of the CategoryLog class.
     * Verifies that the tasks in the category can be retrieved as a 2D array.
     */
	@Test
	public void testTaskAsArray() {
		category.addTask(task1);
		category.addTask(task2);
		category.addTask(task3);
		String[][] taskArray = {{"Task1", "90", "Testing"},
								{"Task2", "90", "Testing"},
								{"Task3", "90", "Testing"}};
		assertArrayEquals(taskArray, category.getTasksAsArray());
	}
	
	/**
     * Tests the getMinDuration method of the CategoryLog class.
     * Verifies that the minimum duration is correctly calculated
     * when no tasks are present, after adding a task, and after
     * adding a task with a shorter duration.
     */
	@Test
	public void testGetMinDuration() {
		assertEquals(0, category.getMinDuration());
		category.addTask(task1);
		assertEquals(90, category.getMinDuration());
		Task testTask = new Task("Test Task", 5, "Testing");
		category.addTask(testTask);
		assertEquals(5, category.getMinDuration());
	}
	
	/**
     * Tests the getMaxDuration method of the CategoryLog class.
     * Verifies that the maximum duration is correctly calculated
     * when no tasks are present, after adding a task, and after
     * adding a task with a longer duration.
     */
	@Test
	public void testGetMaxDuration() {
		assertEquals(0, category.getMaxDuration());
		Task testTask = new Task("Test Task", 5, "Testing");

		category.addTask(testTask);
		
		assertEquals(5, category.getMinDuration());
		
		category.addTask(task1);
		assertEquals(90, category.getMaxDuration());
	}
	
	/**
     * Tests the getAvgDuration method of the CategoryLog class.
     * Verifies that the average duration is correctly calculated
     * when no tasks are present, after adding tasks of the same
     * duration, and after adding a task with a different duration.
     */
	@Test
	public void testGetAvgDuration() {
		assertEquals(0, category.getAvgDuration());
		
		category.addTask(task1);
		assertEquals(90, category.getAvgDuration());
		category.addTask(task2);
		assertEquals(90, category.getAvgDuration());
		category.addTask(task3);
		assertEquals(90, category.getAvgDuration());
		Task testTask = new Task("Test Task", 105, "Testing");
		category.addTask(testTask);
		assertEquals(93.8, category.getAvgDuration());
	}
	
	
	/**
     * Tests the setTask method of the CategoryLog class.
     * Verifies that a task can be replaced at a specific index
     * and that the new task is correctly retrieved.
     */
	@Test
	public void testSetTask() {
		category.addTask(task1);
		category.addTask(task2);
		category.addTask(task3);
		
		
		Task newTask = new Task("SetTask", 90, "Testing Set Task Method");
		category.setTask(1, newTask);
		assertEquals(newTask, category.getTask(1));
	}
	
	
}
