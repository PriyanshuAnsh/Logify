package edu.ncsu.csc216.wolf_tracker.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import edu.ncsu.csc216.wolf_tracker.model.log.AbstractTaskLog;
import edu.ncsu.csc216.wolf_tracker.model.log.AllTasksLog;
import edu.ncsu.csc216.wolf_tracker.model.project.Project;
import edu.ncsu.csc216.wolf_tracker.model.task.Task;
import edu.ncsu.csc216.wolf_tracker.model.util.ILogList;

/**
 * A class for writing project data to files.
 * This class provides methods to save a project and its statistics
 * to specified file locations.
 * 
 * @author Priyanshu Dongre
 */
public class ProjectWriter {
	
	/**
     * Writes the specified project to a file.
     * 
     * @param file the file to which the project will be written
     * @param project the project to be written to the file
     */
	public static void  writeProjectFile(File file, Project project) {
		String output = "";
		try {
			PrintWriter printWriter = new PrintWriter(file);
			output += "! " + project.getProjectName() + "\n";
			
			String[] categoryNames = project.getCategoryNames();
			for(int i = 1; i < categoryNames.length; i++) {
				output += "# " + categoryNames[i] + "\n";
			}
			//output = output.substring(0, output.length() - 1);
			project.setCurrentTaskLog(categoryNames[0]);
			AbstractTaskLog log = project.getCurrentLog();
			ILogList<Task> list = log.getTasks();
			
			System.out.println(list.size());
			
			for(int i = 0; i < list.size(); i++) {
				if(i == list.size() - 1) {
					output += list.getLog(i).toString();
				} else {
					output += list.getLog(i).toString() + "\n";
				}
				
				
			}
			
//			output = output.substring(0, output.length() - 1);
			
			printWriter.write(output);
			printWriter.close();
		} catch(IOException io) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		
	}
	
	/**
     * Writes the statistics of the specified project to a file.
     * 
     * @param file the file to which the project statistics will be written
     * @param project the project whose statistics will be written to the file
     */
	public static void writeStatsFile(File file, Project project) {
		if(file == null) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		try {
			PrintWriter printWriter = new PrintWriter(file);
			String returnString = "Category,Count,Min,Max,Average\n";
			
			String[] categories = project.getCategoryNames();
			
			for(int i = 1; i < categories.length; i++) {
				project.setCurrentTaskLog(categories[i]);
				
				AbstractTaskLog currentLog = project.getCurrentLog();
				returnString += currentLog.toString() + "\n";
			}
			project.setCurrentTaskLog(AllTasksLog.ALL_TASKS_NAME);
			AbstractTaskLog currentLog = project.getCurrentLog();
			returnString += currentLog.toString();
			
			
			printWriter.write(returnString);
			printWriter.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
