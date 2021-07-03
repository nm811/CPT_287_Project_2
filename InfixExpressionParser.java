package project_2;

import java.util.*;
import java.io.*;

public class InfixExpressionParser {
	// TODO: Additional error handling ideas: check if operator is valid, check if parentheses are 
	// balanced, check if character cannot be identified, what if user enters this: 4 4 / 2 --> invalid input
	
	// TODO: Method to check if token is operator
	private static boolean isOperator(String opr) { // Should this be private?
		String[] validOperators = {"^", "*", "/", "%", "+", "-", ">", ">=", "<", "<=", "==", "!=", "&&", "||"};
		for (int i = 0; i < validOperators.length; i++) {
			if (opr.equals(validOperators[i])) {
				return true;
			}
		}
		return false; // throw error if false?
	}
	
	// TODO: Method to check if character is part of operator
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
				while (Character.isDigit(exp.charAt(i))) {
					formattedExp.append(exp.charAt(i));
					i++;
				}
				i--;
				formattedExp.append(' ');
			} else if (isPartOfOperator(exp.charAt(i))) {
				while (isPartOfOperator(exp.charAt(i))) {
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
		}
	}
	// TODO: Convert infix to postfix method
	static String infixToPostfix(String infixExp) { // Should this be private?
		return ""; // change this later
	}
	
	// TODO: Evaluate postfix method
	static int evaluate(String postfixExp) {
		return 0; // change this later
	}
	
	// TODO: main method that reads input from file and outputs to console
	public static void main(String[] args) throws IOException {
		// BufferedReader and StringTokenizer are faster than Scanner
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line = br.readLine();
		StringTokenizer st;
		while (line != "") {
			
		}
	}
}
