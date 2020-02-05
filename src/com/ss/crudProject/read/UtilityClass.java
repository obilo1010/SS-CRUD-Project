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
	
	protected static HashMap<String,String> getPubIdForEdit(String n, String m) {
		PublisherServices ps = new PublisherServices();
		BookServices bs = new BookServices();
		HashMap<String, String> map = bs.createHashMap2(ps.file);
		String[] strs = new String[ps.deleteMapIterator(map)];
		
		for (String item : map.keySet()) {
			
			Integer key = Integer.parseInt(item);
			Integer value = Integer.parseInt(m);
			
			
			if (key == value) {
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
			
			
			if (key == value) {
				map.replace(item, n);  
			}
		
	}
		System.out.println("<<"+map+">>");
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
		System.out.print(Arrays.toString(arrList.toArray()));
		return arrList;	
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
//
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
}


