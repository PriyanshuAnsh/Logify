package edu.ncsu.csc216.wolf_tracker.model.project;

import java.io.File;

import edu.ncsu.csc216.wolf_tracker.model.io.ProjectWriter;
import edu.ncsu.csc216.wolf_tracker.model.log.AbstractTaskLog;
import edu.ncsu.csc216.wolf_tracker.model.log.AllTasksLog;
import edu.ncsu.csc216.wolf_tracker.model.log.CategoryLog;
import edu.ncsu.csc216.wolf_tracker.model.task.Task;
import edu.ncsu.csc216.wolf_tracker.model.util.ILogList;
import edu.ncsu.csc216.wolf_tracker.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tracker.model.util.SortedList;


/**
 * Represents a project that can contain tasks and category logs.
 * This class provides methods to manage the project's name, 
 * track changes, and handle tasks and category logs associated with the project.
 * 
 * The project can be saved to a file and can maintain statistics.
 * 
 * @author Priyanshu Dongre
 */
public class Project {

	 /** The name of the project. */
    private String projectName;

    /** Indicates whether the project has been changed. */
    private boolean isChanged;
    
    /**
     * The current task log being used.
     * This log represents the active tasks and their statuses.
     */
    private AbstractTaskLog currentLog;

    /**
     * A log that contains all tasks.
     * This log maintains a record of all tasks, regardless of their current status.
     */
    private AllTasksLog allTasks;

    /**
     * A sorted list of category logs.
     * This list organizes category logs in a sorted manner, allowing for efficient 
     * retrieval and management of logs associated with different categories.
     */
    private ISortedList<CategoryLog> categories;

    /**
     * Constructs a Project with the specified name.
     * 
     * @param projectName the name of the project
     */
	
	public Project(String projectName) {
		setProjectName(projectName);
		categories = new SortedList<>();
		allTasks = new AllTasksLog();
		currentLog = allTasks;
		setIsChanged(true);
	}
	
	/**
     * Saves the project to a file with the specified filename.
     * 
     * @param file the file to save the project
     */
	public void saveProject(File file) {
		ProjectWriter.writeProjectFile(file, this);
		setIsChanged(false);
	}
	
	/**
     * Saves the statistics of the project to a file with the specified filename.
     * 
     * @param file the file to save the project statistics
     */
	public void saveStats(File file) { 
		ProjectWriter.writeStatsFile(file, this);
		
	}

    /**
     * Retrieves the name of the project.
     * 
     * @return the name of the project
     */
	public String getProjectName() {
		return projectName;
	}
	
