package edu.ncsu.csc216.wolf_tracker.model.log;

import edu.ncsu.csc216.wolf_tracker.model.task.Task;


/**
 * Represents a category log that extends the functionality of an abstract task log.
 * This class allows for the management of tasks within a specific category and
 * provides methods to add and set tasks, as well as to compare category logs.
 * 
 * @author Priyanshu Dongre
 */
public class CategoryLog extends AbstractTaskLog implements Comparable<CategoryLog> {

	
	/**
     * Constructs a CategoryLog with the specified name.
     * 
     * @param taskLogName the name of the task log
     */
	public CategoryLog(String taskLogName) {
		
		super(taskLogName);
		
	}
	
	/**
     * Compares this CategoryLog to another CategoryLog.
     * 
     * @param c the CategoryLog to be compared
     * @return a negative integer, zero, or a positive integer as this CategoryLog
     *         is less than, equal to, or greater than the specified CategoryLog
     */
	public int compareTo(CategoryLog c) {
		
		

		return this.getName().toUpperCase().compareTo(c.getName().toUpperCase());
	}
	
	/**
     * Sets a task at the specified index in the category log.
     * 
     * @param idx the index at which to set the task
     * @param task the task to be set
     */
	@Override
	public void setTask(int idx, Task task) {
		if(task == null) {
			throw new NullPointerException();
		}
		task.addCategory(this);
		super.setTask(idx, task);
	}
	
	/**
     * Adds a task to the category log.
     * 
     * @param task the task to be added
     */
	@Override
	public void addTask(Task task) {
		super.addTask(task);
		task.addCategory(this);
	}

}
