/* Written by Adam Jost on 07/04/2021 */
/* Updated by Neha Metlapalli on 07/05/2021 */
/* Updated by Adam Jost on 07/05/2021 */

package project2.expressions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import project2.expressions.io.Printer;
import project2.expressions.ip.InfixParser;
import project2.expressions.ip.Postfix;

public class ExpressionEvaluator {

	public static void main(String[] args) throws IOException {
		// Open input streams
		FileInputStream fin = new FileInputStream("expressions.txt");
		Scanner scanner = new Scanner(fin);
		
		// Print a heading and a short step-by-step explanation
		// of how the system operates.
		Printer.printHeadingExplanation(); 
		
		// Parse input data
		while (scanner.hasNext()) {
			String line = scanner.nextLine().trim();
			if (!line.equals("")) {
				// Read in the infix expression and format it with a single white
				// space surrounding each token.
				String infixExp = InfixParser.format(line);
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
		}
		
		// Print a notice that the system has reached
		// the end of all saved expressions. 
		Printer.printEndOfExpressions(); 

		// Close open streams.
		scanner.close();
		fin.close();
	}
}
