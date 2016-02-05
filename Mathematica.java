package mini_matematica;

import java.util.Scanner;

public class Mathematica {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String expr = ShuntingYard.infixToPostfix(scanner.nextLine());
		System.out.println("Shunting Yard Rosetta: " + expr);
		RPN.evalRPN(expr);
		scanner.close();
	}
}