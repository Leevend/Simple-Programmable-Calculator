package simplecalculator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This class contains all the input scanning methods necessary for running the calculator
public class ScanInput {
	
	
	//Method 1 under ScanInput class: Scans input for a base operand
	public double scanOperand() {
		
		double Operand = 0;
		
		Scanner scan = new Scanner(System.in);
		try { 
			Operand = scan.nextDouble(); 
		}
		catch (InputMismatchException errbaseOperandIM) {
			System.out.println("Invalid. Please input an integer or double for the operand: ");
		}
		return Operand;
		
	}
	
	
	//Method 2 under ScanInput class: requests and scans input if another base operand would like to be fed 
	public boolean scanBoolInput() {
			
		Scanner scan = new Scanner(System.in);
		while(true) {
			if (scan.hasNext("[yYnN]")) {
				break;
			}
			System.out.println("Invalid entry. Please input Y/N: ");
		}

		boolean answer = scan.hasNext("[Yy]");
		return answer;
		
		}

	
	//Method 3 under ScanInput class: requests and scans input for a desired filename (if writing a file) or a target filename (if reading a file)
	public String scanFileName() {
		
		String filename = null;
		
		System.out.println("Enter the filename: ");
		Scanner scan = new Scanner(System.in);
		filename = scan.next();
		return filename;
		
	}
	
	
	//Method 4 under ScanInput class: requests and scans input for a desired operator
	public String scanOperator() {
		
		String operator = null;
		
		while(true) {  
			
			Scanner scan = new Scanner(System.in);
			operator = scan.next();
			Pattern pNextOperator = Pattern.compile("([^0-9]*[\\+\\-\\*\\/]+)");
			Matcher mNextOperator = pNextOperator.matcher(operator);
			if (mNextOperator.find()) {	
				return operator;
			}
			System.out.println("Invalid entry err2001. ");
		}
		
	}
	
	
}
