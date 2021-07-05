/* Written by Neha Metlapalli and Adam Jost.
 * See individual methods for creation date comments.
 */

package project2.expressions.ip;

import java.util.StringTokenizer;

import project2.expressions.util.SinglyLinkedStack;


public class InfixParser {
	
	/* Written by Neha Metlapalli on 07/03/2021 */
	/**
	 * Checks if a character is part of a valid operator
	 * @param c: the character being tested to see if it's part of a valid operator
	 * @return: {true} if the argument character is part of a valid operator;
	 *          {false} otherwise
	 */
	private static boolean isPartOfOperator(char c) {
		// If the character is one of the following return {true}. Otherwise, return {false}.
		return c == '^' || c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '<' || c == '>' || c == '='
				|| c == '!' || c == '&' || c == '|';
	} // Time Complexity: O(1)
	
	/* Written by Neha Metlapalli on 07/03/2021 */
	/* Comments added by Adam Jost on 07/04/2021 */
	/**
	 * Formats an expression by adding whitespace around tokens
	 * @param exp: expression to be formatted (can be infix, prefix, or postfix)
	 * @return: resulting formatted expression as a String with whitespace around
	 *          tokens.
	 */
	public static String format(String exp) {
		StringBuilder formattedExp = new StringBuilder();
		// Analyze each Character of the expression one by one.
		for (int i = 0; i < exp.length(); i++) {
			// If the current Character is a number.
			if (Character.isDigit(exp.charAt(i))) {
				// Continuously append the current Character to the StringBuilder 
				// until an operator is reached. 
				while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
					formattedExp.append(exp.charAt(i));
					// Move to the next Character in the String.
					i++;
				}
				// Since the last Character was not a number we need to 
				// go back one position.
				i--;
				// Add a blank space after the number.
				formattedExp.append(' ');
			} else if (isPartOfOperator(exp.charAt(i))) {
				// Continuously append the current Character to the StringBuilder 
				// until a digit is reached.
				while (i < exp.length() && isPartOfOperator(exp.charAt(i))) {
					formattedExp.append(exp.charAt(i));
					i++;
				}
				// Since the last Character was not a operator or part of an
				// operator the we need to go back one position.
				i--;
				// Add a blank space after the operator. 
				formattedExp.append(' ');
			} else if (exp.charAt(i) == '(' || exp.charAt(i) == ')') {
				// If the Character is part of a pair of parenthesis then
				// append it to the StringBuilder followed by a blank space.
				formattedExp.append(exp.charAt(i));
				formattedExp.append(' ');
			}
		}
		// Return the formatted expression as a String.
		return formattedExp.toString();
	} // Time Complexity: O(n)
    
	/* Written by Adam Jost on 07/04/2021 */
	/**
	 * Returns the precedence of an operator.
	 * @param operator: the operator to find its precedence
	 * @return: the precedence of the operator
	 * @throws IllegalArgumentException: Operator is not supported.
	 */
	private static int precedence(String operator) {
		// Analyze the operator and return its precedence
		// when determined. 
		switch (operator) {
		case "^":
			return 7;
		case "*":
		case "/":
		case "%":
			return 6;
		case "+":
		case "-":
			return 5;
		case "<":
		case "<=":
		case ">":
		case ">=":
			return 4;
		case "==":
		case "!=":
			return 3;
		case "&&":
			return 2;
		case "||":
			return 1;
		default:
			throw new IllegalArgumentException(String.format("Operator %s is not "
					+ "supported.", operator));
		}
	}  // Time Complexity: O(1)
    
	/* Written by Neha Metlapalli on 07/03/2021 */
	/**
	 * Converts an infix expression to postfix expression.
	 * @param infixExp: an infix expression, tokens may or may not be separated by
	 *        white spaces.
	 * @return: Resulting postfix expression with tokens separated by whitespaces.
	 */
	public static String toPostfix(String infixExp) { // Should this be private?
		// All of the code for the infixToPostfix method I just copied and pasted
		// from the teacher's lecture slides just for reference
		StringTokenizer st = new StringTokenizer(infixExp);
		SinglyLinkedStack<String> stack = new SinglyLinkedStack<>();
		StringBuilder postfix = new StringBuilder();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			// If the current token is a number then we append it to the postfix expression.
			if (Character.isDigit(token.charAt(0))) {
				postfix.append(token).append(' ');
			}
			// If the current token is opening parenthesis, push it onto the stack.
			else if (token.equals("(")) {
				stack.push(token);
			} else if (token.equals(")")) {
				// Pop all operators from the stack, until an opening parenthesis is seen on top
				// of the stack.
				// Append each popped operator to the postfix expression string.
				while (!stack.peek().equals("(")) {
					postfix.append(stack.pop()).append(' ');
				}
				// Pop the opening parenthesis from the stack.
				stack.pop();
			} else {
				// The current token is an operator.
				// Continuously pop and add operators to postfix expression string until the 
				// stack is empty, an opening parenthesis is on the top of the stack, or the 
				// current operator has higher precedence than the operator on top of the stack.
				while (!stack.isEmpty() && !stack.peek().equals("(") && precedence(token) <= precedence(stack.peek())) {
					postfix.append(stack.pop()).append(' ');
				}
				// Push the operator to the stack.
				stack.push(token);
			}
		}
		// Pop the remaining operators from the stack and append them to the postfix
		// expression string.
		while (!stack.isEmpty()) {
			postfix.append(stack.pop()).append(' ');
		}
		
		return postfix.toString();
	}  // Time complexity: O(n)
}
/* Written by Neha Metlapalli and Adam Jost.
 * See individual methods for creation date comments.
 */