	/**
     * Sets the name of the project.
     * 
     * @param projectName the new name for the project
     */
	private void setProjectName(String projectName) {
		
		if(projectName == null || "".equals(projectName) || AllTasksLog.ALL_TASKS_NAME.equals(projectName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.projectName = projectName;
	}
	
	/**
     * Checks if the project has been changed.
     * 
     * @return true if the project has been changed, false otherwise
     */
	public boolean isChanged() {
		return isChanged;
	}
	
	 /**
     * Sets the changed status of the project.
     * 
     * @param isChanged the new changed status of the project
     */
	public void setIsChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
	/**
     * Adds a category log with the specified name to the project.
     * 
     * @param categoryName the name of the category log to be added
     */
	public void addCategoryLog(String categoryName) {
		
		try {
			if(categoryName == null || categoryName.isEmpty() || AllTasksLog.ALL_TASKS_NAME.equals(categoryName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
			
			CategoryLog newCategory = new CategoryLog(categoryName);
			categories.add(newCategory);
			currentLog = newCategory;
			setIsChanged(true);
		} catch (IllegalArgumentException ie) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		
	}
	
	/**
     * Retrieves the names of all category logs associated with the project.
     * 
     * @return an array of category log names, or null if none exist
     */
	public String[] getCategoryNames() {
		
		String[] returnArr = new String[categories.size() + 1];
		returnArr[0] = AllTasksLog.ALL_TASKS_NAME;
		for(int i = 0; i < categories.size(); i++) {
		
			returnArr[i + 1] = categories.get(i).getName();
		}
		
		return returnArr;
	}
	
	/**
     * Sets the current task log to the specified log name.
     * 
     * @param logName the name of the task log to be set as current
     */
	public void setCurrentTaskLog(String logName) {
		AbstractTaskLog category = null;
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getName().equals(logName)) {
				category = categories.get(i);
			}
		}
		
		if(category != null) {
			currentLog = category;
		} else {
			currentLog = allTasks;
		}
	}
	
	/**
	 * Retrieves the current task log.
	 *
	 * @return the current task log as an instance of AbstractTaskLog.
	 */
	public AbstractTaskLog getCurrentLog() {
		return currentLog;
	}
	
	/**
     * Edits the name of an existing category log.
     * 
     * @param categoryName the new name for the category log
     */
	public void editCategoryLogName(String categoryName) {
		if(currentLog.equals(allTasks)) {
			throw new IllegalArgumentException("The All Tasks log may not be edited.");
		}
		try {
			
			if(categoryName == null || categoryName.isEmpty() || AllTasksLog.ALL_TASKS_NAME.equals(categoryName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		
			CategoryLog temp = new CategoryLog(categoryName);
			
			if(categories.contains(temp)) {
				throw new IllegalArgumentException();
			}
			for(int i = 0; i < categories.size(); i++) {
				if(categories.get(i).getName().equals(currentLog.getName())) {
					CategoryLog editCategory = categories.get(i);
					
					categories.remove(i);
					editCategory.setTaskLogName(categoryName);
					categories.add(editCategory);
				}
			}
			setIsChanged(true);
		} catch(IllegalArgumentException ie) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
	}
	
    /**
     * Removes a category log from the project.
     */
	public void removeCategoryLog() {
		if(AllTasksLog.ALL_TASKS_NAME.equals(currentLog.getName())) {
			throw new IllegalArgumentException("The All Tasks log may not be deleted.");
		}
		
		boolean categoryRemoved = false;
		for(int i = 0; i < categories.size(); i++) {
			if(currentLog.getName().equals(categories.get(i).getName())) {
				categories.remove(i);
				categoryRemoved = true;
			}
		}
		
		if(categoryRemoved) {
			ILogList<Task> list = allTasks.getTasks();
			
			for(int i = 0; i < list.size(); i++) {
				if(list.getLog(i).getCategoryName().equals(currentLog.getName())) {
					list.removeLog(i);
				}
			}
			currentLog = allTasks;
		}
		setIsChanged(true);
	}
	
	/**
     * Adds a task to the project.
     * 
     * @param task the Task to be added to the project
     */
	public void addTask(Task task) {
		if(!(currentLog instanceof CategoryLog)) {
			return;
		}
		currentLog.addTask(task);
		allTasks.addTask(task);
		setIsChanged(true);
	}
	
	/**
     * Edits an existing task in the project.
     * 
     * @param idx the index of the task to be edited
     * @param taskName the new name for the task
     * @param taskDuration the new duration for the task
     * @param taskDetails the new details for the task
     */
	public void editTask(int idx, String taskName, int taskDuration, String taskDetails) {
	   
	    Task taskToEdit = currentLog.getTasks().getLog(idx);
	    if (taskToEdit == null) {
	        throw new IllegalArgumentException("Task does not exist at the specified index.");
	    }

	    
	    taskToEdit.setTaskTitle(taskName);
	    taskToEdit.setTaskDuration(taskDuration);
	    taskToEdit.setTaskDetails(taskDetails);

	    
	    setIsChanged(true); 
	}
	
	/**
     * Removes a task from the project at the specified index.
     * 
     * @param idx the index of the task to be removed
     */
	public void removeTask(int idx) {
		
		if(currentLog instanceof AllTasksLog) {
			Task removedTask = currentLog.removeTask(idx);
			
			String removedTaskCategoryName = removedTask.getCategoryName();
			
			//Iterate through the categories.
			for(int i = 0; i < categories.size(); i++) {
				
				CategoryLog currentCategory = categories.get(i);
				
				if(currentCategory.getName().equals(removedTaskCategoryName)) {
					ILogList<Task> taskList = currentCategory.getTasks();
					
					//Now iterate through the task list and remove task.
					
					for(int j = 0; j < taskList.size(); j++) {
						Task currentTask = taskList.getLog(j);
						if(currentTask.equals(removedTask)) {
							currentCategory.removeTask(j);
						}
					}
					
				}
				
			}
		} else {
			
			Task removedTask = currentLog.removeTask(idx);
			ILogList<Task> allTasksList = allTasks.getTasks();
			for(int i = 0; i < allTasksList.size(); i++) {
				if(removedTask.equals(allTasksList.getLog(i))) {
					allTasks.removeTask(i);
				}
			
			}
		}
	
		setIsChanged(true);
	}
	
	/**
     * Retrieves the most recent tasks associated with the project.
     * 
     * @return a 2D array containing the most
     */
	
	public String[][] getMostRecentTasks() {
		String[][] returnArr = new String[categories.size()][3];
		
		for(int i = 0; i < categories.size(); i++) {
			CategoryLog currentCategory = categories.get(i);
			ILogList<Task> logs = currentCategory.getTasks();
			if(logs.size() == 0) {
				returnArr[i][0] = "None";
				returnArr[i][1] = "";
				returnArr[i][2] = currentCategory.getTaskLogName();
			} else {
				returnArr[i][0] = logs.getLog(logs.size() - 1).getTaskTitle();
				returnArr[i][1] = "" + logs.getLog(logs.size() - 1).getTaskDuration();
				returnArr[i][2] = logs.getLog(logs.size() - 1).getCategoryName();
			}
		}
		
		return returnArr;
	}
	
	
	
}
