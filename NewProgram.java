package simplecalculator;

import java.util.ArrayList;

public class NewProgram {

	private String operator;
	private double operand, baseOperand;
	private ScanInput scanInput;
	private ArrayList<String> progOperator = new ArrayList<String>();
	private ArrayList<Double> progOperand = new ArrayList<Double>();

	
	//Constructor with creation of scanNewProgram object of class ScanInput
	public NewProgram() {
		this.scanInput = new ScanInput();
	}
		
	
	//Method 1 under NewProgram class: requests and scans for base operand
	public void baseOperand() {
		
		System.out.println("Please enter an integer or double as the base operand: ");
		this.baseOperand = this.scanInput.scanOperand();
		System.out.println("You have entered " + this.baseOperand + " as the base operand.");
		progOperand.add(this.baseOperand);
		
	}
	
	
	//Method 2 under NewProgram class: requests and scans for operator and right side operand
	public void operationCreation() {
			
		//Requests and scans for operator
		System.out.println("Please choose your desired operator from the enclosed choices on the right (+,-,*,/): ");
		this.operator = this.scanInput.scanOperator();
		progOperator.add(this.operator);
		System.out.println("You have entered " + operator + " as the next operator.");
				
		//Request and scans for right side operand
		System.out.println("Please enter an integer or double as the right side operand: ");
		this.operand = this.scanInput.scanOperand();
		progOperand.add(this.operand);
		System.out.println("You have entered " + operand + " as the next operand.");
	
	}					

	
	//Method 3 under NewProgram class: performs the calculation on the operation, and outputs the resulting answer
	public void operationCalculation() {

		double finalAnswer;
		
		finalAnswer = Calculator.calculate(this.baseOperand, this.operator, this.operand);
		System.out.printf("The final answer is %.2f.", finalAnswer);
		this.baseOperand = finalAnswer;		//Converts the final answer to the left side operand for further operations if requested.
		
	}
		

	//Method 4 under NewProgram class: requests and scans input if further operation is required
	public void askFurtherOperation() {
		
		System.out.println("Would you like to operate further? Y/N ");
		if (this.scanInput.scanBoolInput()) { 
			operationCreation();
			operationCalculation();
			askFurtherOperation();
		}
		
	}
	

	//Method 5 under NewProgram class: requests and scans input if a new operation is required
	public boolean askNewOperation() {
		
		System.out.println("Would you like to try a new operation from scratch? Y/N ");
		return this.scanInput.scanBoolInput();
		
	}
	

	//Method 6 underNewProgram class: requests and scans input if the operation should be programmed into a file
	public void saveProgram() {
		
		System.out.println("Would you like to save this operation as a program? Y/N ");
		if(this.scanInput.scanBoolInput()) {
			FileModding saveOperation = new FileModding();
			saveOperation.fileWriting(this.progOperator, this.progOperand);
		}
		progOperand.clear();
		progOperator.clear();

	}

	
}

