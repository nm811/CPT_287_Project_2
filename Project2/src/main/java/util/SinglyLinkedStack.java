/* Written by Adam Jost on 07/04/2021 */

package main.java.util;

/**
 * A singly-linked-list stack
 * @author: Adam Jost
 * @param <T>: Object type
 */
public class SinglyLinkedStack<T> {

	// Data fields
	private ListNode<T> top;
	private int numItems;

	// Default constructor
	public SinglyLinkedStack() {}

	// Copy constructor
	public SinglyLinkedStack(SinglyLinkedStack<T> other) { 
		// Set number of items to the other's number 
		// of items.
		numItems = other.numItems;
		
		// If there is at least one item: 
		if (numItems != 0) {
			// Copy the top item of the other stack.
			top = new ListNode<T>(other.top.data);
			ListNode<T> copy = top, original = other.top.next;
			// Traverse the stack, copying all of its items. 
			while (original != null) {
				copy.next = new ListNode<T>(original.data);
				copy = copy.next;
				original = original.next;
			}
		}
	}

	// Class-member methods
	/**
	 * Returns the number of items stored in the SinglyLinkedStack.
	 * @return: The number of items stored in the SinglyLinkedStack.
	 */
	public int size() {
		return numItems;
	} // Time Complexity: O(1)

	/**
	 * Tests whether the data container is empty.
	 * @return: {true} if the SinglyLinkedStack is empty; {false} otherwise
	 */
	public boolean isEmpty() {
		return numItems == 0;
	} // Time Complexity: O(1)

	/**
	 * Returns the top item in the SinglyLinkedStack.
	 * @return: The top item of the SinglyLinkedStack.
	 * @throws IllegalArgumentException: The SinglyLinkedStack is empty.
	 */
	public T peek() throws IllegalArgumentException {
		if (isEmpty()) {
			throw new IllegalArgumentException("Accessing non existent item."); 
		}
		
		return top.data;
	} // Time Complexity: O(1)

	/**
	 * Removes and returns the top item in the SinglyLinkedStack.
	 * @return: the removed top item of the SinglyLinkedStack.
	 * @throws IllegalArgumentException: The SinglyLinkedStack is empty.
	 */
	public T pop() throws IllegalArgumentException {
		if (isEmpty()) {
			throw new IllegalArgumentException("Accessing non existent item.");
		}
		// Remove the top item in the SinglyLinkedStack and decrease the number of items
		// in the LinkedListStack by one.
		T topElement = top.data;
		top = top.next;
		numItems--;
	
		// Return the removed item.
		return topElement;
	} // Time Complexity: O(1)

	/**
	 * Adds an item to the top of the LinkedListStack.
	 * @param item: The item being added.
	 */
	public void push(T item) {
		ListNode<T> newTop = new ListNode<T>(item);
		// Add a new item to the top of the stack and increment
		// the number of items in the SinglyLinkedStack. 
		newTop.next = top;
		top = newTop;
		numItems++;
	} // Time Complexity: O(1)

}
