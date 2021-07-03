package project_2;

public class InfixExpressionParser {
	// TODO: Method to check if token is operator
	
	// TODO: Method to check if character is part of operator
	private static boolean isPartOfOperator(char c) {
		return c == '^' || c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || 
			   c == '<' || c == '>' || c == '=' || c == '!' || c == '&' || c == '|';
	}
	
	// TODO: Format infix expression method
	static String format(String exp) {
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
			}
		}
		return formattedExp.toString();
	}
	// TODO: Precedence method
	// TODO: Convert infix to postfix method
	// TODO: Evaluate postfix method
	// TODO: main method that reads input from file and outputs to console
}
