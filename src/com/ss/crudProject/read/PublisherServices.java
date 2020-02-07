package com.ss.crudProject.read;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class PublisherServices extends UtilityClass implements Menu {
	
	Main mainMenu = new Main();
	static String uri = "Resources/inputs/publisher.txt";
	static File file = new File(uri);
	static HashMap<String, String> map = new HashMap<>();
	
	
//	public static void main(String[] args) throws IOException {
//		PublisherServices ps = new PublisherServices();
////		ps.displayMenu();
//		//ps.createHashMap(file);
//		ps.deletePublisher("9");
//	}

	@Override
	public void displayMenu() {
		
		int action;
		
		//HashMap<String, String> map = createHashMap(file);
		
		Scanner userSelection = new Scanner(System.in);
		
		System.out.println("1. Add Publisher");
        System.out.println("2. Edit Publisher");
        System.out.println("3. Delete Publisher");
        System.out.println("4. View All Publisher");
        System.out.println("5. Return to Main Menu");
        
        
        action = read_range(userSelection, 1,5);
        
        if (action == 1) {
        	
        	Scanner n = new Scanner(System.in);
        	
        	System.out.println("If the Publisher is on this list, please enter the publisher's key\n" +"Enter '0' if your author is not shown\n" + publisherOptions());
    		
        	String userInput = n.nextLine();
    		Integer input = Integer.parseInt(userInput);
        	
    		if (input == 0) {
    			
    			System.out.println("Enter Publishers Name");
            	String pubName = n.nextLine();
            	
            	addPublisher(pubName);
    			
    		}
    		else {
    			// remember you moved the mapiterator function to utility class
    			input = read_range(n, 1, mapIterator(map));
    			System.out.println("Thanks, Publisher Already Exists");
    		}
        	
        }
        
        if (action == 2) {
        	
          	
        	System.out.println("Enter the key of  the Publisher you want to edit\n"+ "Enter '0' to return to previous menu\n"+ publisherOptions());
        	Scanner n = new Scanner(System.in);
        	String userInput = n.nextLine();
    		Integer input = Integer.parseInt(userInput);
    		
    		if (input == 0) {
    			displayMenu();
    			
    		}
    		
    		else {
    			input = read_range(n, 1, mapIterator(map));
    			
    			String pubName = getNewPubName();
            	
            	//String updatedPublisherName = pubName;
            	HashMap<String, String> pubmap = getPubIdForEdit(pubName, Integer.toString(input));
 
            	
            	
            	
					try {
						editPublisher(pubmap);
					} catch (IOException e) {
						System.out.println("Somthing Went Wrong");
					}
				
    		
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
        
        if (action == 3) {
        	BookServices bs = new BookServices();
        	System.out.println("Enter the key of the book you wish to delete\n" + publisherOptions());
        	Scanner n = new Scanner(System.in);
        	String deleteKey = n.nextLine();
        	Integer keyValue = Integer.parseInt(deleteKey);
        	
        	try {
        		keyValue = read_range(n, 1, mapIterator(map));
        		ArrayList<String > key = UtilityClass.getPubId(deleteKey);
        		
				deletePublisher(key);
				deleteBookPublisher(deleteKey);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("<<<File not found>>>");
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
        
        if (action == 4) {
        	
        	viewAllPublishers();
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
        
        if (action == 5) {
        	

        	mainMenu.displayMenu();
        }
		
	}
	
	public String getNewPubName() {
		Scanner n = new Scanner(System.in);
		System.out.println("Enter Publishers Name");
    	String pubName = n.nextLine();
    	return pubName;
	}
	

	
	public String  publisherOptions() {
		
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
	
	public void addPublisher(String name) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(uri), true))) {
			
			
			String data = sortId(createHashMap(file))+" "+name;
			
			writer.write(data);
			writer.newLine();
			
			System.out.println("\n");
			System.out.println("\n");
			System.out.println(name+" has been added as a publisher");
			writer.flush();
			
			//displayMenu();
			
		} catch(IOException e) {
			
			System.out.println("Failed to find publisher.txt");
			displayMenu();
			
		}
		
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
				System.out.println(strBuilder);
				map.put(strs[0], strBuilder.toString()); // consider if you had a third part of the publishers name.
				
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	public void viewAllPublishers() {
		
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
			displayMenu();
				
		  }
		}
	
	public void deletePublisher(ArrayList<String> id) throws IOException {
	
		BookServices bs = new BookServices();
		for (String item : id) {
			
			bs.deleteBook(item);
	}
}
	
	public void deleteBookPublisher(String id) throws IOException {
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
            
        }
    
	}
	
	public void editPublisher(HashMap<String, String> map) throws IOException {
		 File file = new File(uri);
	        BufferedWriter bufreader = null;;
	        
	        try{
	            
	           
	            bufreader = new BufferedWriter( new FileWriter(file) );
	 
	         
	            for(Map.Entry<String, String> entry : map.entrySet()){
	            	
	                bufreader.write(entry.getKey() + " " + entry.getValue());
	              
	                bufreader.newLine();
	            }
	            
	            bufreader.flush();
	 
	        } catch(IOException e){
	        	
	            System.out.println("File not found");
	            
	        } finally{
	        	
	                bufreader.close();
	               
	            
	        }
	}
		
}


