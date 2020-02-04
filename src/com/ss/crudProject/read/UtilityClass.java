package com.ss.crudProject.read;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
//import java.util.regex.Pattern;

public abstract class UtilityClass {
	
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
	
	protected static String getAuthorId() {
	
		BookServices bs = new BookServices();
		AuthorServices as = new AuthorServices();
		HashMap<String, String> map = bs.createHashMap(bs.file);
		String[] strs = new String[as.deleteMapIterator(map)];
		
		try {
			Scanner scan = new Scanner(bs.file);
			
			while(scan.hasNextLine()) {
				
				String data = scan.nextLine();
				strs = data.split("\\s+");
				
				
//			for (int i = 1; i<strs.length; i++) {
//				
//				if (strs[strs.length - 2] ==  ) {
//					
//				}
//										// consider if you had a third part of the publishers name.
//			}
//				
				//map.put(strs[0], strBuilder.toString()); // consider if you had a third part of the publishers name.
				
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
		
		//map.entrySet().stream().forEach(e -> i++);
		String authorId = strs[strs.length -1];
		return authorId;	
	}
	
protected HashMap<String, String> createBookHashMap(File file) {
	
	HashMap<String, String> map = new HashMap<String, String>();
		
		try {
			Scanner scan = new Scanner(file);
			
			while(scan.hasNextLine()) {
				
				String data = scan.nextLine();
				String[] strs = data.split("\\s+");
				
				map.put(strs[strs.length-2], "0");
				
			}
			scan.close();
			
			System.out.println(map);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
		
		return map;
	}

public String getLineToDelete(Integer input) {
	AuthorServices as = new AuthorServices();
	String str = null;
	try {
		Scanner s = new Scanner(as.file);
		
		StringBuilder strBuilder = new StringBuilder();
		
		while (s.hasNextLine()) {
			String data = s.nextLine();
			String[] strs = data.split("\\s+");
			Integer id = Integer.parseInt(strs[0]);
			for (int i = 0; i< strs.length; i++) {
				
				if (id == input) {
					strBuilder = strBuilder.append(strs[i]+ " ");
				}
			}
		}
		str = strBuilder.toString();
		s.close();
		
		
	} catch (FileNotFoundException e) {
		System.out.println("File Not Found");
		as.displayMenu();
	
}
	return str;

}
}


