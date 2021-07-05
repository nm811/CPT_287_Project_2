/* Written by Adam Jost on 07/04/2021 */

package project2.expressions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import project2.expressions.ip.InfixParser;
import project2.expressions.ip.Postfix;

public class ExpressionEvaluator {

	public static void main(String[] args) throws IOException {
		// Open input streams
		FileInputStream fin = new FileInputStream("expressions.txt");
		Scanner scanner = new Scanner(fin);
	
		// Parse input data
		while (scanner.hasNext()) {
			// Read in the infix expression.
			String infixExp = scanner.nextLine();
			// Print the infix expression to the console.
			System.out.printf("Infix:   %s\n", infixExp);
			// Convert the infix expression to a postfix expression.
			String postfixExp = InfixParser.toPostfix(infixExp);
			// Print the postfix expression to the console.
			System.out.printf("Postfix: %s\nResult:  ", postfixExp);
			// Evaluate the postfix expression and then print the
			// result to the console. 
			System.out.printf("%s\n\n", Postfix.evaluate(postfixExp));
		}
		// Close open streams.
		scanner.close();
		fin.close();
	}
}
