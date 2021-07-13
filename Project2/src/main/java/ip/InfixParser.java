/* Written by Neha Metlapalli and Adam Jost.
 * See individual methods for creation date comments.
 */

package main.java.ip;

import java.util.StringTokenizer;

import main.java.util.SinglyLinkedStack;

public class InfixParser {

	/* Written by Neha Metlapalli on 07/03/2021 */
	/**
	 * Checks if a character is part of a valid operator.
	 * 
	 * @param c: the character being tested to see if it's part of a valid operator
	 * @return: {true} if the argument character is part of a valid operator;
	 *          {false} otherwise.
	 */
	private static boolean isPartOfOperator(char c) {
		// If the character is one of the following return {true}. Otherwise, return
		// {false}.
		return c == '^' || c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '<' || c == '>' || c == '='
				|| c == '!' || c == '&' || c == '|';
	} // Time Complexity: O(1)

	/* Written by Neha Metlapalli on 07/03/2021 */
	/* Comments added by Adam Jost on 07/04/2021 */
	/* Update by Adam Jost on 07/09/2021 */
	/**
	 * 
	 * Formats an expression by adding whitespace around tokens. Note that the
	 * parameter expression must be free of all whitespace.
	 * 
	 * @param exp: the expression to be formatted which must be free of whitespace.
	 * @return: resulting formatted expression as a String with whitespace around
	 *          tokens.
	 * @throws IllegalArgumentException: Variables are not currently supported.
	 */
	public static String format(String exp) {
		// Uncomment the following line to allow passed-in
		// expressions containing whitespace.
		// exp = exp.replaceAll("[\\s\\p{Z}]","");

		// This is used to build the formatted expression.
		StringBuilder formattedExp = new StringBuilder();

		// Now we analyze each Character of the expression one by one.
		for (int i = 0; i < exp.length(); i++) {
			// Save the current Character.
			char c = exp.charAt(i);
			// Save the Character before and after
			// the current Character of the expression
			// that is being analyzed.
			char beforeC = ' ', afterC = ' ';
			if (i != 0) {
				beforeC = exp.charAt(i - 1);
			}
			if (i != exp.length() - 1) {
				afterC = exp.charAt(i + 1);
			}

			if (Character.isDigit(c)) {
				// Continuously append the current Character to the StringBuilder
				// until an operator is reached.
				while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
					formattedExp.append(exp.charAt(i++));
				}
				// Since the last Character was not a number we need to
				// go back one position.
				i--;
				// Add a blank space after the number.
				formattedExp.append(' ');
			} else if (c == '-' && afterC == '-') {
				// This handles the following scenarios:
				// 1.) 1--1
				// 2.) 1--6^2
				// 3.) 1--(1+1)
				formattedExp.append('+').append(' ');
				// Skip the next '-' operator.
				i++;
			} else if (exp.length() > 3 && c == '-' && beforeC == ' ' && exp.charAt(i + 2) == '^'
					|| exp.length() > 3 && c == '-' && beforeC == '(' && exp.charAt(i + 2) == '^'
					|| i != exp.length() - 2 && c == '-' && exp.charAt(i + 2) == '^' && beforeC == '+') {
				// The above checks and handles the following scenarios:
				// 1.) -6^2 which should equate to -36.
				// 2.) (-6^2) which should also equate to -36.
				// 3.) 1+-6^2 which should equate to -35
				// This will exclude examples such as: 1--6^2 which was handled directly
				// above.
				formattedExp.append("-1").append(' ');
				formattedExp.append('*').append(' ');
			} else if (c == '-' && beforeC == ' ' && afterC == '('
					|| c == '-' && isPartOfOperator(beforeC) && afterC == '(') {
				// The above checks and handles the following scenarios:
				// 1.) -(1+1) which equates to -2.
				// 2.) 1+-(1+1) which equates to 1 - 2.
				// To achieve this I chose to not append the operator itself but to
				// instead multiply the value within the parentheses by -1. To do this
				// we must append "-1 *" which means -(1+1) will turn into -1*(1+1).
				formattedExp.append("-1").append(' ');
				formattedExp.append('*').append(' ');
			} else if (c == '-' && i == 0 || c == '-' && isPartOfOperator(beforeC) || c == '-' && beforeC == '('
					|| c == '-' && afterC == '(') {
				// The above check is to check whether its purpose is to be a
				// subtraction operator or to negate an integer value. If it is
				// {true} then the symbol is used to negate the following value
				// otherwise it is a subtraction operator which means that this
				// check is {false} and moves to the next else-if block.

				// The above checks for the following scenarios:
				// 1.) A '-' symbol is the first character of the expression
				// example: -2+1
				// 2.) A '-' symbol is found directly after an operator
				// example: 1+-2
				// 3.) A '-' symbol is found directly after an opening parentheses.
				// example 1+(-2+1)
				// Append the operator.
				formattedExp.append(c);
				i++;
				// Continuously append each digit of the number until the end
				// of the number is reached.
				while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
					formattedExp.append(exp.charAt(i++));
				}
				// Add a blank space after the number.
				formattedExp.append(' ');
				i--;
			} else if (c == '+' && i == 0 || c == '+' && isPartOfOperator(beforeC) || c == '+' && beforeC == '('
					|| c == '+' && afterC == '(' && i == 0) {
				// The above checks for the following (+) scenarios:
				// 1.) A '+' symbol is the first character of the expression
				// example: +2+1
				// 2.) A '+' symbol is found directly after an operator
				// example: 1-+2
				// 3.) A '+' symbol is found directly after an opening parentheses.
				// example 1+(+2+1)

				// Although some of the expressions this check helps correct may
				// be invalid we can use this to convert the expression to a valid
				// expression that we are assuming is what the user most likely
				// meant by simply removing the invalidly placed '+' operator.

				// If this is the case we simply skip this symbol because it
				// serves no reason purpose and has no impact on the outcome
				// of the solution.
				continue;
			} else if (isPartOfOperator(c)) {
				// If the next Character is an operator then we append it.
				// We also append the following Character if it to
				// is an operator. This accounts for:
				// 1.) >=
				// 2.) <=
				// 3.) &&
				// 4.) ||
				// 5.) ==
				// 6.) !=
				while (i < exp.length() && isPartOfOperator(exp.charAt(i))) {
					formattedExp.append(exp.charAt(i++));
					// This accounts for not adding a negative or positive
					// symbol to the end of an operator.
					if (exp.charAt(i) == '-' || exp.charAt(i) == '+') {
						break;
					}
				}
				// Since the last Character was not a operator or part of an
				// operator the we need to go back one position.
				i--;
				// Add a blank space after the operator.
				formattedExp.append(' ');
			} else if (c == '(' && Character.isDigit(beforeC)) {
				// Accounts for:
				// -5(8) which converts to -5 * ( 8 )
				formattedExp.append('*').append(' ');
				formattedExp.append(exp.charAt(i)).append(' ');
				
			} else if (c == '(' || c == ')') {
				// If the Character is part of a pair of parenthesis then
				// append it to the StringBuilder followed by a blank space.
				formattedExp.append(exp.charAt(i)).append(' ');
				
			} else if (Character.isLetter(c)) {
				// If the user is attempting to use variables in their infix expression then
				// print the following notifying the user that the infix expression is invalid.
				formattedExp.append(exp.charAt(i)).append(' ');
			} else {
				// If none of the above apply then the character must be an unrecognized symbol,
				// so we return an "Invalid Expression Error" message.
				return "Invalid Expression Error";			
			}
		}

		// Return the formatted expression as a String.
		return formattedExp.toString();
	} // Time Complexity: O(n)
	
	/* Original version written by Neha Metlapalli */
	/* Current version completely written by Adam Jost on 07/04/2021 */
	/**
	 * Returns the precedence of an operator.
	 * 
	 * @param operator: the operator to find its precedence
	 * @return: the precedence of the operator
	 * @throws IllegalArgumentException: Operator is not supported.
	 */
	private static int precedence(String operator) {
		// Try/catch block allows the process to continue without
		// crashing the program. Invalid operators will result in
		// an error message later in the program stating "Invalid
		// Expression Error. This will occur in the evaluate method
		// found in the Postfix class. 
		try {
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
				throw new IllegalArgumentException(String.format("Operator %s is not supported.", operator));
			}
		} catch (IllegalArgumentException e) {
			return -1;
		}
	} // Time Complexity: O(1)

	/* Written by Neha Metlapalli on 07/03/2021 */
	/* Updated by Adam Jost on 07/04/2021 */
	/* Updated by Neha Metlapalli on 07/05/2021 */
	/* Updated by Adam Jost on 07/05/2021 */
	/**
	 * Converts an infix expression to postfix expression.
	 * 
	 * @param infixExp: an infix expression, tokens may or may not be separated by
	 *        white spaces.
	 * @return: Resulting postfix expression with tokens separated by whitespaces.
	 */
	public static String toPostfix(String infixExp) {
		// The code for the toPostfix method is a slightly modified version of Prof.
		// Wang's infixToPostfix method,
		// which can be found here:
		// https://gist.github.com/wangbuhuai/c3b716eadf6e7aa70b013975d440268e#file-02-stackalgorithms-java
		// Key modifications:
		// - StringTokenizer is used instead of scanner for efficiency.
		// - Supports negative integer values.

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
				if (precedence(token) == -1) {
					return "Invalid Expression Error";
				}
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
	} // Time complexity: O(n)
}
