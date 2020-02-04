package com.ss.crudProject.read;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AuthorServices extends UtilityClass implements Menu  {
	
	Main mainMenu = new Main();
	static String uri = "Resources/inputs/author.txt";
	static File file = new File(uri);
	static HashMap<String, String> map = new HashMap<>();
	

//	public static void main(String[] args) {
//		AuthorServices m = new AuthorServices();
////		//m.displayMenu();
//		m.deleteAuthor(3);
////		System.out.println(m.createHashMap(file));
////		
////		// TODO Auto-generated method stub
////
//	}
	
	
	

	@Override
	public void displayMenu() {
	
		int action;
		//HashMap<String, String> pam = 
		//createHashMap(file);
		Scanner userSelection = new Scanner(System.in);
		
		System.out.println("1. Add Author");
        System.out.println("2. Edit Author");
        System.out.println("3. Delete Author");
        System.out.println("4. View All Authors");
        System.out.println("5. Return to Main Menu");
        
        action = read_range(userSelection, 1,5);
        
        if (action == 1) {
        	
        	Scanner n = new Scanner(System.in);
        	
        	System.out.println("If the Author is on this list, please enter the authors key\n" +"Enter '0' if your author is not shown \n" + authorOptions());
    		
        	String userInput = n.nextLine();
    		Integer input = Integer.parseInt(userInput);
        	
    		if (input == 0) {
    			
    			System.out.println("Enter Author's First Name");
            	String firstName = n.nextLine();
            	
            	System.out.println("Enter Author's Last Name");
            	String lastName = n.nextLine();
            	
            	addAuthor(firstName, lastName);
//            	System.out.println("\n");
//            	displayMenu();
    			
    		}
    		else {
    			input = read_range(n, 1, mapIterator(map));
    			System.out.println("Thanks Author Already Exists");
    		}
        	
        	
        	
        	n.close();
        	
        }
        
        if (action == 2) {
        	
        	System.out.print("Functionality not ready yet");
        	displayMenu();
        	
//        	Scanner n = new Scanner(System.in);
//        	System.out.println("Enter the key of  the author you want to edit\n"+ "Enter '0' to return to previous menu"+ authorOptions());
//        	
//        	String userInput = n.nextLine();
//    		Integer input = Integer.parseInt(userInput);
//    		
//    		if (input == 0) {
//    			displayMenu();
//    			
//    		}
//    		
//    		else {
//    			
//    			input = read_range(n, 1, mapIterator(map));
//    			
//    			String firstName = getUpdatedFirstName();
//            	
//            	String lastName = getUpdatedLastName();
//            	
//            	editAuthor(firstName, lastName, input);
//    		}
        	
        	
        }
        
        if (action == 3) {
        	System.out.print("Functionality not ready yet");
        	displayMenu();
//        	Scanner n = new Scanner(System.in);
//        	System.out.println("Enter the key of  the author you want to delete\n"+ "Enter '0' to return to previous menu"+ authorOptions());
//        	
//        	String userInput = n.nextLine();
//    		Integer input = Integer.parseInt(userInput);
//    		
//    		if (input == 0) {
//    			displayMenu();
//    			
//    		}
//    		
//    		else {
//    			
//    			input = read_range(n, 1, mapIterator(map));
//    			try {
//					deleteAuthor(getLineToDelete(input));
//				} catch (IOException e) {
//					System.out.println("\n");
//					System.out.println("Something Went Wrong");
//					displayMenu();
//				}
//    		}
        }
        
        if (action == 4) {
        	viewAllAuthors();
        }
        
        if (action == 5) {
        	
//        	Main mainMenu = new Main(); // Should i leave this here or instantiate at the top.
        	mainMenu.displayMenu();
        }
	}
	
	public String getUpdatedFirstName() {
		Scanner n = new Scanner(System.in);
		System.out.print("Enter Author's First Name");
    	String firstName = n.nextLine();
    	n.close();
    	return firstName;
	}
	
	public String getUpdatedLastName() {
		Scanner n = new Scanner(System.in);
		System.out.print("Enter Author's First Name");
    	String lastName = n.nextLine();
    	n.close();
    	return lastName;
	}
	
	public String authorOptions() {
		createHashMap(file);
		StringBuilder strBuilder = new StringBuilder();
		String key = null;
		String value = null;
		String finalString = null;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			
			key = entry.getKey();
			value = entry.getValue();
			
			
		finalString = strBuilder.append(key+" "+value+"\n").toString();
		}
		return finalString;
	}
	
public int deleteMapIterator(HashMap<String, String> map) {
		
		int count = 0;
		//map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
		for (String item : map.keySet()) {
			
			count += 1;	
		}
		return count;
		
}


public int mapIterator(HashMap<String, String> map) {
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		//map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
		for (String item : map.keySet()) {
			
			Integer key = Integer.parseInt(item);
			list.add(key);
			
		}
		Collections.sort(list);
		return list.get(list.size()-1);
		
}

