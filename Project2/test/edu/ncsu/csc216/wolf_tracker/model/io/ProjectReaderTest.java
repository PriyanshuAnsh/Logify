package edu.ncsu.csc216.wolf_tracker.model.io;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tracker.model.project.Project;
import edu.ncsu.csc216.wolf_tracker.model.task.Task;

/**
 * Tests ProjectReader
 * @author Priyanshu Dongre
 */
public class ProjectReaderTest {

	/** File to test ProjectReader */
	private File file = new File("test-files/studentTestProjectReader.txt");
	
	 /** Expected Project instance */
	private Project expectedProject;
	
	/**
     * Sets up the expected Project instance before each test. This includes
     * adding a category log named "Design" and a sample task.
     */
	@BeforeEach
	public void setUp() {
		expectedProject = new Project("CSC 216 Task Log");
		//CategoryLog category = new CategoryLog("Design");
		Task task = new Task("Read Project 2 requirements", 45, "(https://pages.github.ncsu.edu/engr-csc216-staff/CSC216-SE-Materials/projects/project2/project2-part1.html)\n"
				+ "and identify candidate classes and methods.");
		expectedProject.addCategoryLog("Design");
		expectedProject.addTask(task);
	}
	
	/**
     * Tests the ProjectReader's ability to accurately read and parse the project file.
     * Compares the resulting Project instance with the expected Project instance to
     * verify:
     * - The project name matches.
     * - The category names match.
     * - The current log name matches.
     * - The task count in the current log is as expected.
     */
	@Test
	public void testProjectReader() {
		
		Project result = ProjectReader.readProjectFile(file);
		assertEquals(expectedProject.getProjectName(), result.getProjectName());
		assertArrayEquals(expectedProject.getCategoryNames(), result.getCategoryNames());
		assertEquals("All Tasks", result.getCurrentLog().getName());
		assertEquals(expectedProject.getCurrentLog().getTaskCount(), result.getCurrentLog().getTaskCount());
		
		assertThrows(IllegalArgumentException.class, () -> ProjectReader.readProjectFile(new File(".txt")));
		
		assertDoesNotThrow(() -> ProjectReader.readProjectFile(new File("test-files/project2.txt")));
	}
	

}
