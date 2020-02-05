package com.ss.crudProject.read;

import java.util.Scanner;




public class Main extends UtilityClass implements Menu {



	public static void main(String[] args) {
		Main main = new Main();
		main.displayMenu();
	}

	
	public void displayMenu() {
		int action;
		
		Scanner userSelection = new Scanner(System.in);
		
		System.out.println("Choose A Service");
        System.out.println("1. Author Services");
        System.out.println("2. Publisher Services");
        System.out.println("3. Book Services");
        
        action = read_range(userSelection, 1,4);
        
        if (action == 1) {
        	
        	AuthorServices as = new AuthorServices();
        	as.displayMenu();
        
        }
        
        if (action == 2) {
        	
        	PublisherServices ps = new PublisherServices();
        	ps.displayMenu();
        	
        	
        }
        
        if (action == 3) {
        	
        	BookServices bs = new BookServices();
        	bs.displayMenu();
        	
        	
        }
        
        userSelection.close();
        
	}





}
