package simplecalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Calculator {
	
	//Method overloading under this class --> One for programmed operation, another for fresh operations
	
	
	//Method 1 under Calculator class: performs arithmetic calculation for programmed operations
	public static double calculate(double baseOperand, ArrayList<String> progOperator, ArrayList<Double> progOperand) {
	
		double finalAnswer = baseOperand;
		DecimalFormat decForm = new DecimalFormat("0.#");
		System.out.print(finalAnswer);
		for (int i =0; i<progOperator.size(); i++) {
			System.out.print(" " + progOperator.get(i) + " " + decForm.format(progOperand.get(i+1)) + " ");
			switch (progOperator.get(i)) {
				case "+": 
					finalAnswer += progOperand.get(i+1);
					break;
				case "-": 
					finalAnswer -= progOperand.get(i+1); 
					break;
				case "*": 
					finalAnswer *= progOperand.get(i+1); 
					break;
				case "/": 
					finalAnswer /= progOperand.get(i+1); 		
					break;
				default:
					System.out.println("Invalid operator");
			}
		}
		return finalAnswer;

	}
	 
	
	//Method 2 under Calculator class: performs arithmetic calculation for fresh/new operations
	public static double calculate(double baseOperand, String operator, Double operand) {
		
		double finalAnswer = baseOperand;
		System.out.println(finalAnswer + operator + operand);
		switch (operator) {
			case "+": 
				finalAnswer += operand;
				break;
			case "-": 
				finalAnswer -= operand; 
				break;
			case "*": 
				finalAnswer *= operand; 
				break;
			case "/": 
				finalAnswer /= operand; 		
				break;
			default:
				System.out.println("Invalid operator");
		}
		return finalAnswer;
		
	}
	
	
}
