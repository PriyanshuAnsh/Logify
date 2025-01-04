package edu.ncsu.csc216.wolf_tracker.model.util;

/**
 * A class that implements a sorted list using a generic type.
 * The list maintains its elements in sorted order according to their natural ordering.
 *
 * @author Priyanshu Dongre
 * @param <E> the type of elements in this sorted list.
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/** 
	 * The current number of elements in the sorted list. 
	 */
	private int size;

	/** 
	 * A reference to the first node in the linked list representing the sorted list. 
	 */
	private ListNode front;
	
	
	/**
     * Constructs a new SortedList.
     */
	public SortedList() {
		size = 0;
		front = null;
	}
	
	 /**
     * Adds the specified element to the sorted list in the correct position.
     *
     * @param element the element to be added to the list
     * @throws NullPointerException if the specified element is null
     */
	@Override
	public void add(E element) {
		
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		
		ListNode newNode = new ListNode(element, null);
		
		if(front == null) {
			front = newNode;
			size++;
			return;
		}
		ListNode currentNode = front;
		ListNode previousNode = null;
		ListNode preNewNode = null;
		boolean insertAfter = true;
		
		if(this.contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		while(currentNode != null) {
			
			
			if(newNode.data.compareTo(currentNode.data) > 0) {
				
				preNewNode = currentNode;
//				newNode.next = currentNode.next;
//				currentNode.next = newNode;
			} else if(newNode.data.compareTo(currentNode.data) < 0) {
				if(previousNode == null) {
					newNode.next = front;
					front = newNode;
					insertAfter = false;
					break;
				} else {
					previousNode.next = newNode;
					newNode.next = currentNode;
					insertAfter = false;
					break;
				}
			} 

			previousNode = currentNode;
			
			currentNode = currentNode.next;

			
			
//			if(currentNode.data.compareTo(newNode.data) > 0) {
//				newNode.next = currentNode.next;
//				currentNode.next = newNode;
//				size++;
//			} else if(currentNode.data.compareTo(newNode.data) < 0) {
//				if(previousNode == null) {
//					
//				}
//				previousNode.next = newNode;
//				newNode.next = currentNode;
//				size++;
//			} else if(currentNode.data.compareTo(newNode.data) == 0) {
//				throw new IllegalArgumentException("Cannot add duplicate element");
//			} else {
//				previousNode = currentNode;
//				currentNode = currentNode.next;
//			}
//			
			
			
//			if(currentNode.next == null) {
//				tail = currentNode;
//			}
//			
//			currentNode = currentNode.next;
			
		}
		
		if(insertAfter) {
			newNode.next = preNewNode.next;
			preNewNode.next = newNode;
		}
//		tail.next = newNode;
		size++;		 
	}

	
	 /**
     * Removes the element at the specified position in this list.
     *
     * @param idx the index of the element to be removed
     * @return removedElement the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (idx less than 0 or idx greater than or equal to size)
     */
	@Override
	public E remove(int idx) {

		if(idx < 0 || idx >= size || size == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		int index = 0;
		ListNode removingNode = null;
		ListNode currentNode = front;
		ListNode previousNode = null;
		while(currentNode != null) {
			if(index == idx) {
				removingNode = currentNode;
				if(previousNode == null) {
					front = currentNode.next;
				} else {
					previousNode.next = currentNode.next;
				}
				
				size--;
				return removingNode.data;
			} else {
				previousNode = currentNode;
				currentNode = currentNode.next;
				index++;
			}
		}
		
		return null;
	}

	
	/**
     * Checks if the specified element is contained in the sorted list.
     *
     * @param element the element to be checked for containment
     * @return true if the list contains the specified element, false otherwise
     * @throws NullPointerException if the specified element is null
     */
	@Override
	public boolean contains(E element) {
		
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		ListNode currentNode = front;
		while(currentNode != null) {
			if(currentNode.data.compareTo(element) == 0) {
				return true;
			} else {
				currentNode = currentNode.next;
			}
		}
		return false;
	}

	
	/**
     * Retrieves the element at the specified position in this list.
     *
     * @param idx the index of the element to return
     * @return requiredElement the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (idx less than 0 or idx greater than or equal to size)
     */
	@Override
	public E get(int idx) {

		
		if(idx < 0 || idx >= size || size == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		int index = 0;
		ListNode temp = front;
		while(temp != null) {
			if(index == idx) {
				return temp.data;
			}
			
			index++;
			temp = temp.next;
		}
		return null;
	}

	/**
     * Returns the number of elements in this list.
     *
     * @return size the number of elements in this list
     */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	

	
	/**
	 * A private inner class that represents a node in a linked list.
	 *
	 * @author Priyanshu Dongre
	 */
	private class ListNode {
		
		/** The data stored in this node */
	    public E data;

	    /** A reference to the next node in the linked list */
	    public ListNode next;
		
	    
	    /**
	     * Constructs a new ListNode with the specified data and next node reference.
	     *
	     * @param data the data to be stored in this node
	     * @param next the reference to the next node in the linked list
	     */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
