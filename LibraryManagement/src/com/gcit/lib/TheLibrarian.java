package com.gcit.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class TheLibrarian {

	public TheLibrarian()  {
		// TODO Auto-generated constructor stub
	}

	static Scanner libconsole = new Scanner(System.in); // For user Input
	static ArrayList<String> libBranch=new ArrayList<String>();// ArrayList for List of Library Branches
	static int UserInputinLib, UserInputinLib2, UserInputinLib3, UserInputinLib4; //Static variables for User input

	public static void showLibBranchs(){

		System.out.println("The Branches are: ");

		try {

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String selectQuery = "select * from tbl_library_branch";

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				libBranch.add(rs.getString("branchName") + rs.getString("branchAddress"));
				//System.out.println(libBranch); For Testing
				System.out.println( rs.getInt("branchId")+") " + "Branch Name: " +rs.getString("branchName") + ", " + "Branch Address: " +rs.getString("branchAddress"));
				//	System.out.println("Branch Address: " +rs.getString("branchAddress"));
				System.out.println("---------------------------");
			}
			System.out.println( "0) Quit to previous menu");
			System.out.println("*------------------------------*");

			UserInputinLib = libconsole.nextInt();
			libconsole.nextLine();

			if(UserInputinLib == 0){

				Main.mainMenu(); 

			} else if(UserInputinLib < 0 || UserInputinLib > libBranch.size() ) {
				System.out.println("Please Enter a number between 1 and " + libBranch.size());
				showLibBranchs();
			}

			showLibBranches2(UserInputinLib);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//*-------------------------------------------------------------------------------------*//



	public static void showLibBranches2(int branchId){

		if(branchId <= libBranch.size()){

			System.out.println("You have chosen " + (libBranch.get(branchId -1)));
			System.out.println("Here are your options");
			System.out.println("1)  Update the details of the Library ");
			System.out.println("2)	Add copies of Book to the Branch");
			System.out.println("0)	Quit to previous menu");

			UserInputinLib2 = libconsole.nextInt();
			libconsole.nextLine();


			if(UserInputinLib2 == 1){			

				try {

					System.out.println("Enter the new Name of the library");
					String InputLibBranchName = libconsole.nextLine();

					System.out.println("Enter the new Address of the library");
					String InputLibBranchAddress = libconsole.nextLine();


					System.out.println("You have chosen to update the Branch with "
							+ "BranchId: " 
							+ UserInputinLib3 
							+ "and BranchName: " 
							+ InputLibBranchName
							+ "and BranchAddress: " 
							+ InputLibBranchAddress); 

					Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
					String upDateQuery = "update tbl_library_branch set branchName= ?, branchAddress= ? where branchId="+UserInputinLib+"";
					//String upDateQuery = "update tbl_library_branch set branchName= ?, branchAddress= ? where branchId="+UserInputinLib+"";

					PreparedStatement pstmt = conn1.prepareStatement(upDateQuery);

					pstmt.setString(1, InputLibBranchName);

					pstmt.setString(2, InputLibBranchAddress);

					//pstmt.setInt(3, UserInputinLib3);

					pstmt.executeUpdate();


					showLibBranches2(branchId);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} 
			} else if(UserInputinLib2 == 2){

				UpdateCopies(UserInputinLib);	

			}  else if(UserInputinLib2 == 0){

				showLibBranchs();

			}
		} 
	} 

	public static void UpdateCopies(int BranchId){

		try {

			System.out.println("Pick the Book you want to add copies of, to your branch:");

			Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			//String selectCopiesQuery = "SELECT title, authorName, tbl_book.bookId FROM tbl_book JOIN tbl_book_authors ON tbl_book.bookId = tbl_book_authors.bookId JOIN tbl_author ON tbl_author.authorId = tbl_book_authors.authorId ";

			String selectCopiesQuery = "SELECT title, authorName, tbl_book.bookId FROM tbl_book JOIN tbl_book_authors ON tbl_book.bookId = tbl_book_authors.bookId JOIN tbl_author ON tbl_author.authorId = tbl_book_authors.authorId JOIN tbl_book_copies ON tbl_book_copies.bookId = tbl_book.bookId WHERE tbl_book_copies.branchId = ?";
			PreparedStatement pstmt = conn2.prepareStatement(selectCopiesQuery);
			pstmt.setInt(1, UserInputinLib);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){

				//libBranch.add(rs.getString("branchName") + rs.getInt("branchAddress"));
				//System.out.println(libBranch); For Testing
				System.out.println(rs.getInt("bookId")+") " +rs.getString("title") +"by " + rs.getString("authorName"));
				//	System.out.println("Branch Address: " +rs.getString("branchAddress"));
				System.out.println("---------------------------");
				//System.out.println(+ (num +1) +") Quit to previous menu");
			}
			System.out.println("0) Quit to previous menu");

			UserInputinLib3 = libconsole.nextInt();
			libconsole.nextLine();

			if(UserInputinLib3 == 0){

				showLibBranchs();
			}

			UpdateCopies2(UserInputinLib3);// Giving me the current no of Copies.


		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void UpdateCopies2(int bookId){

		try {

			Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String NumberOfCopiesQuery = "SELECT noOfCopies, title FROM tbl_book_copies JOIN tbl_book ON tbl_book.bookId = tbl_book_copies.bookId WHERE tbl_book.bookId="+UserInputinLib3;

			PreparedStatement pstmt = conn3.prepareStatement(NumberOfCopiesQuery);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){

				System.out.println("The number of copies of " + rs.getString("title") + "is " + rs.getInt("noOfCopies"));
			}

			System.out.println("Enter the existing number of copies that you have ");
			UserInputinLib4 = libconsole.nextInt();
			libconsole.nextLine();

			UpdateNoOfCopies(UserInputinLib4);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void UpdateNoOfCopies(int noOfCopies){

		try {
			Connection conn4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			//String UpdateTheCopiesQuery = "update tbl_book_copies set noOfCopies=+"+ UserInputinLib4 + " where tbl_book_copies.bookId="+UserInputinLib3;
			String UpdateTheCopiesQuery = "update tbl_book_copies set noOfCopies=? where tbl_book_copies.bookId=?";

			PreparedStatement pstmt = conn4.prepareStatement(UpdateTheCopiesQuery);
			pstmt.setInt(1, UserInputinLib4);
			pstmt.setInt(2, UserInputinLib3);
			//
			pstmt.executeUpdate();
			System.out.println("The number of copies have been updated");
			UpdateCopies(UserInputinLib);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

