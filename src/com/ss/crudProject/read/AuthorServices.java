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
import java.util.HashSet;
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
//		//AuthorServices m = new AuthorServices();
//		//m.displayMenu();
//		UtilityClass.getLineToEdit(4);
//		//m.deleteMapIterator(map);
////		System.out.println(m.createHashMap(file));
////		
////		// TODO Auto-generated method stub
////
//	}
	
	
	@Override
	public void displayMenu() {
		
		int action;
		//HashMap<String, String> pam = createHashMap(file);
	
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
    			
    		}
    		else {
    			input = read_range(n, 1, mapIterator(map));
    			System.out.println("Thanks Author Already Exists");
    		}
        	
        	
    		System.out.println("Enter '0' to return to Main Menu");
    		n = new Scanner(System.in);
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
        
        if (action == 2) {
        	
        	
        	
        	System.out.println("Enter the key of  the author you want to edit\n"+ "Enter '0' to return to previous menu\n"+ authorOptions());
        	Scanner n = new Scanner(System.in);
        	String userInput = n.nextLine();
    		Integer input = Integer.parseInt(userInput);
    		
    		if (input == 0) {
    			displayMenu();
    			
    		}
    		
    		else {
    			
    			input = read_range(n, 1, mapIterator(map));
            	
    			String fullName = getFullName();
            	
            	Integer.toString(input);
            	String edit = fullName;
            	HashMap<String, String> map = getAuthorIdForEdit(edit, Integer.toString(input));
            	
            	
            	try {
					editAuthor(map);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Something Went Wrong");
				}
            	
            	
            	
            	
    		}
    		
        	
        }
        
        if (action == 3) {
        	
        	System.out.println("Enter the key of the author you wish to delete\n" + authorOptions());
        	Scanner n = new Scanner(System.in);
        	String deleteKey = n.nextLine(); 
        	Integer keyValue = Integer.parseInt(deleteKey);
        	
        	
        	
        	try {
        		keyValue = read_range(n, 1, mapIterator(map));
        		ArrayList<String > key = UtilityClass.getAuthorId(deleteKey);
        		
				deleteAuthor(key);
				deleteBookAuthor(deleteKey);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("<<<File not found>>>");
			}
        	n.close();
        	//mainMenu.displayMenu();
        }
        
        if (action == 4) {
        	viewAllAuthors();
        }
        
        if (action == 5) {
        	
//        	Main mainMenu = new Main(); // Should i leave this here or instantiate at the top.
        	mainMenu.displayMenu();
        }
        //displayMenu();
	}
	
	public String getFullName() {
		Scanner n = new Scanner(System.in);
		String fullName;
		System.out.println("Enter Author's Full Name");
		Integer name = n.nextInt();
		n.nextLine();
		
    	 fullName = Integer.toString(name);
		//String fullName = Integer.toString(num);
    	return fullName;
	}
	
//	public String getUpdatedFirstName() {
//		Scanner n = new Scanner(System.in);
//		System.out.print("Enter Author's First Name");
//    	String firstName = n.nextLine();
//    	n.close();
//    	
//    	return firstName;
//    	
//	}
//	
//	public String getUpdatedLastName() {
//		Scanner n = new Scanner(System.in);
//		System.out.print("Enter Author's First Name");
//    	String lastName = n.nextLine();
//    	n.close();
//    	return lastName;
//	}
	
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
			
			System.out.println("\n"+firstName+" "+lastName+" has been added");
			
			
			
			
		} catch(IOException e) {
			
			System.out.println("Failed to find author.txt");
			displayMenu();
			
		}
		
		System.out.println("\n");
				
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
	

	public void deleteAuthor(ArrayList<String> id) throws IOException {
				BookServices bs = new BookServices();
				for (String item : id) {
					
					bs.deleteBook(item);
	}
	}
				
	public void deleteBookAuthor(String id) throws IOException {
			HashMap<String, String> map = createHashMap(file);
					map.remove(id);
					
				    
		 File file = new File(uri);
			        BufferedWriter bufreader = null;;
			        
			        try{
			            
			            //create new BufferedWriter for the output file
			            bufreader = new BufferedWriter( new FileWriter(file) );
			 
			            //iterate map entries
			            for(Map.Entry<String, String> entry : map.entrySet()){
			            	
			                bufreader.write(entry.getKey() + " " + entry.getValue());
			                
			                //new line
			                bufreader.newLine();
			            }
			            
			            bufreader.flush();
			 
			        } catch(IOException e){
			        	
			            System.out.println("File not found");
			            
			        } finally{
			        	
			                bufreader.close();
			                
			                System.out.println("\n");
			            	//displayMenu();
			            
			        }
			    
	}	
				
					
				    
	public void editAuthor(HashMap<String, String> map) throws IOException {
		
		 File file = new File(uri);
	        BufferedWriter bufreader = null;;
	        
	        try{
	            
	            //create new BufferedWriter for the output file
	            bufreader = new BufferedWriter( new FileWriter(file) );
	 
	            //iterate map entries
	            for(Map.Entry<String, String> entry : map.entrySet()){
	            	
	                bufreader.write(entry.getKey() + " " + entry.getValue());
	                
	                //new line
	                bufreader.newLine();
	            }
	            
	            bufreader.flush();
	            System.out.println("\n Author has been edited");
	            
	            
	 
	        } catch(IOException e){
	        	
	            System.out.println("File not found");
	            
	        } finally{
	        	
	                bufreader.close();
	            displayMenu();
	        }
		
	}				
				
		
 
}

	
	






	
