package com.gcit.lib;

import java.util.Scanner;

public class TheAdministrator {

	public TheAdministrator() {
		// TODO Auto-generated constructor stub
	}

	static Scanner libconsole = new Scanner(System.in);
	static int UserInputinLib;
	public static void AdministratorMenu(){
		//System.out.println("Test");
		
		System.out.println("1)	Add/Update/Delete Book and Author");
				System.out.println( "2)	Add/Update/Delete Publishers");
				System.out.println("3)	Add/Update/Delete Library Branches"); 
				System.out.println("4)	Add/Update/Delete Borrowers");
				System.out.println("5)	Over-ride Due Date for a Book Loan");
		
		UserInputinLib = libconsole.nextInt();
		
		switch (UserInputinLib) {
		case 1:
			
			AlterBookAndAuthor.ShowBooksAndAuthors();
			break;
			
		case 2:

		default:
			break;
		}
		
	}

}
