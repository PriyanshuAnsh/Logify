package edu.ncsu.csc216.wolf_tracker.model.log;

import edu.ncsu.csc216.wolf_tracker.model.task.Task;


import edu.ncsu.csc216.wolf_tracker.model.util.ILogList;
import edu.ncsu.csc216.wolf_tracker.model.util.LogList;


/**
 * Abstract class representing a task log.
 * This class serves as a base for managing a collection of tasks,
 * providing methods to add, remove, and retrieve tasks, as well as
 * to calculate various statistics related to task durations.
 * 
 * @author Priyanshu Dongre
 */
public abstract class AbstractTaskLog {
	
	/** The list of tasks in the task log */
    private ILogList<Task> tasks;
    
    /** The name of the task log */
    private String taskLogName;

    /**
     * Constructs an AbstractTaskLog with the specified name.
     * 
     * @param taskLogName the name of the task log
     */
	public AbstractTaskLog(String taskLogName) {
		try {
			this.setTaskLogName(taskLogName);
			tasks = new LogList<>();
			
		} catch (IllegalArgumentException ie) {
			throw new IllegalArgumentException(ie.getMessage());
		}
	}
	
	
	/**
     * Returns the name of the task log.
     * 
     * @return the name of the task log
     */
	public String getName() {
		return taskLogName;
	}
	
	
	/**
     * Sets the name of the task log.
     * 
     * @param taskLogName the new name of the task log
     */
	public void setTaskLogName(String taskLogName) {
		
		if(taskLogName == null || taskLogName.isEmpty()) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.taskLogName = taskLogName;
	}
	
	 /**
     * Gets the name of the task log.
     * 
     * @return the name of the task log
     */
	public String getTaskLogName() {
		return taskLogName;
	}

	
	/**
     * Returns the list of tasks in the task log.
     * 
     * @return the list of tasks
     */
	public ILogList<Task> getTasks() {
		return tasks;
	}
	
	
	/**
     * Adds a task to the task log.
     * 
     * @param task the task to be added
     */
	public void addTask(Task task) {
		tasks.addLog(task);
	}
	
	/**
     * Sets a task at the specified index in the task log.
     * 
     * @param idx the index at which to set the task
     * @param task the task to be set
     */
	public void setTask(int idx, Task task) {
		tasks.setLog(idx, task);
	}
	
	
	/**
     * Removes a task from the task log at the specified index.
     * 
     * @param idx the index of the task to be removed
     * @return the removed task
     */
	public Task removeTask(int idx) {
		
		Task removedTask = tasks.removeLog(idx);
		return removedTask;
	}
	
	
	/**
     * Retrieves a task from the task log at the specified index.
     * 
     * @param idx the index of the task to be retrieved
     * @return the task at the specified index
     */
	public Task getTask(int idx) {
		return tasks.getLog(idx);
	}
	
   /**
    * Returns the number of tasks in the task log.
    * 
    * @return the number of tasks
    */
	public int getTaskCount() {
		return tasks.size();
	}
	
	/**
     * Returns the minimum duration of tasks in the task log.
     * 
     * @return the minimum duration
     */
	public int getMinDuration() {
		if(tasks.size() == 0) {
			return 0;
		}
		int min = 1000;
		
		for(int i = 0; i < tasks.size(); i++) {
			int taskDuration = tasks.getLog(i).getTaskDuration();
			min = Math.min(taskDuration, min);
			
		}
		return min;
	}
	
	
	/**
     * Returns the maximum duration of tasks in the task log.
     * 
     * @return the maximum duration
     */
	public int getMaxDuration() {
		int max = 0;
		
		for(int i = 0; i < tasks.size(); i++) {
			int taskDuration = tasks.getLog(i).getTaskDuration();
			max = Math.max(taskDuration, max);
		}
		return max;
	}
	
	
	/**
     * Returns the average duration of tasks in the task log.
     * 
     * @return the average duration
     */
	public double getAvgDuration() {
		
		if(tasks.size() == 0) {
			return 0;
		}
		
		double sumOfDuration = 0;
		
		for(int i = 0; i < tasks.size(); i++) {
			sumOfDuration += tasks.getLog(i).getTaskDuration();
		}
		
		double averageDuration = sumOfDuration / tasks.size();

	    averageDuration = Math.round(averageDuration * 10.0) / 10.0;

	    return averageDuration;
	}
	
	
	/**
     * Returns a 2D array representation of the tasks in the task log.
     * 
     * @return a 2D array of tasks
     */
	public String[][] getTasksAsArray() {
		
		String[][] returnArr = new String[tasks.size()][3];
		
		for(int i = 0; i < tasks.size(); i++) {
			returnArr[i][0] = tasks.getLog(i).getTaskTitle();
			returnArr[i][1] = "" + tasks.getLog(i).getTaskDuration();
			returnArr[i][2] = tasks.getLog(i).getCategoryName();
		}
		return returnArr;
	}
	
	
	/**
     * Returns a string representation of the task log.
     * 
     * @return a string representation of the task log
     */
	@Override
	public String toString() {
		
		String returnStr = "";
		
		if(this.getTaskCount() != 0) {
			returnStr += this.getName() + "," + this.getTaskCount() + "," + this.getMinDuration() + "," + this.getMaxDuration() + "," + this.getAvgDuration();
		} else {
			returnStr += this.getName() + "," + this.getTaskCount() + "," + "," + ",";
		}
		
		return returnStr;
		
	}
}
