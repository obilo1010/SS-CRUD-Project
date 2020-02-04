package com.ss.crudProject.read;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
	
//	public static void main(String[] args) {
//		PublisherServices ps = new PublisherServices();
////		ps.displayMenu();
//		//ps.createHashMap(file);
//	}

	@Override
	public void displayMenu() {
		
		int action;
		
		//createHashMap(file);
		
		Scanner userSelection = new Scanner(System.in);
		
		System.out.println("1. Add Publisher");
        System.out.println("2. Edit Publisher");
        System.out.println("3. Delete Publisher");
        System.out.println("4. View All Publisher");
        System.out.println("5. Return to Main Menu");
        
        
        action = read_range(userSelection, 1,5);
        
        if (action == 1) {
        	
//        	Scanner n = new Scanner(System.in);
//        	
//        	System.out.println("Enter Publisher's Name");
//        	String pubName = n.nextLine();
//        	
//        	addPublisher(pubName);
//        	n.close();
        	
        	Scanner n = new Scanner(System.in);
        	
        	System.out.println("If the Publisher is on this list, please enter the publisher's key\n" +"Enter '0' if your author is not shown \n" + publisherOptions());
    		
        	String userInput = n.nextLine();
    		Integer input = Integer.parseInt(userInput);
        	
    		if (input == 0) {
    			
    			System.out.println("Enter Publishers Name");
            	String pubName = n.nextLine();
            	
            	addPublisher(pubName);
    			
    		}
    		else {
    			input = read_range(n, 1, mapIterator(map));
    			System.out.println("Thanks, Publisher Already Exists");
    		}
        	
        }
        
        if (action == 2) {
        	
        	
        	System.out.print("Functionality not ready yet");
        	displayMenu();
        }
        
        if (action == 3) {
//        	System.out.println("Functionality not ready yet");
//        	displayMenu();
        	
         	Scanner n = new Scanner(System.in);
        	System.out.println("Enter the key of  the publisher you want to delete\n"+ "Enter '0' to return to previous menu"+ publisherOptions());
        	
        	String userInput = n.nextLine();
    		Integer input = Integer.parseInt(userInput);
    		
    		if (input == 0) {
    			displayMenu();
    			
    		}
    		
    		else {
    			
    			input = read_range(n, 1, mapIterator(map));
    			try {
					deletePublisher(getLineToDelete(input));
				} catch (IOException e) {
					System.out.println("\n");
					System.out.println("Something Went Wrong");
					displayMenu();
				}
    		}
        }
        
        if (action == 4) {
        	
        	viewAllPublishers();
        }
        
        if (action == 5) {
        	

        	mainMenu.displayMenu();
        }
		
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
	
	public void deletePublisher(String id) throws IOException {
		HashMap<String, String> map = createHashMap(file);
		System.out.println(map);
		map.remove(id);
		System.out.println(map);
		
//		FileWriter fstream = new FileWriter(uri);
//	    BufferedWriter out = new BufferedWriter(fstream);
	    
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
		
}


