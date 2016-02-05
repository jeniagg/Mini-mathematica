package mini_matematica;

import java.util.Stack;

public class RPN {
	public static void evalRPN(String expr) {
		Stack<Double> stack = new Stack<Double>();
		System.out.println("Input\tOperation\tStack after");
		String[] tokens = expr.split("\\s");
		for (String token : tokens) {
			System.out.print(token + "\t");
			Double tokenNum = null;
			try {
				tokenNum = Double.parseDouble(token);
			} catch (NumberFormatException e) {
			}
			if (tokenNum != null) {
				System.out.print("Push\t\t");
				stack.push(tokenNum);
			} else if (token.equals("*")) {
				System.out.print("Operate\t\t");
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				stack.push(firstOperand * secondOperand);

			} else if (token.equals("/")) {
				System.out.print("Operate\t\t");
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				stack.push(firstOperand / secondOperand);

			} else if (token.equals("-")) {
				System.out.print("Operate\t\t");
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				stack.push(firstOperand - secondOperand);

			} else if (token.equals("+")) {
				System.out.print("Operate\t\t");
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				stack.push(firstOperand + secondOperand);

			} else if (token.equals("^")) {
				System.out.print("Operate\t\t");
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				stack.push(Math.pow(firstOperand, secondOperand));

			} else {// just in case
				System.out.println("Error");
				return;
			}
			System.out.println(stack);
		}
		System.out.println("Final answer: " + stack.pop());
	}

}

