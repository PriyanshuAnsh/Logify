package edu.ncsu.csc216.wolf_tracker.model.project;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tracker.model.log.AllTasksLog;
import edu.ncsu.csc216.wolf_tracker.model.task.Task;


/**
 * Test class for the Project.
 * This class contains unit tests to verify the functionality of the
 * Project class, including project name management, category logs,
 * task management, and exception handling.
 * 
 * @author Priyanshu Dongre
 */
public class ProjectTest {
	
	/** Instance of Project used for testing */
    private Project project;
    
    /**
     * Sets up the Project instance before each test.
     * Initializes the project variable to a new instance of Project
     * with the name "Testing".
     */
	
	@BeforeEach
	public void setUp() {
		project = new Project("Testing");
	}
	
	/**
     * Tests various exception scenarios in the Project class.
     * Verifies that appropriate exceptions are thrown when invalid
     * arguments are provided for project creation, category log name
     * editing, and adding category logs.
     */
	@Test
	public void testExceptions() {
		assertThrows(IllegalArgumentException.class, () -> new Project(null));
		assertThrows(IllegalArgumentException.class, () -> new Project(""));
		assertThrows(IllegalArgumentException.class, () -> new Project("All Tasks"));
		assertThrows(IllegalArgumentException.class, () -> project.editCategoryLogName(null));
		assertThrows(IllegalArgumentException.class, () -> project.editCategoryLogName(""));
		assertThrows(IllegalArgumentException.class, () -> project.editCategoryLogName("All Tasks"));
		
		assertThrows(IllegalArgumentException.class, () -> { new Project("Test").addCategoryLog(null);
		});
		assertThrows(IllegalArgumentException.class, () -> { new Project("Test").editCategoryLogName(null);
		});
	}
	/**
     * Tests the getProjectName method of the Project class.
     * Verifies that the project name is correctly retrieved.
     */
	@Test
	public void testGetProjectName() {
		assertEquals("Testing", project.getProjectName());
	}
	
	/**
     * Tests category log management in the Project class.
     * Verifies that category logs can be added, edited, and removed,
     * and that the current log is correctly set and retrieved.
     */
	@Test
	public void testCategory() {
		project.addCategoryLog("Unit Test");
		assertEquals("Unit Test", project.getCurrentLog().getName());
		
		project.editCategoryLogName("Test");
		assertEquals("Test", project.getCurrentLog().getName());
		String[] test = {"All Tasks", "Test"};
		
		assertArrayEquals(test, project.getCategoryNames());
		
		
		project.addCategoryLog("Project 2 Part 2");
		assertEquals("Project 2 Part 2", project.getCurrentLog().getName());
		
		
		
		String[] test2 = {"All Tasks", "Project 2 Part 2", "Test"};
		
		assertArrayEquals(test2, project.getCategoryNames());
	
		assertEquals("Project 2 Part 2", project.getCurrentLog().getName());
		
		project.setCurrentTaskLog("Test");
		assertEquals("Test", project.getCurrentLog().getName());
		
		project.setCurrentTaskLog("Something");
		assertEquals(AllTasksLog.ALL_TASKS_NAME, project.getCurrentLog().getName());
	
		assertThrows(IllegalArgumentException.class, () -> project.removeCategoryLog());

		project.setCurrentTaskLog("Test");
		project.removeCategoryLog();
		assertEquals(AllTasksLog.ALL_TASKS_NAME, project.getCurrentLog().getName());
		
		
		//Add Task to the Project
		
		project.setCurrentTaskLog("Project 2 Part 2");
		assertEquals("Project 2 Part 2", project.getCurrentLog().getName());
		Task newTask = new Task("test1", 85, "Testing add task in project2");
		project.addTask(newTask);
		assertEquals(newTask, project.getCurrentLog().getTasks().getLog(0));
		
		//editing newTask
		project.editTask(0, "Test2", 10, "Editing Task");
		assertEquals("Editing Task", newTask.getTaskDetails());
		
		assertEquals("Test2", project.getCurrentLog().getTask(0).getTaskTitle());
		assertEquals(10, project.getCurrentLog().getTask(0).getTaskDuration());
		assertEquals("Editing Task", project.getCurrentLog().getTask(0).getTaskDetails());
		
		
		
		//Removing Project 2 Part 2 category. This should Also remove newTask from All Tasks.
		
		project.removeCategoryLog();
		assertNotEquals(newTask, project.getCurrentLog().getTask(0));
		assertEquals(0, project.getCurrentLog().getTasks().size());
		assertTrue(project.isChanged());
	}
	
	/**
     * Tests the addCategoryLog method of the Project class.
     * Verifies that adding duplicate category names throws an
     * IllegalArgumentException. Also checks that the category names
     * are correctly added to the project.
     */
	@Test
	public void testAddDuplicateCategories() {
		project.addCategoryLog("Test1");
		project.addCategoryLog("Test2");
		assertEquals("Test1", project.getCategoryNames()[1]);
		assertEquals("Test2", project.getCategoryNames()[2]);
		
		assertThrows(IllegalArgumentException.class, () -> project.addCategoryLog("Test1"));
	}
	
	
	/**
     * Tests the removeTask method of the Project class.
     * Verifies that tasks can be added to a category log and that
     * they can be removed correctly. Also checks that the task count
     * is updated appropriately after removal.
     */
	@Test
	public void testRemoveTask() {
		project.addCategoryLog("Test1");
		Task task1 = new Task("Test1", 1, "Testing1");
		Task task2 = new Task("Test2", 2, "Testing2");
		project.addTask(task1);
		project.addTask(task2);
		assertEquals("Test1", project.getCurrentLog().getName());
		
		project.setCurrentTaskLog(AllTasksLog.ALL_TASKS_NAME);
		assertEquals(AllTasksLog.ALL_TASKS_NAME, project.getCurrentLog().getName());
		assertEquals(2, project.getCurrentLog().getTasks().size());
		
		project.removeTask(1);
		assertEquals(1, project.getCurrentLog().getTasks().size());
		
		project.setCurrentTaskLog("Test1");
		assertEquals(1, project.getCurrentLog().getTaskCount());
		
		project.removeTask(0);
		assertEquals(0, project.getCurrentLog().getTaskCount());
	}
	
	/**
     * Tests the getMostRecentTasks method of the Project class.
     * Verifies that the most recent tasks from different category logs
     * are correctly retrieved and formatted as a 2D array.
     */
	@Test
	public void testGetMostRecentTasks() {
		project.addCategoryLog("Test1");
		project.addCategoryLog("Test2");
		project.addCategoryLog("Empty_Cat");
		
		Task task1 = new Task("Task1", 1, "1");
		Task task2 = new Task("Task 2", 2, "2");
		
		project.setCurrentTaskLog("Test1");
		project.addTask(task1);
		
		project.setCurrentTaskLog("Test2");
		project.addTask(task2);
		
		
		String[][] expectedArr = {{"None", "", "Empty_Cat"}, {"Task1", "1", "Test1"}, {"Task 2", "2", "Test2"} };
		
		assertArrayEquals(expectedArr, project.getMostRecentTasks());
	}
	

	

}
