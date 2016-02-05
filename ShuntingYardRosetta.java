package mini_matematica;

import java.util.Stack;

public class ShuntingYardRosetta {
	

	public static final double pi=3.14;
	public static final double e=2.71;

	static String infixToPostfix(String infix) {
		final String operations = "-+/*^"; 
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();

		String[] tokens = infix.split("\\s");
		for (String token : tokens) {
			char c = token.charAt(0);
			int idx = operations.indexOf(c);
			// check for operator
			if (idx != -1) {
				if (stack.isEmpty())
					stack.push(idx);

				else {
					while (!stack.isEmpty()) {
						int precedenceSecond = stack.peek() / 2;
						int precedenceFirst = idx / 2;
						if (precedenceSecond > precedenceFirst
								|| (precedenceSecond == precedenceFirst && c != '^'))
							sb.append(operations.charAt(stack.pop())).append(' ');
						else
							break;
					}
					stack.push(idx);
				}
			} else if (c == '(') {
				stack.push(-2); // -2 stands for '('
			} else if (c == ')') {
				// until '(' on stack, pop operators.
				while (stack.peek() != -2)
					sb.append(operations.charAt(stack.pop())).append(' ');
				stack.pop();
			} else if (isNumeric(token)) {
				
				sb.append(token).append(' ');

			}else{
				sb.append(doAdvancedCalc(token)).append(' ');
			}
		}
		while (!stack.isEmpty()) {
			sb.append(operations.charAt(stack.pop())).append(' ');
		}

		return sb.toString();
		
	}
	
	
	public static double doAdvancedCalc(String expr){
		if(expr.startsWith("log")){
			return log(expr);
		}else if(expr.startsWith("pow")){
			return pow(expr);
		}else if(expr.startsWith("sin")){
			return sin(expr);
		}else if(expr.startsWith("cos")){
			return cos(expr);
		}else if(expr.startsWith("tg")){
			return tg(expr);
		}else if (expr.startsWith("cotg")){
			return cotg(expr);
		}else if (expr.startsWith("sqrt")){
			return sqrt(expr);
		}
		return 0;
	}
	
	private static double pow(String expr){
		String temp = expr.substring(4,expr.length()-1).replaceFirst(",", "!");
		String [] operands = temp.split("!");
		double firstOp=assignValuesToOperands(operands[0]);
		double secondOp=assignValuesToOperands(operands[1]);
		return Math.pow(firstOp, secondOp);
	}
	
	private static double log(String expr){
		String temp = expr.substring(4,expr.length()-1).replaceFirst(",", "!");
		String [] operands = temp.split("!");
		double secondOp=assignValuesToOperands(operands[1]);
		return Math.log(secondOp);
	}
	
	private static double sin(String expr){
		String temp = expr.substring(4,expr.length()-1);
		double operand = assignValuesToOperands(temp);
			return Math.sin(operand);
	}
	
	private static double cos(String expr){
		String temp = expr.substring(4,expr.length()-1);
		double operand = assignValuesToOperands(temp);
			return Math.cos(operand);
	}
	
	private static double tg(String expr){
		String temp = expr.substring(3,expr.length()-1);
		double operand = assignValuesToOperands(temp);
		return Math.tan(operand);
	}
	
	private static double cotg(String expr){
		String temp = expr.substring(5,expr.length()-1);
		double operand = assignValuesToOperands(temp);
		return 1/Math.tan(operand);
	}
	

	
	private static double sqrt(String expr){
		String temp = expr.substring(5,expr.length()-1);
		double operand = assignValuesToOperands(temp);
		return Math.pow(operand, 0.5);
	}
	
	private static double assignValuesToOperands(String op){
		if(isNumeric(op)){
			return Double.parseDouble(op);
		}else if(op.equals("e")){
				return e;
		}else if(op.equals("pi")){
				return pi;
		}else{
			return doAdvancedCalc(op);
		}
	}
	
	private static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");
	}
	

}
