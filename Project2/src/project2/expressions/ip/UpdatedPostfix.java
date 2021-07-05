/* Written by Neha Metlapalli and Adam Jost.
 * See individual methods for creation date comments.
 */

package project2.expressions.ip;

import project2.expressions.util.SinglyLinkedStack;
import java.util.*;
import java.lang.Math;

public class UpdatedPostfix {
	/* Written by Neha Metlapalli on 07/05/2021 */
	/**
	 * Checks if a token is a valid operator
	 * @param opr: the String that is being tested to see if it's a valid operator
	 * @return: {true} if the argument is a valid operator; {false} otherwise
	 */
	private static boolean isOperator(String opr) {
		String[] validOperators = {"^", "*", "/", "%", "+", "-", ">", ">=", "<", "<=", "==", "!=", "&&", "||"}; // List of valid operators
		for (int i = 0; i < validOperators.length; i++) { // For each valid operator
			if (opr.equals(validOperators[i])) { // If the input String equals the valid operator
				return true; // Then the input String is a valid operator, so return {true}
			}
		}
		return false; // If the input String does not equal any valid operator, then return {false}
	}
	
	/* Written by Adam Jost on 07/04/2021 */
	/**
	 * Evaluates a postfix expression and returns the result.
	 * @param expression: the expression to be evaluated.
	 * @return: the calculated result.
	 */
	// For the following modified version of the evaluate method to work, the toPostfix method has to return a postfix expression with whitespaces
	public static int evaluate(String postfixExp) {
		// The code for the toPostfix method is a slightly modified version of Prof. Wang's infixToPostfix method, 
		// which can be found here: https://gist.github.com/wangbuhuai/c3b716eadf6e7aa70b013975d440268e#file-02-stackalgorithms-java
		// Key modifications:
		// - StringTokenizer is used instead of scanner for efficiency.
		// - This modified version can also evaluate the power, modulus, 
		//   comparison (>, >=, <, <=), equality comparison (==, !=), and logical (&&, ||) 
		//   operators
		
		// Create StringTokenizer to break up the postfix expression into tokens
		StringTokenizer st = new StringTokenizer(postfixExp);

		// Note: if you want the postfix expression to first be formatted so the user 
		// doesn't need to provide an expression with whitespaces, change access modified of 
		// format method to default, and change the above line and comments to:
		/*
			// The input postfix expression is first formatted with whitespace around tokens so that 
			// the user does not need to provide a correctly formatted input expression
			// Then, create StringTokenizer to break up the formatted postfix expression into tokens
			StringTokenizer st = new StringTokenizer(format(postfixExp));

		*/
		// For the first comment crediting this method to Prof. Wang, this should also 
		// be a key modification then:
		// - The input infix expression does not need to be formatted correctly for the method to produce the correct output. 

		// Initialize a stack.
		SinglyLinkedStack<Integer> stack = new SinglyLinkedStack<>();

		// Parse each token in the postfix expression.
		while (st.hasMoreTokens()) {
		    String token = st.nextToken();

		    // If the current token is an operand, push it onto the stack.
		    if (Character.isDigit(token.charAt(0))) { 
			stack.push(Integer.valueOf(token)); 
		    }
		    else if (isOperator(token)) {
			// Pop the right operand from the stack.
			int right = stack.pop();

			// Pop the left operand from the stack.
			int left = stack.pop();

			// Analyze the token to determine the type
			// of operation to be performed and push 
			// the evaluation result onto the stack.
			if (token.equals("^")) { // Power
				stack.push((int)Math.pow(left, right));
			} else if (token.equals("*")) { // Multiplication
				stack.push(left * right); 
			} else if (token.equals("/")) { // Division
				// I changed this part of the error handling
				if (right == 0) {
					System.out.print("Error: Cannot Divide By ");
					return 0;
				} else {
					stack.push(left / right);
				}
			} else if (token.equals("%")) { // Modulus
				stack.push(left % right);
			} else if (token.equals("+")) { // Addition
				stack.push(left + right); 
			} else if (token.equals("-")) { // Subtraction
				stack.push(left - right); 
			} else if (token.equals(">")) { // Greater than 
				// Push 1 for {true} if the left operand is
				// greater than the right operand, and
				// push 0 for {false} otherwise.
				stack.push(left > right ? 1 : 0);
			} else if (token.equals(">=")) { // Greater than or equal to
				// Push 1 for {true} if the left operand is
				// greater than or equal to the right operand,
				// and push 0 for {false} otherwise.
				stack.push(left >= right ? 1 : 0);
			} else if (token.equals("<")) { // Less than
				// Push 1 for {true} if the left operand is
				// less than the right operand, and
				// push 0 for {false} otherwise.
				stack.push(left < right ? 1 : 0);
			} else if (token.equals("<=")) { // Less than or equal to
				// Push 1 for {true} if the left operand is
				// less than or equal to the right operand,
				// and push 0 for {false} otherwise.
				stack.push(left <= right ? 1 : 0);
			} else if (token.equals("==")) { // Equal to
				// Push 1 for {true} if the left operand is
				// equal to the right operand, and push 0 for {false} 
				// otherwise.
				stack.push(left == right ? 1 : 0);
			} else if (token.equals("!=")) { // Not equal to
				// Push 1 for {true} if the left operand is
				// not equal to the right operand, and push 0 for {false} 
				// otherwise.
				stack.push(left != right ? 1 : 0);
			} else if (token.equals("&&")) { // Logic and
				// Push 1 for {true} if both operands are greater than or equal to 1,
				// and push 0 for {false} otherwise.
				if (left >= 1 && right >= 1) {
					stack.push(1);
				} else {
					stack.push(0);
				}
			} else { // Logic or "||"
				// Push 0 for {false} if both operands are equal to 0
				// operands are equal to zero, and push 1 for {true} 
				// otherwise.
				stack.push(left + right == 0 ? 0 : 1);
			}
		    } else {
			// throw error because token unidentified
		    }
		}
		// The only value in the stack is the evaluation result.
		return stack.peek();
	}  // Time complexity: O(n)
}
