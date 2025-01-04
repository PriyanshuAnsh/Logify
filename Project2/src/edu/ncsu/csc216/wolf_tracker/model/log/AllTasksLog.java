package edu.ncsu.csc216.wolf_tracker.model.log;

/**
 * Represents a log that contains all tasks.
 * This class extends the AbstractTaskLog and provides functionality
 * to manage a collection of tasks under a single log named "All Tasks".
 * 
 * The name of the log is defined as a constant.
 * 
 * @author Priyanshu Dongre
 */
public class AllTasksLog extends AbstractTaskLog {
	
	/** The constant name for the All Tasks log. */
    public static final String ALL_TASKS_NAME = "All Tasks";

    /**
     * Constructs an AllTasksLog with the specified name.
     * 
     */
    
	public AllTasksLog() {
		super(ALL_TASKS_NAME);
	}
	
	/**
     * Sets the name of the task log.
     * 
     * @param taskLogName the new name for the task log
     */
	public void setTaskLogName(String taskLogName) {
		if(!taskLogName.equals(ALL_TASKS_NAME)) {
			throw new IllegalArgumentException("The All Tasks log may not be edited.");
		}
		super.setTaskLogName(taskLogName);
	}

}
