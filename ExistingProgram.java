package simpleCalculator;

import java.util.ArrayList;

public class ExistingProgram {
	
	private double baseOperand;
	private double finalAnswer;
	private ArrayList<String> progOperator = new ArrayList<String>();
	private ArrayList<Double> progOperand = new ArrayList<Double>();
	private String program;
	private ScanInput scanInput;
	
	
	//Constructor with creation of scanProgramExist object of class ScanInput
	public ExistingProgram() {
		this.scanInput = new ScanInput();
	}
	

	//Method 1 under ExistingProgram class: reads target file
	public void readProgram() {
		
		FileModding fileMod = new FileModding();
		this.program = fileMod.fileReading();
		this.progOperator = fileMod.operatorFind(program);
		this.progOperand = fileMod.operandFind(program);
		
	}
	
	
	//Method 2 under ExistingProgram class: displays the programmed operation within the target file
	public void displayProgrammedOperation() {
		
		System.out.printf("%.1f ", this.progOperand.get(0));
		for (int i=0; i< this.progOperator.size(); i++) {	
			
			System.out.printf("%s ", this.progOperator.get(i));
			System.out.printf("%.1f ", this.progOperand.get(i+1));
		}
	}
	

	//Method 3 under ExistingProgram class: Requests for new base operand(s) to try with existing program
	public void newBaseOperandCalculate() {
		
		while(true) {  //Looping to allow user to try with other base operands on programmed operation
			System.out.println("\n What new base operand would you like to try? ");
			this.baseOperand = this.scanInput.scanOperand();
			this.finalAnswer = Calculator.calculate(this.baseOperand, this.progOperator, this.progOperand);
			System.out.printf("\nThe final answer is %.2f.\n", this.finalAnswer);
			System.out.print("Would you like to try the same program with another base operand? Y/N ");
			if(this.scanInput.scanBoolInput()) {
				continue;
			}
			break;	
		}
		
	}
	
}
