package edu.ncsu.csc216.wolf_tracker.model.util;

/**
 * A class that implements a log list using a generic type.
 * This list is designed to store log entries of type E.
 * @author Priyanshu Dongre
 * @param <E> the type of elements in this log list
 */
public class LogList<E> implements ILogList<E> {

	/** The array that holds the log entries */
	private E[] list;
	
	/** The current number of elements in the log list */
	private int size;
	
	 /** The initial capacity of the log list */
	private static final int INIT_SIZE = 10;
	
	 /**
     * Constructs a new LogList with an initial capacity.
     */
	@SuppressWarnings("unchecked")
	public LogList() {
		list = (E[]) new Object[INIT_SIZE];
	}
	
	/**
     * Adds a log entry to the end of the log list.
     *
     * @param element the log entry to be added to the list
     * @throws NullPointerException if the specified element is null
     */
	@Override
	public void addLog(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		if(size == list.length) {
			ensureCapacity(size);
		}
		
		list[size] = element;
		size++;
		
	}

	 /**
     * Sets the log entry at the specified index to the given element.
     *
     * @param idx the index of the log entry to be replaced
     * @param element the new log entry to set at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (idx less than 0 or idx greater than or equal size())
     * @throws NullPointerException if the specified element is null
     */
	@Override
	public void setLog(int idx, E element) {
		
		if(size == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		for(int i = 0; i < size; i++) {
			if(i == idx) {
				list[i] = element;
			}
		}
		
	}

	/**
     * Removes the log entry at the specified index from the log list.
     *
     * @param idx the index of the log entry to be removed
     * @return removedElement the log entry that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (idx less than 0 or idx greater than or equal to size())
     */
	@Override
	public E removeLog(int idx) {
		
		if(size == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		if(idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		E currentElement = list[idx];
		list[idx] = null;
		if(idx == size - 1) {
			size--;
			return currentElement;
		}
		
		for(int i = idx; i < size - 1; i++) {
			E nextElement = list[i + 1];
			list[i] = nextElement;
		}
		size--;
		return currentElement;
	}

	/**
     * Retrieves the log entry at the specified index in the log list.
     *
     * @param idx the index of the log entry to return
     * @return requiredElement the log entry at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (idx less than 0 or idx greater than or equal size())
     */
	
	@Override
	public E getLog(int idx) {
		
		if(idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return list[idx];
	}

	/**
     * Returns the number of log entries in this log list.
     *
     * @return the number of log entries in this log list
     */
	@Override
	public int size() {
		
		return size;
	}
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int capacity) {
		
		E[] tempList =  (E[]) new Object[capacity * 2];
		
		for(int i = 0; i < size; i++) {
			tempList[i] = this.list[i];
		}
		this.list = tempList;
		
	}
	

}
