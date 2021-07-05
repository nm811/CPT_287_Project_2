/* Written by Adam Jost on 07/04/2021 */

package project2.expressions.util;

/**
 * A Node object
 * @author: Adam Jost
 * @param <T>: Object type
 */
public class ListNode<T> {
	// Data fields
	T data;
	ListNode<T> next;
	
	// Constructor
	ListNode(T value) { 
		data = value; 
	} // Time complexity: O(1);
}
