package com.ss.crudProject.read;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
//import java.util.regex.Pattern;



public abstract class UtilityClass {
	
public static int mapIterator(HashMap<String, String> map) {
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		//map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
		for (String item : map.keySet()) {
			
			Integer key = Integer.parseInt(item);
			list.add(key);
			
		}
		Collections.sort(list);
		return list.get(list.size()-1);
		
}
	
	protected static int read_range(Scanner scanner, int low, int high) {
	    int value;
	    value = scanner.nextInt();
	    while (value < low || value > high) {
	      System.out.print("Please enter a value between " + low + " and " + high + ": ");
	      value = scanner.nextInt();
	    }
	    //scanner.close();
	    return value;
	  }
	
	protected static int read_rangee(Scanner scanner, int num) {
	    int value;
	    value = scanner.nextInt();
	    while (value != num) {
	      System.out.print("Please enter '0' to return to main menu");
	      value = scanner.nextInt();
	    }
	    //scanner.close();
	    return value;
	  }
	
	protected static String sortId(HashMap<String, String> map) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		TreeMap<String, String> treeMap = new TreeMap<>(map);
		System.out.print(map);
		for (String str : treeMap.keySet()) {
		    Integer num = Integer.parseInt(str);
		    l.add(num);
		}
		Collections.sort(l);
		String g = Integer.toString(l.get(l.size()-1)+1);
		return g;
		
	}
	
	protected static String sortPubId(HashMap<String, String> map) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		TreeMap<String, String> treeMap = new TreeMap<>(map);
		for (String str : treeMap.keySet()) {
		    Integer num = Integer.parseInt(str);
		    l.add(num);
		}
		Collections.sort(l);
		String g = Integer.toString(l.get(l.size()-1));
		return g;
		
	}
	
	protected static HashMap<String,String> getPubIdForEdit(String n, String m) {
		PublisherServices ps = new PublisherServices();
		BookServices bs = new BookServices();
		HashMap<String, String> map = bs.createHashMap2(ps.file);
		String[] strs = new String[ps.deleteMapIterator(map)];
		
		for (String item : map.keySet()) {
			
			Integer key = Integer.parseInt(item);
			Integer value = Integer.parseInt(m);
			
			
			if (key != value) {
				continue;
				
			} else {
				map.replace(item, n);  
			}
		
	}
		return map;
}
	protected static HashMap<String,String> getAuthorIdForEdit(String n, String m) {
		AuthorServices as = new AuthorServices();
		BookServices bs = new BookServices();
		HashMap<String, String> map = bs.createHashMap2(as.file);
		String[] strs = new String[as.deleteMapIterator(map)];
		
		for (String item : map.keySet()) {
			
			Integer key = Integer.parseInt(item);
			Integer value = Integer.parseInt(m);
			
			
			if (key != value) {
				continue;
				  
			} else {
				
				map.replace(item, n);
				// if someone enters an author id which doesn't exist because its been deleted, this if statement 
				//will never be true. call main.displayMenu in this else block
				//System.out.println("Author Doesnt exist");
				
			}
	}
		return map;
}
	
	protected static ArrayList<String> getAuthorId(String n) {
	
		BookServices bs = new BookServices();
		AuthorServices as = new AuthorServices();
		ArrayList<String> arrList = new ArrayList<String>();
		HashMap<String, String> map = bs.createHashMap2(bs.file);
		String[] strs = new String[as.deleteMapIterator(map)];
		try {
			Scanner scan = new Scanner(bs.file);
			
			while(scan.hasNextLine()) {
				
				String data = scan.nextLine();
				strs = data.split("\\s+");
				
		for (int i = 0; i<1; i++) {
			
			Integer id = Integer.parseInt(strs[strs.length - 2]);
			Integer input = Integer.parseInt(n);
			
			if ( id == input  ) {
				
				arrList.add(strs[0]);
				
				}
			}	
		}
			scan.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
		System.out.println(Arrays.toString(arrList.toArray()));
		return arrList;	
	}
	
public int deleteMapIterator(HashMap<String, String> map) {
		
		int count = 0;
		//map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
		for (String item : map.keySet()) {
			
			count += 1;	
		}
		return count;
		
}

	
	protected static ArrayList<String> getPubId(String n) {
		
		BookServices bs = new BookServices();
		PublisherServices ps = new PublisherServices();
		ArrayList<String> arrList = new ArrayList<String>();
		HashMap<String, String> map = bs.createHashMap2(bs.file);
		String[] strs = new String[ps.deleteMapIterator(map)];
		try {
			Scanner scan = new Scanner(bs.file);
			
			while(scan.hasNextLine()) {
				
				String data = scan.nextLine();
				strs = data.split("\\s+");
				
		for (int i = 0; i<1; i++) {
			
			Integer id = Integer.parseInt(strs[strs.length - 1]);
			Integer input = Integer.parseInt(n);
			
			if ( id == input  ) {
				
				arrList.add(strs[0]);
				
				}
			}	
		}
			scan.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
		
		return arrList;	
	}
	
//protected HashMap<String, String> createBookHashMap(File file) {
//	
//	HashMap<String, String> map = new HashMap<String, String>();
//		
//		try {
//			Scanner scan = new Scanner(file);
//			
//			while(scan.hasNextLine()) {
//				
//				String data = scan.nextLine();
//				String[] strs = data.split("\\s+");
//				
//				map.put(strs[strs.length-2], "0");
//				
//			}
//			scan.close();
//			
//			System.out.println(map);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			System.out.println("File not found");
//		}
//		
//		return map;
//	}
////
//public static String getLineToEdit(Integer input) {
//	AuthorServices as = new AuthorServices();
//	String str = null;
//	try {
//		Scanner s = new Scanner(as.file);
//		
//		StringBuilder strBuilder = new StringBuilder();
//		
//		while (s.hasNextLine()) {
//			String data = s.nextLine();
//			String[] strs = data.split("\\s+");
//			Integer id = Integer.parseInt(strs[0]);
//			for (int i = 0; i< strs.length; i++) {
//				
//				if (id == input) {
//					strBuilder = strBuilder.append(strs[i]+ " ");
//				}
//			}
//		}
//		
//		
//		str = strBuilder.toString();
//		System.out.println(str);
//		s.close();
//		
//		
//	} catch (FileNotFoundException e) {
//		System.out.println("File Not Found");
//		as.displayMenu();
//	
//}
//	return str;
//
//}
	
	public String getAuthorForEdit() {
		
	AuthorServices authorServices = new AuthorServices();
	String authorId = "";
	System.out.println("If your Author is on this List please enter the author's key\n" + "Enter '0' if your author is not shown\n"+authorServices.authorOptions());
	Scanner u = new Scanner(System.in);
	String bookAuthor = u.nextLine();            	
	Integer bookAuthorId = Integer.parseInt(bookAuthor);
	
	
	if (bookAuthorId == 0) {
		
		System.out.println("Enter Author's First Name");
    	String firstName = u.nextLine();
    	
    	System.out.println("Enter Author's Last Name");
    	String lastName = u.nextLine();
    	
    	authorServices.addAuthor(firstName, lastName);
    	authorId = sortPubId(authorServices.createHashMap(AuthorServices.file));
    	
	} else {
		
		bookAuthorId = read_range(u, 1, mapIterator(AuthorServices.map));
		
		authorId = Integer.toString(bookAuthorId);
		System.out.println("Thanks for choosing the author");
	}
	
	System.out.println(authorId);
	return authorId;
}
	
	public String getPublisherForEdit() {
		
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
		n.close();
		return publisherId;
	}
}


