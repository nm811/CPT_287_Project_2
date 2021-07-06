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
	/* Update by Adam Jost on 07/05/2021 */ 
	/**
	 * 
	 * Formats an expression by adding whitespace around tokens
	 * @param exp: expression to be formatted (can be infix, prefix, or postfix)
	 * @return: resulting formatted expression as a String with whitespace around
	 *          tokens.
	 * @throws IllegalArgumentException: Variables are not currently supported.
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
			} else if (exp.charAt(i) == '-' && i == 0 || exp.charAt(i) == '-' && isPartOfOperator(exp.charAt(i-1)) ||
					exp.charAt(i) == '-' && Character.isWhitespace(exp.charAt(i-1)) && isPartOfOperator(exp.charAt(i-2)) ||
					exp.charAt(i) == '-' && exp.charAt(i-1) == '(' || exp.charAt(i) == '-' && exp.length()>=3 &&
					Character.isWhitespace(exp.charAt(i-1)) && exp.charAt(i-2) == '(') {
				// The current character is a '-' but what is its purpose? 
				// The above check is to check whether its purpose is to be a 
				// subtraction operator or to negate an integer value. If it is 
				// {true} then the symbol is used to negate the following value
				// otherwise it is a subtraction operator which means that this
				// check is {false}.
				
				// The above checks for the following scenarios:
				// 1.) A '-' symbol is the first character of the expression
				//     example: -2 +1
				// 2.) A '-' symbol is found directly after an operator
				//     example: 1 +-2
				// 3.) A '-' symbol is found directly after an operator that 
				//     is followed by whitespace.
				//     example: 1 +   -2
				// 4.) A '-' symbol is found directly after an opening parentheses.
				//     example 1 + (-2+1)
				// 5.) A '-' symbol is found directly after an opening parentheses 
				//     which is followed by whitespace.
				//     example: 1 + (    -2+1)
		
				// Append the operator.
				formattedExp.append(exp.charAt(i));
				
				// Skip any white space between the '-' symbol and its integer value
				// counterpart. (example: "1 + -    2" will convert to "1 + -2").
				while (i+1 < exp.length() && Character.isWhitespace(exp.charAt(i+1))) {
					i++;
				}
				
				// Continuously append each digit of the number until the end 
				// of the number is reached. 
				while (i+1 < exp.length() && Character.isDigit(exp.charAt(i+1))) {
					formattedExp.append(exp.charAt(i+1));
					i++;
				}
				
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
				// operator then we need to go back one position.
				i--;
				// Add a blank space after the operator. 
				formattedExp.append(' ');
			} else if (exp.charAt(i) == '(' || exp.charAt(i) == ')') {
				// If the Character is part of a pair of parenthesis then
				// append it to the StringBuilder followed by a blank space.
				formattedExp.append(exp.charAt(i));
				formattedExp.append(' ');
			} else if (Character.isWhitespace(exp.charAt(i))) {
				// If the character is any form of whitespace then simply continue to
				// the next character to be evaluated. 
				continue;
			} else if (Character.isLetter(exp.charAt(i))) {
				// If the user is attempting to use variables in their infix expression then
				// throw an IllegalArgumentException notifying the user that the found variable 
				// is not currrently supported in this version. 
				throw new IllegalArgumentException(String.format("Found letter \"%s\" but expected a numeric value."
						+ " Variables are not currently supported.", exp.charAt(i)));
			} else {
				// If none of the above apply then the character must be an unrecognized symbol, so
				// throw an IllegalArgumentException notifying the user that the item is not 
				// currently supported. 
				throw new IllegalArgumentException(String.format("Operator \"%s\" is not supported.", exp.charAt(i)));
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
	/* Updated by Adam Jost on 07/04/2021 */
	/* Updated by Neha Metlapalli on 07/05/2021 */
	
	/**
	 * Converts an infix expression to postfix expression.
	 * @param infixExp: an infix expression, tokens may or may not be separated by
	 *        white spaces.
	 * @return: Resulting postfix expression with tokens separated by whitespaces.
	 */
	public static String toPostfix(String infixExp) { 
		// The code for the toPostfix method is a slightly modified version of Prof. Wang's infixToPostfix method, 
 		// which can be found here: https://gist.github.com/wangbuhuai/c3b716eadf6e7aa70b013975d440268e#file-02-stackalgorithms-java
 		// Key modifications:
 		// - StringTokenizer is used instead of scanner for efficiency.
 		// - The input infix expression does not need to be formatted correctly for the method to produce the correct output.
		
		StringTokenizer st = new StringTokenizer(infixExp);
		SinglyLinkedStack<String> stack = new SinglyLinkedStack<>();
		StringBuilder postfix = new StringBuilder();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			// If the current token is a number then we append it to the postfix expression.
			if (Character.isDigit(token.charAt(0))) {
				postfix.append(token).append(' ');
			} else if (token.charAt(0) == '-' && token.length() > 1) {
				// Token is a negative number. Append the negative number.
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