public HashMap<String, String> createHashMap(File file) {
		
		try {
			Scanner scan = new Scanner(file);
			
			while(scan.hasNextLine()) {
				
				String data = scan.nextLine();
				String[] strs = data.split("\\s+");
				StringBuilder strBuilder = new StringBuilder();
				
				for (int i = 1; i<strs.length; i++) {
				
					 strBuilder = strBuilder.append(" "+strs[i]);							// consider if you had a third part of the publishers name.
			}
				
				map.put(strs[0], strBuilder.toString()); // consider if you had a third part of the publishers name.
				
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	public void addAuthor(String firstName, String lastName) {
		
		//createHashMap(file);
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(uri), true))) {
			
			
			String data =sortId(createHashMap(file))+" "+firstName+" "+lastName;
			
			writer.write(data);
			writer.newLine();
			
			System.out.println(firstName+" "+lastName+" has been added");
			
			
		} catch(IOException e) {
			
			System.out.println("Failed to find author.txt");
			displayMenu();
			
		}
				
	}
	
	public void viewAllAuthors() {
		
	try(BufferedReader bufread = new BufferedReader(new FileReader(uri))){
			
		String line = bufread.readLine();
			
		while (line != null) {
			
			System.out.println(line);
			
			line = bufread.readLine();		
		}
		System.out.println("Enter '0' to return to Main Menu");
		Scanner n = new Scanner(System.in);
		String option = n.nextLine();
		Integer intoption = Integer.parseInt(option);
		
		if (intoption == 0) {
			mainMenu.displayMenu();
		}
		else if (intoption > 0) {
			intoption = read_rangee(n, 0);
			mainMenu.displayMenu();
			}
		
	} 
	catch (IOException e) {
			
		System.out.println("File not Found");
			
	  }
	}
	

	public void deleteAuthor(String str) throws IOException {
		
		File inputFile = new File("Resources/inputs/author.txt");
		//System.out.print(inputFile);
		File tempFile = new File("myTempFile.txt");
		 
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		 
		String lineToRemove = str;
		String currentLine;
		 
		while((currentLine = reader.readLine()) != null) {
		 
		// trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		   
		    if(trimmedLine.equals(lineToRemove)) continue;
		 
		    writer.write(currentLine + System.getProperty("line.separator"));
		    
		}
		writer.flush();
		 
		writer.close(); 
		reader.close(); 
		 
		tempFile.renameTo(inputFile);
	}

	public void editAuthor(String fn, String ln, Integer input) {
		
		
		    try {
		        // input the (modified) file content to the StringBuffer "input"
		        BufferedReader infile = new BufferedReader(new FileReader(uri));
		        StringBuffer inBuffer = new StringBuffer();
		        String line;

		        while ((line = infile.readLine()) != null) {
		            line = input+" "+fn+" "+ln;
		            inBuffer.append(line);
		            inBuffer.append('\n');
		        }
		        
		        while ((line = infile.readLine()) == getLineToDelete(input)) {
		        	line = input+" "+fn+" "+ln;
		            inBuffer.append(line);
		            inBuffer.append('\n');
		        }
		        infile.close();

		        // write the new string with the replaced line OVER the same file
		        FileOutputStream fileOut = new FileOutputStream(uri);
		        fileOut.write(inBuffer.toString().getBytes());
		        fileOut.close();

		    } catch (Exception e) {
		        System.out.println("Error reading file.");
		    }
		}
	}

//public void deleteFunction() {
//BookServices bs = new BookServices();
//Scanner scan;
//try {
//	scan = new Scanner(bs.file);
//} catch (FileNotFoundException e1) {
//	// TODO Auto-generated catch block
//	e1.printStackTrace();
//}
//String fileName = "books.txt";
//String lineToRemove = scan.findInLine(Pattern.compile("........"+getAuthorId()));	
//try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
//	stream.filter(line->!line.trim().equals(lineToRemove)).forEach(System.out::println);
//} catch (IOException e) {
//	e.printStackTrace();
//}
//}

//public void deleteFunction() {
//	
//File tempFile = new File("Resources/inputs/myTempFile.txt");
//
//BookServices bs = new BookServices();
// 
//BufferedReader reader = new BufferedReader(new FileReader(bs.file));
//BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
//
//String ltr = null;
//
//try {
//	Scanner scan = new Scanner(bs.file);
//	
//	while(scan.hasNextLine()) {
//
//		
//		ltr = scan.findInLine(Pattern.compile("........"+getAuthorId()+"...."));
//		
//		
//	}
//} catch (FileNotFoundException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
// 
//String lineToRemove = ltr;
//String currentLine;
// 
//while((currentLine = reader.readLine()) != null) {
// 
//// trim newline when comparing with lineToRemove
//    String trimmedLine = currentLine.trim();
//   
//    if(trimmedLine.equals(lineToRemove)) continue;
// 
//    writer.write(currentLine + System.getProperty("line.separator"));
//}
// 
//writer.close(); 
//reader.close(); 
// 
//boolean successful = tempFile.renameTo(bs.file);			
//}





	
