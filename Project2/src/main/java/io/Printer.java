/* Create by Adam Jost on 07/05/2021 */
 
package main.java.io;

public class Printer {
	
	/** Prints a single-lined separator to the console. */
	private static void printSingleSeperator() {
		for (int i=0; i<=53; i++) {
			System.out.print("=");
		}
		System.out.println();
	}
	
	/** Prints a dashed, single-lined separator to the console. */
	private static void printDashSeperator() {
		for (int i=0; i<=53; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	/** Prints a heading and a step-by-step explanation of the system
	 *  to the console. 
	 */
	public static void printHeadingExplanation() {
		printSingleSeperator();
		System.out.printf("%" + 9 + "s%s\n", 
				" ", "INFIX TO POSTFIX PARSER AND EVALUATOR");
		printSingleSeperator();
		System.out.printf("%" + 15 + "s%s\n", 
				" ", "Step-By-Step Explanation");
		printDashSeperator();
		System.out.println("STEP 1: Read in infix expression from file.\n"
				+ "STEP 2: Parse the expression given in String format.\n"
				+ "STEP 3: Convert the formatted expression to a postfix \n"
				+ "        expression.\n"
				+ "STEP 4: Evaluate the postfix expression.\n"
				+ "STEP 5: Print the evaluated result.");
		printSingleSeperator();
		System.out.printf("%" + 10 + "s%s\n", 
				" ", "Scroll Down to View All Expressions");
		printSingleSeperator();
		System.out.println();
	}
	
	/** Prints a notice that we have reached the end of the expressions to
	 *  the console. 
	 */
	public static void printEndOfExpressions() {
		printSingleSeperator();
		System.out.printf("%" + 9 + "s%s\n", " ", 
				"Reached the End of Saved Expressions");
		printSingleSeperator();
	}
	
}
