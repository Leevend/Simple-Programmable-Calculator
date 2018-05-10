package simpleCalculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileModding {
	private String fileName = null;
	ScanInput scanInput = new ScanInput();
	
	
	// Method 1 under FileAccess class: Reads the programmed operation within a target file
	public String fileReading() {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String programmedCode = null;

		while(true) {
			try {
				fileName = scanInput.scanFileName();
				fileReader = new FileReader(fileName);
				bufferedReader = new BufferedReader(fileReader);
				programmedCode = bufferedReader.readLine();
				bufferedReader.close();
				return programmedCode;
			} 
			catch (FileNotFoundException errFileReadFNF) {
				System.out.println(fileName + " was not found. Please try again.");
			}
			catch (IOException errFileReadIO) {
				errFileReadIO.printStackTrace();
			}
		}
		
	}
	
	
	//Method 2 under FileAccess class: Writes/programs the operation into a desired file
	public void fileWriting(ArrayList<String> progOperator, ArrayList<Double> progOperand) {
		
			try {
				fileName = scanInput.scanFileName();
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
			}
			
			catch (FileNotFoundException errFileWriteFNF) {
				System.out.println("There is no " + fileName + " found. Please try again.");
				fileWriting(progOperator, progOperand);
			}
			
	}
	
	
	//Method 3 under FileAccess class: finds all the operands stored in a programmed operation and stores temporarily for calculator use
	public ArrayList<Double> operandFind(String program) {
	
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
	
	
	//Method 4 under FileAccess class: finds all the operators stored in a programmed operation and stores temporarily for calculator use
	 public ArrayList<String> operatorFind(String program)  {
	
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
	 
}
