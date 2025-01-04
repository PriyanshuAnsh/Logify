package edu.ncsu.csc216.wolf_tracker.model.util;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class that contains unit tests for the SortedList class.
 * This class uses JUnit to verify the behavior of the SortedList implementation.
 * @author Priyanshu Dongre
 */
public class SortedListTest {

	/** 
	 * A sorted list of strings used for testing purposes.
	 * This list maintains its elements in sorted order and 
	 * is utilized in various test cases to verify the 
	 * functionality of the SortedList class.
	 */
	private SortedList<String> list;
	
	/**
     * Sets up the SortedList instance before each test.
     * Initializes the list variable to a new instance of SortedList
     * for testing purposes.
     */
	@BeforeEach
	public void setUp() {
		list = new SortedList<>();
	}

	/**
     * Tests the add method of the SortedList class.
     * Verifies that elements can be added to the list in sorted order,
     * and that the size of the list is updated correctly. 
     * Also checks that adding null elements and duplicate elements
     * throws the appropriate exceptions.
     */
	@Test
	public void testAdd() {
		list.add("Ansh");
		assertEquals(1, list.size());
		list.add("Priyanshu");
		assertEquals("Ansh", list.get(0));
		assertEquals(2, list.size());
		assertEquals("Priyanshu", list.get(1));
		list.add("Andy");
		assertEquals(3, list.size());
		assertEquals("Andy", list.get(0));
		assertEquals("Ansh", list.get(1));
		assertEquals("Priyanshu", list.get(2));
		
		list.add("Preston");
		assertEquals(4, list.size());
		assertEquals("Andy", list.get(0));
		assertEquals("Ansh", list.get(1));
		assertEquals("Preston", list.get(2));
		assertEquals("Priyanshu", list.get(3));
		
		
		NullPointerException e1 = assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals("Cannot add null element.", e1.getMessage());
	
		IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> list.add("Ansh"));
		assertEquals("Cannot add duplicate element.", e2.getMessage());
	}
	
	
	/**
     * Tests the remove method of the SortedList class.
     * Verifies that elements can be removed from the list and that
     * the size of the list is updated correctly. 
     * Also checks that removing an element at an invalid index
     * throws the appropriate exception.
     */
	@Test
	public void testRemove() {
		list.add("Ansh");
		list.add("Priyanshu");
		
		assertEquals(2, list.size());
		
		assertEquals("Priyanshu", list.remove(1));
		assertEquals(1, list.size());
		
		IndexOutOfBoundsException e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertEquals("Invalid index.", e1.getMessage());
		
	}
	
	
}
