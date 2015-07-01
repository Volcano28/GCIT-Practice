package com.gcit.lib;

import java.util.Scanner;

public class Main {

	static Scanner console = new Scanner(System.in); // For user Input

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		mainMenu(); // Calling main Menu for user.

	}

	public static void mainMenu(){

		System.out.println("Welcome to the GCIT Library Management System.");
		System.out.println("*--------------------------------------------*");
		System.out.println("Please Choose a Category");
		System.out.println("*--------------------------------------------*");
		System.out.println("Please Enter 1 if you want to use as a Librarian Section");
		System.out.println("Please Enter 2 if you want to use as a Administratior Section");
		System.out.println("Please Enter 3 if you want to use as a Borrower Section");

		int UsersInput = console.nextInt();

		while(UsersInput < 1 || UsersInput > 3 ){ //The User should input between 1 & 3.
			
			System.out.println("The number you entered is invalid,"
					+ " Please try to enter a number between 1 & 3");
			
			UsersInput = console.nextInt();	
		}
		
		if(UsersInput == 1){
			
			System.out.println("Welcome to the Librarian Section");
			System.out.println("*------------------------------*");
			TheLibrarian.showLibBranchs();
		
			
		} else if(UsersInput == 2){
			System.out.println("Welcome to the Administration Section");
			System.out.println("*-----------------------------------*");
			TheAdministrator.AdministratorMenu();
			
		} else if(UsersInput == 3){
			System.out.println("Welcome to the Borrower Section");
			System.out.println("*-----------------------------*");
			TheBorrower.CheckCardNumber();
			
		}
	}
}

