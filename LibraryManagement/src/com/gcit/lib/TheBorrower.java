package com.gcit.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TheBorrower {

	public TheBorrower() {
		// TODO Auto-generated constructor stub
	}

	static Scanner libconsole = new Scanner(System.in); // For user Input
	static ArrayList<String> libBranch=new ArrayList<String>();
	static int UserInputinLib, UserInputinLib2, UserInputinLib3, chooseInput;
	

	public static void CheckCardNumber(){

		try {
			System.out.println("Please enter your card number");

			UserInputinLib = libconsole.nextInt();
			libconsole.nextLine();

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String checkCardQuery = "select * from tbl_borrower WHERE cardNo=?";

			PreparedStatement pstmt = conn.prepareStatement(checkCardQuery);
			pstmt.setInt(1,UserInputinLib);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("The number you entered is valid");
				showLibBranchs();
			} else{
				System.out.println("The number you entered is invalid please try again");
				CheckCardNumber();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

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
			UserInputinLib2 = libconsole.nextInt();
			if(UserInputinLib2 == 0){
				CheckCardNumber(); 
			} 
			else if(UserInputinLib < 0 || UserInputinLib > libBranch.size() ) {
				System.out.println("Please Enter a number between 1 and " + libBranch.size());
				showLibBranchs();
			} else{

				System.out.println("Choose 1 to Checkout");
				System.out.println("Choose 2 to Return");
				System.out.println("Choose 0 to Previous Menu");
				chooseInput = libconsole.nextInt();
				libconsole.nextLine();

				if(chooseInput == 1){ 
					CheckOut1();
				} else if( chooseInput==2){
					ReturnTheBook(UserInputinLib2);
				} else if(chooseInput == 0){
					showLibBranchs();
					
				}
				
				else{ 
					System.out.println("The number you entered is invalid please try again");
					CheckCardNumber(); 
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void CheckOut1(){
		try {

			Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String selectCopiesQuery = "SELECT title, authorName, tbl_book.bookId FROM tbl_book JOIN tbl_book_authors ON tbl_book.bookId = tbl_book_authors.bookId JOIN tbl_author ON tbl_author.authorId = tbl_book_authors.authorId JOIN tbl_book_copies ON tbl_book_copies.bookId = tbl_book.bookId WHERE tbl_book_copies.branchId = ? AND tbl_book_copies.noOfCopies > 0";

			PreparedStatement pstmt = conn2.prepareStatement(selectCopiesQuery);
			pstmt.setInt(1, UserInputinLib2);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Pick the Book you want to check out , from your branch:");
			while(rs.next()){

				//libBranch.add(rs.getString("branchName") + rs.getInt("branchAddress"));
				//System.out.println(libBranch); For Testing
				System.out.println(rs.getInt("bookId")+") " +rs.getString("title"));
				//	System.out.println("Branch Address: " +rs.getString("branchAddress"));
				System.out.println("---------------------------");
				//System.out.println(+ (num +1) +") Quit to previous menu");
			}
			System.out.println("0) Quit to previous menu");

			UserInputinLib3 = libconsole.nextInt();
			libconsole.nextLine();
			CheckOut2(UserInputinLib3);

			if(UserInputinLib3 == 0){

				showLibBranchs();

			} 
			//CheckOut2(UserInputinLib3);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void CheckOut2(int BookId){

		try {
			Date date = new Date();
			long now = date.getTime();//
			Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String AddEntryQuery = "INSERT INTO tbl_book_loans VALUES(? , ? , ? , ? , ?, ?)";
			PreparedStatement pstmt = conn2.prepareStatement(AddEntryQuery);
			pstmt.setInt(1, UserInputinLib3);
			pstmt.setInt(2, UserInputinLib2);
			pstmt.setInt(3, UserInputinLib);
			pstmt.setTimestamp(4, new Timestamp(now));	//Constructs a Timestamp object using a milliseconds time value.	
			pstmt.setTimestamp(5, new Timestamp(now+7*24*3600*1000));
			pstmt.setTimestamp(6, null);
			pstmt.executeUpdate();
			System.out.println("Succesfully CheckedOut");

			String updateQuery = "UPDATE tbl_book_copies SET noOfCopies = (noOfCopies - 1) WHERE tbl_book_copies.bookId = ?";
			PreparedStatement pstmt1 = conn2.prepareStatement(updateQuery);
			pstmt1 = conn2.prepareStatement(updateQuery); 
			pstmt1.setInt(1, UserInputinLib3); 
			pstmt1.executeUpdate(); 

			showLibBranchs();


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void ReturnTheBook(int BranchId){

		try {
			Date date = new Date();
			long now = date.getTime();
			System.out.println("Enter the books name you wanted to return: ");
			String giveTitle = libconsole.nextLine();
			
			int ChosenbookId = 0;
			Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String ReturnBookQuery = "SELECT bookId from tbl_book WHERE title=?";
			PreparedStatement pstmt = conn2.prepareStatement(ReturnBookQuery);
			pstmt.setString(1, giveTitle);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){

				ChosenbookId = rs.getInt("bookId");
				System.out.println("Returned = " + rs.getInt("bookId"));
				System.out.println("---------------------------");
			

			String updateQuery ="UPDATE tbl_book_loans SET dateIn = ? WHERE tbl_book_loans.bookId =? and tbl_book_loans.cardNo =?"; 
			PreparedStatement pstmt1 = conn2.prepareStatement(updateQuery); 
			pstmt1.setTimestamp(1, new Timestamp(now)); 
			pstmt1.setInt(2, ChosenbookId); 
			pstmt1.setInt(3, UserInputinLib); 
			pstmt1.executeUpdate(); 

			System.out.println("Your Information has been updated.");

			//Update noOfCopies 
			String updateCopiesNumberQuery = "UPDATE tbl_book_copies SET noOfCopies = (noOfCopies + 1) WHERE tbl_book_copies.bookId = ?"; 
			PreparedStatement pstmt2 = conn2.prepareStatement(updateCopiesNumberQuery); 
			pstmt2.setInt(1, ChosenbookId); 
			pstmt2.executeUpdate(); 
			System.out.println("Record number has been reported.");
			showLibBranchs();
			}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
}

}
