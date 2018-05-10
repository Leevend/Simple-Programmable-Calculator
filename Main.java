package simpleCalculator;

//V0.1 - April 25th 2018

//This source file covers the 'main' method of this programmable calculator application.

public class Main {
	
	
	public static void main (String[] args) {
		
			
		System.out.println("Welcome to Simple Arithmetic Calculator v0.1. Do you have an existing program? Y/N ");
		ScanInput scanInput = new ScanInput();
		
		// If there is an existing program to import from
		if (scanInput.scanBoolInput()) {
			
			ExistingProgram existingProgram = new ExistingProgram();
			existingProgram.readProgram();
			existingProgram.displayProgrammedOperation();
			existingProgram.newBaseOperandCalculate();
			
		}

		
		// If there is no existing program, hence a fresh program
		else {
			
			NewProgram newProgram = new NewProgram();
			while(true) {
				newProgram.baseOperand();
				newProgram.operationCreation();
				newProgram.operationCalculation();
				newProgram.askFurtherOperation();
				if(!newProgram.askNewOperation()) {
					break;
				}
			}
			newProgram.saveProgram();
			
		}
		System.out.println("Thank you for trying Simple Arithmetic Calculator v0.1!");
	}
}
