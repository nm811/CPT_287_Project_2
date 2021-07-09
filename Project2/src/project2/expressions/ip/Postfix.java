/* Created by Adam Jost on 07/04/2021 */
/* Updated by Adam Jost on 07/09/2021 */

package project2.expressions.ip;

import java.util.StringTokenizer;

import project2.expressions.util.SinglyLinkedStack;

public class Postfix {
	/**
	 * Evaluates a postfix expression and returns the result.
	 * 
	 * @param expression: the expression to be evaluated.
	 * @return: the calculated result.
	 */
	public static String evaluate(String expression) {
		// Initialize a stack for performing the following.
		SinglyLinkedStack<Integer> stack = new SinglyLinkedStack<>();

		// StringTokenizer to break up the postfix expression into tokens
		StringTokenizer tokenizer = new StringTokenizer(expression);

		// Analyze all of the tokens one by one.
		while (tokenizer.hasMoreTokens()) {
			// The current token being analyzed.
			String token = tokenizer.nextToken();

			// If the token is a number (digit), push it to the stack.
			if (Character.isDigit(token.charAt(0))) {
				// Token is a positive integer.
				stack.push(Integer.valueOf(token));
			} else if (token.charAt(0) == '-' && token.length() > 1) {
				// This try/catch block accounts for some invalid expressions 
				// such as 1-=1 or 1+=2.
				try {
					// Token is a negative integer.
					stack.push(Integer.valueOf(token));
				} catch (NumberFormatException e) {
					return "Invalid Expression Error \nError: Invalid Use of Operators";
				}
			}

			// If the character is an operator then pop two
			// elements from stack and then perform the operation.
			else {
				int rtOperand = -1, lftOperand = -1;
				try {
					// Pop the top two digits to be used as the
					// left and right operands of the following
					// operation.
					rtOperand = stack.pop();
					lftOperand = stack.pop();
				} catch (IllegalArgumentException e) {
					// If an exception is thrown then infix expression was invalid
					// beyond the intelligent error handling contained in this
					// program. Successful evaluation is not possible so we output
					// an error message to the console.
					return "Invalid Expression Error\nError: Invalid Infix Expression";
				}

				// Analyze the character to determine the type
				// of operation to be performed and perform the
				// operation once a matching value is found.
				switch (token) {
				case "+": // Addition
					stack.push(lftOperand + rtOperand);
					break;
				case "-": // Subtraction
					stack.push(lftOperand - rtOperand);
					break;
				case "/": // Division
					try {
						stack.push((int) lftOperand / rtOperand);
					} catch (ArithmeticException e) { // Divide-by-zero error
						return "Arithmetic Error\nError: Divide By Zero";
						// return 0;
					}
					break;
				case "*": // Multiplication
					stack.push(lftOperand * rtOperand);
					break;
				case "^": // Power operator
					stack.push((int) Math.pow(lftOperand, rtOperand));
					break;
				case "%": // Modulus
					try {
						stack.push(lftOperand % rtOperand);
					} catch (ArithmeticException e) { // Modulus-by-zero error
						return "Arithmetic Error\nError: Modulus By Zero";
					}
				case ">": // Greater than
							// Push 1 for {true} if the leftOperand is
							// greater than the right operand, push 0 for {false}
							// otherwise.
					stack.push(lftOperand > rtOperand ? 1 : 0);
					break;
				case ">=": // Greater or equal to ">="
					// Push 1 for {true} if the leftOperand is
					// greater than or equal to the right operand,
					// push 0 for {false} otherwise.
					stack.push(lftOperand >= rtOperand ? 1 : 0);
					break;
				case "<": // Less than "<"
							// Push 1 for {true} if the leftOperand is
							// less than the right operand, push 0 for {false}
							// otherwise.
					stack.push(lftOperand < rtOperand ? 1 : 0);
					break;
				case "<=": // Less than or equal to "<="
					// Push 1 for {true} if the leftOperand is
					// less than or equal to the right operand,
					// push 0 for {false} otherwise.
					stack.push(lftOperand <= rtOperand ? 1 : 0);
					break;
				case "==": // Equal To "=="
					// Push 1 for {true} if the leftOperand is
					// equal to the right operand, push 0 for {false}
					// otherwise.
					stack.push(lftOperand == rtOperand ? 1 : 0);
					break;
				case "!=": // Not Equal To "!="
					// Push 1 for {true} if the leftOperand is not
					// equal to the right operand, push 0 for {false}
					// otherwise.
					stack.push(lftOperand != rtOperand ? 1 : 0);
					break;
				case "&&": // Logic And
					// Push 1 for {true} if both operands are equal to 1
					// and push 0 for {false} if either the left or right
					// operand is zero.
					stack.push(lftOperand > 0 && rtOperand > 0 ? 1 : 0);
					break;
				case "||": // Logic Or
					// Push 1 for {true} if either operand is equal to 1
					// and push 0 for {false} if both the left and right
					// operands are equal to zero.
					stack.push(lftOperand > 0 || rtOperand > 0 ? 1 : 0);
					break;
				}
			}
		}
		// Return the result of the evaluated expression.
		return String.valueOf(stack.peek());
	} // Time complexity: O(n)
}
