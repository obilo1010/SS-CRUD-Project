package com.ss.crudProject.read;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import java.util.stream.Stream;

public class BookServices extends UtilityClass implements Menu{
	
	Main mainMenu = new Main();
	static String uri = "Resources/inputs/books.txt";
	static File file = new File(uri);
	static HashMap<String, String> map = new HashMap<>();
	
//	public static void main(String[] args) throws IOException {
//		BookServices bs = new BookServices();
//		//bs.displayMenu();
//		//bs.deleteBook("3");
//		//System.out.println(bs.createHashMap(file));
//	}
	
	@Override
	public void displayMenu() {
		int action;
		
		//createHashMap(file);
		
		Scanner userSelection = new Scanner(System.in);
		
		System.out.println("1. Add Book");
        System.out.println("2. Edit Book");
        System.out.println("3. Delete Book");
        System.out.println("4. View All Book");
        System.out.println("5. Return to Main Menu");
        
        
        action = read_range(userSelection, 1,5);
        
        if (action == 1) {
        	
        	Scanner n = new Scanner(System.in);
        	System.out.println("If the Book is on this list, please enter the books key\n" +"Enter '0' if your book is not shown \n" + bookOptions());
        	String bookTitle = null;
        	String userInput = n.nextLine();
    		Integer input = Integer.parseInt(userInput);
        	
    		if (input == 0) {
    			
    			System.out.println("Enter Book Title");
            	bookTitle = n.nextLine();
            	
            	String author = getAuthor();
            	
            	String publisher = getPublisher();
            	
            	addBook(bookTitle, author, publisher);
            	
            	
            	System.out.println("Enter '0' to return to Main Menu");
//            	n = new Scanner(System.in);
    			String option = n.nextLine();
    			Integer intoption = Integer.parseInt(option);
    			
    			if (intoption == 0) {
    				mainMenu.displayMenu();
    			}
    			else if (intoption > 0) {
    				intoption = read_rangee(n, 0);
    				mainMenu.displayMenu();
    				}
    			
    			n.close();
            			
            } else {
            	input = read_range(n, 1, mapIterator(map));
            	System.out.print("\n");
            	System.out.println("Book already exists");
            	
            }
        	
        	
        }
        
        if (action == 2) {
        	

        	System.out.println("Enter the key of the book you want to edit\n"+ "Enter '0' to return to previous menu\n"+ bookOptions());
        	Scanner n = new Scanner(System.in);
        	String userInput = n.nextLine();
    		Integer input = Integer.parseInt(userInput);
    		
    		if (input == 0) {
    			displayMenu();
    			
    		}
    		
    		else {
    			
    			input = read_range(n, 1, mapIterator(map));
    			
    			String booksNewTitle = getBooksNewTitle();
    			
    			
            	
            	String authorUpdate = getAuthorForEdit();
            	
            	String publisherUpdate = getPublisherForEdit();
            	String key  = Integer.toString(input);
            	
            	String value = booksNewTitle+" "+authorUpdate+" "+publisherUpdate;
            	
            	
            	
            	try {
					editBook(key, value);
			
				} catch (IOException e) {
					System.out.println("Something Went Wrong");
				}
    		}
        	
    		
    		
			
    		
    		
        }
        
        if (action == 3) {
        	
        	
        	System.out.println("Enter the key of the book you wish to delete\n" + bookOptions());
        	Scanner n = new Scanner(System.in);
        	String deleteKey = n.nextLine(); 
        	//Integer deletedKey = Integer.parseInt(deleteKey);
        	
        	
        	
        	try {
				deleteBook(deleteKey);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("<<<File not found>>>");
			}
        	n.close();
      	
        }
        
        if (action == 4) {
        	
        	readBooks();
        }
        
        if (action == 5) {
        	
        	Main mainMenu = new Main();
        	mainMenu.displayMenu();
        }
		
	}
	
