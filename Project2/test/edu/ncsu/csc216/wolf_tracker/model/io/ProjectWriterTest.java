package edu.ncsu.csc216.wolf_tracker.model.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tracker.model.project.Project;
import edu.ncsu.csc216.wolf_tracker.model.task.Task;



/**
 * Tests the functionality of the ProjectWriter class.
 * This class contains unit tests to verify that the ProjectWriter
 * correctly writes project data to files and handles various scenarios.
 * 
 * @author Priyanshu Dongre
 */
public class ProjectWriterTest {

	 /** Sample Project instance */
    private Project project;
    
    /** Expected content of the output file */
	private String expectedFileContent = "! Test\n# Test1\n# Test2\n# Test3\n* Testing ProjectReader,90,Test1\nEverything Looking Good";

	/**
     * Sets up the Project instance before each test. Initializes the project name
     * and prepares it for category and task additions.
     */
	@BeforeEach
	public void setUp() {
		project = new Project("Test");
	}
	/**
     * Tests the writeProjectFile method in ProjectWriter by:
     * - Adding categories and a task to the Project instance.
     * - Writing the Project data to a file.
     * - Reading the file content and comparing it to the expected output.
     * 
     * Ensures that the content output by ProjectWriter matches the expected format
     * and content.
     */
	@Test
	public void testWriteToFile() {
		project.addCategoryLog("Test1");
		Task task = new Task("Testing ProjectReader", 90, "Everything Looking Good");
		project.addTask(task);
		project.addCategoryLog("Test2");
		project.addCategoryLog("Test3");
		
		File file = new File("test-files/StudentTest.txt");
		ProjectWriter.writeProjectFile(file, project);
		
		
		Scanner fileProcessor;
		String result = "";
		try {
			fileProcessor = new Scanner(file);
			
			while(fileProcessor.hasNextLine()) {
				result += fileProcessor.nextLine() + "\n";
			}
			result = result.substring(0, result.length() - 1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(expectedFileContent, result);		
	}
	
	/**
     * Tests the writeProjectFile method in ProjectWriter by:
     * - Adding categories and a task to the Project instance.
     * - Writing the Project data to a file.
     * - Reading the file content and comparing it to the expected output.
     * 
     * Ensures that the content output by ProjectWriter matches the
     * expected format and content.
     */
	@Test
	public void testWriteStatsFile() {
		
		assertThrows(IllegalArgumentException.class, () -> ProjectWriter.writeStatsFile(null, project));
		
		String expectedResult = "Category,Count,Min,Max,Average\nTest1,1,90,90,90.0\nTest2,0,,,\nTest3,0,,,\nAll Tasks,1,90,90,90.0";
		project.addCategoryLog("Test1");
		Task task = new Task("Testing ProjectReader", 90, "Everything Looking Good");
		project.addTask(task);
		project.addCategoryLog("Test2");
		project.addCategoryLog("Test3");
		
		File file = new File("test-files/ST_testWriteStatsToFile.csv");
		ProjectWriter.writeStatsFile(file, project);
		
		Scanner fileProcessor;
		String result = "";
		try {
			fileProcessor = new Scanner(file);
			
			while(fileProcessor.hasNextLine()) {
				result += fileProcessor.nextLine() + "\n";
			}
			result = result.substring(0, result.length() - 1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(expectedResult, result);		
		
		
	}

}
