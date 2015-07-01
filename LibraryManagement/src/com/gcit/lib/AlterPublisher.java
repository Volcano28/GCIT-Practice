package com.gcit.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AlterPublisher {

	public AlterPublisher() {
		// TODO Auto-generated constructor stub
	}
	
	static Scanner libconsole = new Scanner(System.in);

	public static void UpdatePublisher(){

		try {
			System.out.println("Enter the new name of the publisher ");
			int publisherId = libconsole.nextInt();
			libconsole.nextLine();
			
			String publishername = libconsole.nextLine();
			
			
			String publisherAdress = libconsole.nextLine();
		

			//			System.out.println("Enter the new Authors name");
			//			String InputLibAuthorName = libconsole.nextLine();


//			System.out.println("You have chosen to update name to "
//
//					+ "and Book Title: " 
//					+ InputLibbooktitle 
//					); 

			Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String upDateTitleQuery = "INSERT INTO tbl_publisher VALUES(?, ?, ?)";
			PreparedStatement pstmt = conn1.prepareStatement(upDateTitleQuery);
			
			pstmt.setInt(1,publisherId);
			pstmt.setString(2,publishername);
			pstmt.setString(3,publisherAdress);
			pstmt.executeQuery();
			
			String selectCopiesQuery = "SELECT * FROM tbl_publisher";

			PreparedStatement pstmt1 = conn1.prepareStatement(selectCopiesQuery);
			ResultSet rs = pstmt1.executeQuery();

			while(rs.next()){

				//libBranch.add(rs.getString("branchName") + rs.getInt("branchAddress"));
				//System.out.println(libBranch); For Testing
				System.out.println(rs.getString("publisherName"));
				//	System.out.println("Branch Address: " +rs.getString("branchAddress"));
				System.out.println("---------------------------");
				//System.out.println(+ (num +1) +") Quit to previous menu");
			}




			//Main.mainMenu();
			//showLibBranches2(branchId);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