	public String getBooksNewTitle() {
		Scanner n = new Scanner(System.in);
		System.out.println("Enter Book New Title");
    	String booksNewTitle = n.nextLine();
    	//n.close();
    	return booksNewTitle;
	}
	
	
	public void viewAllBooks() {
		
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
	
	public String  bookOptions() {
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
	
	public void addBook(String title, String authorId, String publisherId) {
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(uri), true))) {
			
			
			String data =sortId(createHashMap(file))+" "+title+ " "+ authorId+" "+publisherId;
			
			writer.write(data);
			writer.newLine();
			
			System.out.println("\n"+title+" has been added");
			writer.flush();
			
			
		} catch(IOException e) {
			
			System.out.println("Failed to find books.txt");
			//displayMenu();
			
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
						if (i < strs.length-2) {
							
						 strBuilder = strBuilder.append(" "+strs[i]);							// consider if you had a third part of the publishers name.
					}
				
				}
				map.put(strs[0], strBuilder.toString());
				
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
		
		return map;
	}

public HashMap<String, String> createHashMap2(File file) {
	
	try {
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			
			String data = scan.nextLine();
			String[] strs = data.split("\\s+");
			StringBuilder strBuilder = new StringBuilder();
			
				for (int i = 1; i<strs.length; i++) {
					
						
					 strBuilder = strBuilder.append(" "+strs[i]);							// consider if you had a third part of the publishers name.
				
			
			}
			map.put(strs[0], strBuilder.toString());
			System.out.println(map);
			
		}
		scan.close();
		
		System.out.println(map);
	} catch (FileNotFoundException e) {
		System.out.println("File Not Found");
	}
	
	return map;
}





	
public String getAuthor() {
	Scanner n = new Scanner(System.in);
	String authorId = null;
	AuthorServices authorServices = new AuthorServices();
	System.out.println("If your Author is on this List please enter the author's key\n" + "Enter '0' if your author is not shown");
	System.out.println(authorServices.authorOptions());
	String bookAuthor = n.nextLine();            	
	Integer bookAuthorId = Integer.parseInt(bookAuthor);
	
	if (bookAuthorId == 0) {
		
		System.out.println("Enter Author's First Name");
    	String firstName = n.nextLine();
    	
    	System.out.println("Enter Author's Last Name");
    	String lastName = n.nextLine();
    	
    	authorServices.addAuthor(firstName, lastName);
    	authorId = sortPubId(authorServices.createHashMap(AuthorServices.file));
	} else {
		
		bookAuthorId = read_range(n, 1, mapIterator(AuthorServices.map));
		
		authorId = Integer.toString(bookAuthorId);
		System.out.println("Thanks for choosing the author");
	}
	
	return authorId;
}

public String getPublisher() {
	
	Scanner n = new Scanner(System.in);
	String publisherId = null;
	PublisherServices pubServices = new PublisherServices();
	System.out.println("If your Publisher is on this List please enter the publishers key\n" + "Enter '0' if your publisher is not shown\n"+pubServices.publisherOptions());
	String bookPublisher = n.nextLine();
	Integer bookPublisherId = Integer.parseInt(bookPublisher);
	
	if (bookPublisherId == 0) {
		
		System.out.println("Enter Publishers Name");
    	String pubName = n.nextLine();
    	
    	pubServices.addPublisher(pubName);
    	publisherId = sortPubId(pubServices.createHashMap(PublisherServices.file));
	} else {
		
		bookPublisherId = read_range(n, 1, mapIterator(PublisherServices.map)); //PublisherServices.map
		
		publisherId = Integer.toString(bookPublisherId);
		System.out.println("Thanks for choosing the publisher");
	}
	return publisherId;
}


	public void readBooks() {
		
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
	
	public void deleteBook(String id) throws IOException {
		HashMap<String, String> map = createHashMap2(file);
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
	
	public void editBook(String a, String b) throws IOException {
		HashMap<String, String> editedBookMap = createHashMap2(file);
		
		editedBookMap.put(a,b);
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