package project2.expressions.ip;

import java.util.StringTokenizer;

import project2.expressions.util.SinglyLinkedStack;


public class InfixParser {
	
	/* Written by Neha Metlapalli on 07/03/2021 */
	/**
	 * Checks if a character is part of a valid operator
	 * @param c: the character being tested to see if it's part of a valid operator
	 * @return: {true} if the argument character is part of a valid operator;
	 *          {false} otherwise
	 */
	private static boolean isPartOfOperator(char c) {
		// If the character is one of the following return {true}. Otherwise, return {false}.
		return c == '^' || c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '<' || c == '>' || c == '='
				|| c == '!' || c == '&' || c == '|';
	} // Time Complexity: O(1)
	
	/* Written by Neha Metlapalli on 07/03/2021 */
	/* Comments added by Adam Jost on 07/04/2021 */
	/**
	 * Formats an expression by adding whitespace around tokens
	 * @param exp: expression to be formatted (can be infix, prefix, or postfix)
	 * @return: resulting formatted expression as a String with whitespace around
	 *          tokens.
	 */
	public static String format(String exp) {
		StringBuilder formattedExp = new StringBuilder();
		// Analyze each Character of the expression one by one.
		for (int i = 0; i < exp.length(); i++) {
			// If the current Character is a number.
			if (Character.isDigit(exp.charAt(i))) {
				// Continuously append the current Character to the StringBuilder 
				// until an operator is reached. 
				while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
					formattedExp.append(exp.charAt(i));
					// Move to the next Character in the String.
					i++;
				}
				// Since the last Character was not a number we need to 
				// go back one position.
				i--;
				// Add a blank space after the number.
				formattedExp.append(' ');
			} else if (isPartOfOperator(exp.charAt(i))) {
				// Continuously append the current Character to the StringBuilder 
				// until a digit is reached.
				while (i < exp.length() && isPartOfOperator(exp.charAt(i))) {
					formattedExp.append(exp.charAt(i));
					i++;
				}
				// Since the last Character was not a operator or part of an
				// operator the we need to go back one position.
				i--;
				// Add a blank space after the operator. 
				formattedExp.append(' ');
			} else if (exp.charAt(i) == '(' || exp.charAt(i) == ')') {
				// If the Character is part of a pair of parenthesis then
				// append it to the StringBuilder followed by a blank space.
				formattedExp.append(exp.charAt(i));
				formattedExp.append(' ');
			}
		}
		// Return the formatted expression as a String.
		return formattedExp.toString();
	} // Time Complexity: O(n)
    
	/* Written by Adam Jost on 07/04/2021 */
	/**
	 * Returns the precedence of an operator.
	 * @param operator: the operator to find its precedence
	 * @return: the precedence of the operator
	 * @throws IllegalArgumentException: Operator is not supported.
	 */
	private static int precedence(String operator) {
		// Analyze the operator and return its precedence
		// when determined. 
		switch (operator) {
		case "^":
			return 7;
		case "*":
		case "/":
		case "%":
			return 6;
		case "+":
		case "-":
			return 5;
		case "<":
		case "<=":
		case ">":
		case ">=":
			return 4;
		case "==":
		case "!=":
			return 3;
		case "&&":
			return 2;
		case "||":
			return 1;
		default:
			throw new IllegalArgumentException(String.format("Operator %s is not "
					+ "supported.", operator));
		}
	}  // Time Complexity: O(1)
    
	/* Written by Neha Metlapalli on 07/03/2021 */
	/**
	 * Converts an infix expression to postfix expression.
	 * @param infixExp: an infix expression, tokens may or may not be separated by
	 *        white spaces.
	 * @return: Resulting postfix expression with tokens separated by whitespaces.
	 */
	public static String toPostfix(String infixExp) { // Should this be private?
		// All of the code for the infixToPostfix method I just copied and pasted
		// from the teacher's lecture slides just for reference
		StringTokenizer st = new StringTokenizer(infixExp);
		SinglyLinkedStack<String> stack = new SinglyLinkedStack<>();
		StringBuilder postfix = new StringBuilder();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			// If the current token is a number then we append it to the postfix expression.
			if (Character.isDigit(token.charAt(0))) {
				postfix.append(token).append(' ');
			}
			// If the current token is opening parenthesis, push it onto the stack.
			else if (token.equals("(")) {
				stack.push(token);
			} else if (token.equals(")")) {
				// Pop all operators from the stack, until an opening parenthesis is seen on top
				// of the stack.
				// Append each popped operator to the postfix expression string.
				while (!stack.peek().equals("(")) {
					postfix.append(stack.pop()).append(' ');
				}
				// Pop the opening parenthesis from the stack.
				stack.pop();
			} else {
				// The current token is an operator.
				// Continuously pop and add operators to postfix expression string until the 
				// stack is empty, an opening parenthesis is on the top of the stack, or the 
				// current operator has higher precedence than the operator on top of the stack.
				while (!stack.isEmpty() && !stack.peek().equals("(") && precedence(token) <= precedence(stack.peek())) {
					postfix.append(stack.pop()).append(' ');
				}
				// Push the operator to the stack.
				stack.push(token);
			}
		}
		// Pop the remaining operators from the stack and append them to the postfix
		// expression string.
		while (!stack.isEmpty()) {
			postfix.append(stack.pop()).append(' ');
		}
		
		return postfix.toString();
	}  // Time complexity: O(n)
}
