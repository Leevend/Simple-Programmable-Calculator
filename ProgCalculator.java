import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//V0.1 - April 25th 2018

//This source file covers the 'main' method and the 'file reading and writings methods to allow programmability of this calculator application.

public class ProgCalculator {
	
	
	//File reading method
	public static String fileReading() {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String filename = null;
		String line = null;
		String programmedCode = null;
		
		while(true) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println("Enter the filename containing the program:");
				filename = scan.next();
				fileReader = new FileReader(filename);
				bufferedReader = new BufferedReader(fileReader);
				if ((line = bufferedReader.readLine()) != null) {
					programmedCode = line;
				}
				bufferedReader.close();
				return programmedCode;
			} 
			catch (FileNotFoundException e1) {
				System.out.println(filename + " was not found. Please try again.");
			}
			catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	//File Writing Method
	private static void fileWriting(ArrayList<String> progOperator, ArrayList<Double> progOperand) {
		
		String fileName = null; 
		Scanner scanFileName;
		try {
			scanFileName = new Scanner(System.in);
			System.out.println("Please enter the desired filename of the program: ");
			fileName = scanFileName.nextLine();
			File program = new File(fileName);
			PrintWriter writer = new PrintWriter(program);
			
			writer.print(progOperand.get(0));
			for (int i=0; i<progOperator.size(); i++) {
				
				writer.print(" ");
				writer.print(progOperator.get(i));
				writer.print(" ");
				writer.print(progOperand.get(i+1));
			}
			writer.close();
			System.out.println(fileName + " was succesfully created, with program stored within.");
			scanFileName.close();
		}
		
		catch (FileNotFoundException errFileWriterFNF) {
			System.out.println("There is no " + fileName + " found. Please try again.");
		}
		

	}
	
	
	//Method that finds and stores all the operands in the program
	private static ArrayList<Double> operandFind(String program) {
		ArrayList<String> tempList = new ArrayList<String>();
		ArrayList<Double> operandList = new ArrayList<Double>();
		Pattern pattern = Pattern.compile("[-]?[0-9]*\\.?[0-9]+");
		Matcher match = pattern.matcher(program);
		while (match.find()) {
			tempList.add(match.group());
		}
		for (String a: tempList) {
			operandList.add(Double.parseDouble(a));
		}
		return operandList;
	}
	
	
	//Method that finds and stores all the operators in the program
	 private static ArrayList<String> operatorFind(String program)  {
		ArrayList<String> tempList = new ArrayList<String>();
		ArrayList<String> operatorList = new ArrayList<String>();
		Pattern pattern = Pattern.compile("\\s+[\\+\\-\\*\\/]+\\s+");
		Matcher match = pattern.matcher(program);
		while (match.find()) {
			tempList.add(match.group());
		}
		for (String a: tempList) {
			operatorList.add(a.replaceAll("\\s",""));
		}
		return operatorList;
	}
	
	 
	//Calculation method using programmed operation
	private static double calculator(double baseOperand, ArrayList<String> progOperator, ArrayList<Double> progOperand) {
		double finalAnswer = baseOperand;
		for (int i =0; i<progOperator.size(); i++) {
			System.out.println(finalAnswer + progOperator.get(i) + progOperand.get(i+1));
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
			}
		}
		return finalAnswer;
	}
	 
	
	//Calculation method using fresh operation
	private static double calculator(double baseOperand, String operator, Double operand) {
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
		}
		return finalAnswer;
	}
	
	//Main method
	public static void main (String[] args) {
		
		ArrayList<String> progOperator = new ArrayList();
		ArrayList<Double> progOperand = new ArrayList();
		double baseOperand;
		Scanner scanExProg;
		Scanner scanBaseOper;
		
		while(true) { //Looping to check for valid input for query on existing program
			scanExProg = new Scanner(System.in);
			System.out.println("Welcome to Simple Arithmetic Calculator v0.1. Do you have an existing program? Y/N ");
			if (scanExProg.hasNext("[yYnN]")) {
				break;
			}
			System.out.println("Invalid entry err1001. ");
		}
		if (scanExProg.hasNext("[yY]")) {
			
			Scanner scanRepeatProg;
			
			String program = fileReading();
			progOperator = operatorFind(program);
			progOperand = operandFind(program);
			
			System.out.printf("%.1f ", progOperand.get(0));
			for (int i=0; i< progOperator.size(); i++) {	
				
				System.out.printf("%s ", progOperator.get(i));
				System.out.printf("%.1f ", progOperand.get(i+1));
			}
				
			while(true) {  //Looping to allow user to try with other base operands on programmed operation.
				
				
				baseOperandYes: while(true) {  //Looping to check for valid response in an integer/double as a base operand.
					scanBaseOper = new Scanner(System.in);
					System.out.println("\nPlease enter a new base operand to try with this programmed operation: ");
					try { baseOperand = scanBaseOper.nextDouble(); }
					catch (InputMismatchException errbaseOperYesIM) { continue baseOperandYes; }
					
					/*Pattern pBaseOperand = Pattern.compile("[-]?[0-9]*\\.?[0-9]+");
					Matcher mBaseOperand = pBaseOperand.matcher(Double.toString(baseOperand));
					if (mBaseOperand.find()) {
						break;
					}
					System.out.println("Invalid entry. ");
					*/
					break;
				}
				
				
				double finalAnswer = calculator(baseOperand, progOperator, progOperand);
				System.out.printf("The final answer is %.2f.\n", finalAnswer);
				
				
				while(true) {  //Looping to check for valid response if another base operand wants to be tried on.
					System.out.println("Would you like to try the same program with another base operand? Y/N ");
					scanRepeatProg = new Scanner(System.in);
					if(scanRepeatProg.hasNext("[nNyY]")) {
						break;
					}
					System.out.println("Invalid entry err1002. ");
				}
				if(scanRepeatProg.hasNext("[nN]")) {
					break;
				}
			}
			scanBaseOper.close();
			scanRepeatProg.close();
		}

		if (scanExProg.hasNext("[nN]")) {
			
			String operator;
			double operand;
			Scanner scanNextOperator;
			Scanner scanNextOperand;
			Scanner scanContinueOp;
			Scanner scanNewOperand;
			Scanner scanSaveProgram;

			while(true) { 
		
				baseOperandNo: while(true) {   //Looping to check for valid response in an integer/double as base operand
					scanBaseOper = new Scanner(System.in);
					System.out.println("Please enter an integer or double as the base operand: ");
					try { 
						baseOperand = scanBaseOper.nextDouble();
					}
					catch (InputMismatchException errBaseOperNoIM) {
						continue baseOperandNo;
					}
					/*Pattern pBaseOperand = Pattern.compile("[-]?[0-9]*\\.?[0-9]+");
					Matcher mBaseOperand = pBaseOperand.matcher(Double.toString(baseOperand));
					if (mBaseOperand.find()) {
						break;
					}
					System.out.println("Invalid entry. ");
					*/
					break;
				}					
				progOperand.add(baseOperand);
				System.out.println("You have entered " + baseOperand + " as the base operand.");
				
				
				while(true) {  //Looping to allow continuous operation on the same base operand.
					
					
					while(true) {  //Looping to check for correct next operator entry.
						scanNextOperator = new Scanner(System.in);
						System.out.println("Please choose your desired operator from the enclosed choices on the right (+,-,*,/): ");
						operator = scanNextOperator.next();
						Pattern pNextOperator = Pattern.compile("([^0-9]*[\\+\\-\\*\\/]+)");
						Matcher mNextOperator = pNextOperator.matcher(operator);
						if (mNextOperator.find()) {	
							break;
						}
						System.out.println("Invalid entry err2001. ");
					}
					progOperator.add(operator);
					System.out.println("You have entered " + operator + " as the next operator.");
					
			
					operand: while(true) {  //Looping to check for correct next operand entry.
						scanNextOperand = new Scanner(System.in);
						System.out.println("Please enter an integer or double as the right side operand: ");
						try {
							operand = scanNextOperand.nextDouble(); 
						}
						catch (InputMismatchException errNextOperandIM) {
							continue operand;
						}
						/* Pattern pNextOperand = Pattern.compile("[-]?[0-9]*\\.?[0-9]+");
						Matcher mNextOperand = pNextOperand.matcher(Double.toString(operand));
						System.out.println(Double.toString(operand));
						if (mNextOperand.find()) {
							break;
						}
						System.out.println("Invalid entry. ");
						*/
						break;
					}
					progOperand.add(operand);
					System.out.println("You have entered " + operand + " as the next operand.");
					
					
					//Sends the base operand, operator and right hand operand for calculation
					double finalAnswer = calculator(baseOperand, operator, operand);
					System.out.printf("The final answer is %.2f.", finalAnswer);
					baseOperand = finalAnswer;		//Converts base operand to left side operand for further operation if requested.
			
					
					while(true) {  //Looping to check for valid response if further operation is needed.
						scanContinueOp = new Scanner(System.in);
						System.out.println("Would you like to operate further? Y/N ");
						if(scanContinueOp.hasNext("[nNyY]")) {
							break;
						}
						System.out.println("Invalid entry err2002. ");
					}
					if(scanContinueOp.hasNext("[nN]")) {  //If no, then no further operation is performed; returning to outermost while loop.
						break;
					}

				}
				
				
				while(true) {  //Looping to check for valid response if new operation is desired.
					scanNewOperand = new Scanner(System.in);
					System.out.println("Would you like to try a new operation from scratch? Y/N ");
					if(scanNewOperand.hasNext("[nNyY]")) {
						break;
					}
					System.out.println("Invalid entry 2003. ");
				}
				if (scanNewOperand.hasNext("[nN]")) {
					break;
				}
				progOperand.clear();
				progOperator.clear();
			}
				
			
			while(true) {
				scanSaveProgram = new Scanner(System.in);
				System.out.println("Would you like to save this operation as a program? Y/N ");
				if(scanSaveProgram.hasNext("[yYnN]")) {
					break;
				}
				System.out.println("Invalid entry 2004. ");
			}
			if (scanSaveProgram.hasNext("[yY]")) {
				fileWriting(progOperator, progOperand);
			}
			scanBaseOper.close();
			scanNextOperator.close();
			scanNextOperand.close();
			scanContinueOp.close();
			scanNewOperand.close();
			scanSaveProgram.close();
		}
		scanExProg.close();	
		System.out.println("Thank you for trying Simple Arithmetic Calculator v0.1!");
	}
}
