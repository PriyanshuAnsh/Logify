package edu.ncsu.csc216.wolf_tracker.model.task;

import edu.ncsu.csc216.wolf_tracker.model.log.CategoryLog;

/**
 * Represents a task with a title, duration, and details.
 * This class provides methods to get and set the properties of a task.
 * It also allows associating a task with a category.
 * 
 * @author Priyanshu Dongre
 */
public class Task {

	/** The title of the task */
    private String taskTitle;

    /** The duration of the task in minutes */
    private int taskDuration;

    /** Additional details about the task */
    private String taskDetails;
    
    /** The category associated with the task */
    private CategoryLog category;

    
    /**
     * Constructs a Task with the specified title, duration, and details.
     * 
     * @param taskTitle the title of the task
     * @param taskDuration the duration of the task in minutes
     * @param taskDetails additional details about the task
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public Task(String taskTitle, int taskDuration, String taskDetails) {
		
    	try {
    		this.setTaskTitle(taskTitle);
    		this.setTaskDuration(taskDuration);
    		this.setTaskDetails(taskDetails);
    	} catch(IllegalArgumentException ie) {
    		throw new IllegalArgumentException(ie.getMessage());
    	}
	}

    /**
     * Gets the title of the task.
     *
     * @return the title of the task
     */

    public String getTaskTitle() {
        return taskTitle;
    }

    /**
     * Sets the title of the task.
     *
     * @param taskTitle the new title of the task
     * @throws IllegalArgumentException if taskTitle is null or empty
     */
    public void setTaskTitle(String taskTitle) {
        if (taskTitle == null || taskTitle.isEmpty()) {
            throw new IllegalArgumentException("Incomplete task information.");
        }
        this.taskTitle = taskTitle;
    }

    /**
     * Gets the duration of the task.
     *
     * @return the duration of the task in minutes
     */
    public int getTaskDuration() {
        return taskDuration;
    }

    /**
     * Sets the duration of the task.
     *
     * @param taskDuration the new duration of the task in minutes
     * @throws IllegalArgumentException if taskDuration is negative or zero
     */
    public void setTaskDuration(int taskDuration) {
        if (taskDuration <=  0) {
            throw new IllegalArgumentException("Incomplete task information.");
        }
        this.taskDuration = taskDuration;
    }
    
 

    /**
     * Gets the details of the task.
     *
     * @return the details of the task
     */
    public String getTaskDetails() {
        return taskDetails;
    }

    /**
     * Sets the details of the task.
     *
     * @param taskDetails the new details of the task
     * @throws IllegalArgumentException if taskDetails is null or empty
     */
    public void setTaskDetails(String taskDetails) {
        if (taskDetails == null || taskDetails.isEmpty()) {
            throw new IllegalArgumentException("Incomplete task information.");
        }
        this.taskDetails = taskDetails;
    }
    
    
    /**
     * Adds a category to the task.
     * 
     * @param category the CategoryLog to be associated with the task
     * @throws IllegalArgumentException if the category is null or if the task already has a category
     */
    public void addCategory(CategoryLog category) {
    	if(category == null) {
    		throw new IllegalArgumentException("Incomplete task information.");
    	} 
    	if(this.category == null) {
    		this.category = category;
    	} else {
    		throw new IllegalArgumentException("Incomplete task information.");
    	}
    	
    }
    
    /**
     * Retrieves the name of the category.
     * 
     * @return the name of the category, or null if not set
     */
    public String getCategoryName() {
    	if(category == null) {
    		return "";
    	}
    	return category.getName();
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string containing the task title, duration, and details
     */
    @Override
    public String toString() {
 
        String returnStr = "* " + taskTitle + "," + taskDuration + "," + this.getCategoryName() + "\n"
        		+ taskDetails;
        
        return returnStr;
    }
}