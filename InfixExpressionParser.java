package project_2;

import java.util.*;
import java.io.*;

public class InfixExpressionParser {
	// TODO: Additional error handling ideas: check if operator is valid, check if parentheses are 
	// balanced, check if character cannot be identified, what if user enters this: 4 4 / 2 --> invalid input
	
	// TODO: Add documentation comments and comments to explain code
	
	/**
	 * Checks if a token is a valid operator
	 * @param opr: the String that is being tested to see if it's a valid operator
	 * @return: {true} if the argument is a valid operator; {false} otherwise
	 */
	private static boolean isOperator(String opr) { // Should this be private?
		String[] validOperators = {"^", "*", "/", "%", "+", "-", ">", ">=", "<", "<=", "==", "!=", "&&", "||"};
		for (int i = 0; i < validOperators.length; i++) {
			if (opr.equals(validOperators[i])) {
				return true;
			}
		}
		return false; // throw error if false?
	}
	
	/**
	 * Checks if a character is part of a valid operator
	 * @param c: the character being tested to see if it's part of a valid operator
	 * @return: {true} if the argument character is part of a valid operator; {false} otherwise
	 */
	private static boolean isPartOfOperator(char c) { // Should this be private?
		return c == '^' || c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || 
			   c == '<' || c == '>' || c == '=' || c == '!' || c == '&' || c == '|';
		// throw error if false?
	}
	
	// TODO: Format infix expression method
	static String format(String exp) { // Should this be private?
		StringBuilder formattedExp = new StringBuilder();
		for (int i = 0; i < exp.length(); i++) {
			if (Character.isDigit(exp.charAt(i))) {
				while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
					formattedExp.append(exp.charAt(i));
					i++;
				}
				i--;
				formattedExp.append(' ');
			} else if (isPartOfOperator(exp.charAt(i))) {
				while (i < exp.length() && isPartOfOperator(exp.charAt(i))) {
					formattedExp.append(exp.charAt(i));
					i++;
				}
				i--;
				formattedExp.append(' ');
			} else if (exp.charAt(i) == '(' || exp.charAt(i) == ')') {
				formattedExp.append(exp.charAt(i));
				formattedExp.append(' ');
			} else if (exp.charAt(i) != ' ') {
				// throw error
			}
		}
		return formattedExp.toString();
	}
	
	// TODO: Precedence method
	private static int precedence(String operator) { // Should this be private?
		if (operator.equals("^")) {
			return 7;
		} else if (operator.equals("*") || operator.equals("/") || operator.equals("%")) {
			return 6;
		} else if (operator.equals("+") || operator.equals("-")) {
			return 5;
		} else if (operator.equals("<") || operator.equals("<=") || operator.equals(">") || 
				   operator.equals(">=")) {
			return 4;
		} else if (operator.equals("==") || operator.equals("!=")) {
			return 3;
		} else if (operator.equals("&&")) {
			return 2;
		} else if (operator.equals("||")) {
			return 1;
		} else {
			// throw error
			throw new IllegalArgumentException("fregeg");
		}
	}
	// TODO: Convert infix to postfix method
	static String infixToPostfix(String infixExp) { // Should this be private?
		// All of the code for the infixToPostfix method I just copied and pasted 
		// from the teacher's lecture slides just for reference
		
		StringTokenizer st = new StringTokenizer(format(infixExp));
		Stack<String> stack = new Stack<String>();
		StringBuilder postfixExp = new StringBuilder();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			// If the current token is an operand, append it to the postfix expression string.
			if (Character.isDigit(token.charAt(0))) { 
				postfixExp.append(token).append(' '); 
			}
			// If the current token is opening parenthesis, push it onto the stack.
			else if (token.equals("(")) { 
				stack.push(token); 
			}
			else if (token.equals(")")) {
				// Pop all operators from the stack, until an opening parenthesis is seen on top of the stack.
				// Append each popped operator to the postfix expression string.
				while (!stack.peek().equals("(")) { 
					postfixExp.append(stack.pop()).append(' '); 
				}
				// Pop the opening parenthesis from the stack.
				stack.pop();
			} else {
				// The current token is an operator.
				// Keep popping operators (and append them to postfix expression string) from the stack, until:
				// 1) the stack is empty,
				// 2) an opening parenthesis is seen on top of the stack, or
				// 3) the current operator has higher precedence than the operator on top of the stack.
				while (!stack.isEmpty() && !stack.peek().equals("(") &&
				precedence(token) <= precedence(stack.peek())) {
					postfixExp.append(stack.pop()).append(' ');
				}
				// Push the current operator onto the stack.
				stack.push(token);
			}
		}
		// Pop the remaining operators from the stack and append them to the postfix expression string.
		while (!stack.isEmpty()) { 
			postfixExp.append(stack.pop()).append(' '); 
		}
		return postfixExp.toString();
	}
	
	// TODO: Evaluate postfix method
	static int evaluate(String postfixExp) {
		return 0; // change this later
	}
	
	// TODO: main method that reads input from file and outputs to console
	public static void main(String[] args) throws IOException {
		// BufferedReader and StringTokenizer are faster than Scanner
		// We can change this though if it's confusing
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line = br.readLine();
		StringTokenizer st;
		while (line != "") {
			
		}
		br.close();
	}
}
