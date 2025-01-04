package edu.ncsu.csc216.wolf_tracker.model.io;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tracker.model.log.AllTasksLog;
import edu.ncsu.csc216.wolf_tracker.model.project.Project;
import edu.ncsu.csc216.wolf_tracker.model.task.Task;

/**
 * A class for reading project data from files.
 * This class provides methods to read a project from a specified file.
 * 
 * @author Priyanshu Dongre
 */
public class ProjectReader {
	
	/**
     * Reads a project from the specified file.
     * 
     * @param file the file from which the project will be read
     * @return the Project object read from the file, or null if the operation fails
     */
	@SuppressWarnings("resource")
	public static Project readProjectFile(File file) {
		Project project = null;
		String cache = null;
		try {

			Scanner fileScanner = new Scanner(file);
			String fileAsString = "";
			int count = 1;
			while(fileScanner.hasNextLine()) {
				String currentLine = fileScanner.nextLine();
				if(count == 1 && currentLine.charAt(0) != '!') {
					throw new IllegalArgumentException();
				}
				fileAsString += currentLine + "\n";
				count++;
			}
			//System.out.print(fileAsString);// This is working
			
			fileScanner.close();
			fileScanner = new Scanner(fileAsString);
			while(fileScanner.hasNextLine()) {
				String currentLine = fileScanner.nextLine();
				
				if(currentLine.charAt(0) == '!') {
					
					cache = currentLine;
					project = new Project(currentLine.substring(2));
					fileAsString = fileAsString.substring(currentLine.length() + 1);
				} else if(currentLine.charAt(0) == '#') {
	
					project.addCategoryLog(currentLine.substring(2));
					fileAsString = fileAsString.substring(currentLine.length() + 1);
				} else if(currentLine.charAt(0) == '*') {
					break;
				} else {
					throw new IllegalArgumentException("Unable to load file.");
				}
				
			}
			
			fileScanner.close();
			fileScanner = new Scanner(fileAsString);
			fileScanner.useDelimiter("[*]");
			while(fileScanner.hasNext()) {
				String currentLine = fileScanner.next();
//				System.out.println(currentLine);
				processTask(project, currentLine);
				
			}
			
			fileScanner.close();
		} catch(IOException e) {
			throw new IllegalArgumentException("Unable to load file.");
		} catch(Exception e) {
			//Skip Line
		}
		
		if(cache == null) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		project.setCurrentTaskLog(AllTasksLog.ALL_TASKS_NAME);
		//System.out.print(project.getCurrentLog().getName());
		return project;
	}

	/**
     * Processes a task line and adds it to the specified project.
     * 
     * @param project the project to which the task will be added
     * @param line the line containing task information to be processed
     */

	private static void processTask(Project project, String line) {
		
		Scanner lineProcessor = new Scanner(line).useDelimiter("[,]");
		
		
		
		String taskTitle = lineProcessor.next().substring(1);
//		System.out.println(taskTitle);
		
		int taskDuration = Integer.parseInt(lineProcessor.next());
//		System.out.println(taskDuration);
		lineProcessor.useDelimiter("\n");
		String category = lineProcessor.next().substring(1);
//		System.out.println(category);
		
		
		String taskDetails = "";
		
		while(lineProcessor.hasNextLine()) {
			taskDetails += lineProcessor.nextLine();
			if(lineProcessor.hasNext()) {
				taskDetails += "\n";
			}
		}
		taskDetails = taskDetails.substring(1);
//		System.out.println(taskDetails);
		
		
		Task task = new Task(taskTitle, taskDuration, taskDetails);
		
		project.setCurrentTaskLog(category);
		project.addTask(task);
		
		lineProcessor.close();
	}
}
