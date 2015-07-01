package com.gcit.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;


public class AlterBookAndAuthor {

	public AlterBookAndAuthor() {
		// TODO Auto-generated constructor stub
	}

	static Scanner libconsole = new Scanner(System.in);
	static int UserInputinUpd,insertId,insertPubId;
	static String insertbookTitle;

	public static void ShowBooksAndAuthors(){

		try {
			System.out.println("Here are the books:");

			Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String selectCopiesQuery = "SELECT title, authorName, tbl_book.bookId FROM tbl_book JOIN tbl_book_authors ON tbl_book.bookId = tbl_book_authors.bookId JOIN tbl_author ON tbl_author.authorId = tbl_book_authors.authorId";

			PreparedStatement pstmt = conn2.prepareStatement(selectCopiesQuery);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){

				//libBranch.add(rs.getString("branchName") + rs.getInt("branchAddress"));
				//System.out.println(libBranch); For Testing
				System.out.println(rs.getInt("bookId")+")" +rs.getString("title") +" by " + rs.getString("authorName"));
				//	System.out.println("Branch Address: " +rs.getString("branchAddress"));
				System.out.println("---------------------------");
				//System.out.println(+ (num +1) +") Quit to previous menu");
			}
			System.out.println("Here are your options");
			System.out.println("1)Insert a new book");
			System.out.println("2)Update a new book");
			System.out.println("3)Delete a book");
			System.out.println("0)Go to main");
			int Input = libconsole.nextInt();
			libconsole.nextLine();
			
			System.out.println("Enter the ID");
			//UserInputinUpd = libconsole.nextInt();
			//libconsole.nextLine();
			//libconsole.nextLine();

			switch (Input) {
			case 1:
				InsertNewBook();
				break;
			case 2:
				UpdateBookAuthor(UserInputinUpd);
				break;
			case 3:
				DeleteBooks(UserInputinUpd);
				break;
			case 0:
				ShowBooksAndAuthors();
			default:
				break;
			}
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void InsertNewBook(){
		
		int bookId,authorId,genreId,pubId;
		String publisherName,pubAddress,pubPhone;
		String bookTitle;
		String AuthorName;
		String genreName = null;

		try {
			
			System.out.println("Enter everything required for a book ");
			
			System.out.println("Enter the new publisher name,adress and phone for a book ");
			publisherName = libconsole.nextLine();
			pubAddress = libconsole.nextLine();
			pubPhone = libconsole.nextLine();
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String InsertQuery = "INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?,? ,? )";
			PreparedStatement pstmt = conn.prepareStatement(InsertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,publisherName);
			pstmt.setString(2,pubAddress);
			pstmt.setString(3,pubPhone);
			pstmt.executeUpdate();
			
			
			
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					 pubId = generatedKeys.getInt(1);
				} else {
					throw new SQLException(
							"No key returned");
				}
		        }
			
			System.out.println("Enter a book Title; ");
			bookTitle = libconsole.nextLine();
			
			InsertQuery = "INSERT INTO tbl_book (title, pubId) VALUES(?,?)";
			PreparedStatement pstmt1 = conn.prepareStatement(InsertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmt1.setString(1,bookTitle);
			pstmt1.setInt(2, pubId);
			pstmt1.executeUpdate();
			
			try (ResultSet generatedKeys = pstmt1.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					 bookId = generatedKeys.getInt(1);
				} else {
					throw new SQLException(
							"No key returned");
				}
		        }
			System.out.println("Please enter the author of the book ");
			AuthorName = libconsole.nextLine();
			
			InsertQuery = "INSERT INTO tbl_author(authorName) VALUES(?)";
			PreparedStatement pstmtbook = conn.prepareStatement(InsertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmtbook.setString(1,AuthorName);
			pstmtbook.executeUpdate();
			
			try (ResultSet generatedKeys = pstmtbook.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					 authorId = generatedKeys.getInt(1);
				} else {
					throw new SQLException(
							"No key returned");
				}
		        }
			
			InsertQuery = "INSERT INTO tbl_book_authors (bookId, authorId) VALUES(?,?)";
			PreparedStatement pstmtAuthorins = conn.prepareStatement(InsertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmtAuthorins.setInt(1,bookId);
			pstmtAuthorins.setInt(2,authorId);
			pstmtAuthorins.executeUpdate();
			
			
			InsertQuery = "INSERT INTO tbl_genre(genre_name) VALUES(?)";
			PreparedStatement pstmtgenre = conn.prepareStatement(InsertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmtgenre.setString(1,genreName);
			pstmtgenre.executeUpdate();
			
			try (ResultSet generatedKeys = pstmtgenre.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					 genreId = generatedKeys.getInt(1);
				} else {
					throw new SQLException(
							"No key returned");
				}
		        }
			
			InsertQuery = "INSERT INTO tbl_book_genres(genre_id, bookId) VALUES(?,?)";
			PreparedStatement pstmtgenre2 = conn.prepareStatement(InsertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmtgenre2.setInt(1,genreId);
			pstmtgenre2.setInt(2,bookId);
			pstmtgenre2.executeUpdate();
			
			
			
			// ýnsert týtle & pub ýd return book ýd
			
			System.out.println("Book has been added");
			ShowBooksAndAuthors();
			
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void UpdateBookTitle(int bookId){

		try {
			System.out.println("Enter the new name of the book ");
			String InputLibbooktitle = libconsole.nextLine();

			//			System.out.println("Enter the new Authors name");
			//			String InputLibAuthorName = libconsole.nextLine();


			System.out.println("You have chosen to update name to "

					+ "and Book Title: " 
					+ InputLibbooktitle 
					); 

			Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String upDateTitleQuery = "UPDATE tbl_book SET title= ? WHERE tbl_book.bookId="+bookId+"";

			PreparedStatement pstmt = conn1.prepareStatement(upDateTitleQuery);

			pstmt.setString(1, InputLibbooktitle);

			pstmt.executeUpdate();

			System.out.println("Update the Author of the "+ InputLibbooktitle);
			
			UpdateBookAuthor(UserInputinUpd);

			//Main.mainMenu();
			//showLibBranches2(branchId);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void UpdateBookAuthor(int bookId){

		try {
			System.out.println("Enter the new Authors Name you wanted to change ");
			String InputLibAuthorName = libconsole.nextLine();

			//			System.out.println("Enter the new Authors name");
			//			String InputLibAuthorName = libconsole.nextLine();


			System.out.println("You have chosen to update "

					+ "and Book Author: " 
					+ InputLibAuthorName 
					); 

			Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			String upDateAuthorQuery = "UPDATE tbl_author SET AuthorName= ? WHERE AuthorId="+bookId+"";
			//JOIN tbl_book_authors ON tbl_book.bookId = tbl_book_authors.bookId JOIN tbl_author ON tbl_author.authorId = tbl_book_authors.authorId SET title= ? WHERE tbl_book.bookId="+bookId+"";

			PreparedStatement pstmt = conn1.prepareStatement(upDateAuthorQuery);

			pstmt.setString(1, InputLibAuthorName);

			pstmt.executeUpdate();

			ShowBooksAndAuthors();

			//Main.mainMenu();
			//showLibBranches2(branchId);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void DeleteBooks(int bookId){
		// we added new comments to deletebooks
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		try{	
			System.out.println("Test");
			//int InputDelete = libconsole.nextInt();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "dreamt28");
			//String DeleteBooksQuery = "DELETE FROM tbl_book WHERE tbl_book.bookId="+InputDelete;
			
//			c DeleteBooksQuery =  "DELETE FROM tbl_book_author WHERE bookId = ?"; 
//						PreparedStatement pstmt = conn.prepareStatement(DeleteBooksQuery); 
//			 			pstmt.setInt(1, bookId); 
//			 			pstmt.executeUpdate(); 

//			 			DeleteBooksQuery =  "DELETE FROM tbl_book_genre WHERE bookId = ?";
//			 			 			pstmt = conn.prepareStatement(DeleteBooksQuery); 
//			 			 			pstmt.setInt(1, bookId); 
//			 					pstmt.executeUpdate(); 
			 		 			 
			 			 			//then just delete the book 
			String DeleteBooksQuery = "DELETE FROM tbl_book WHERE bookId = ?"; 
			PreparedStatement pstmt = conn.prepareStatement(DeleteBooksQuery); 
			 			 			pstmt.setInt(1, bookId); 
			 			 			pstmt.executeUpdate(); 

			 			 			System.out.println("You succesfully deleted!");
			 			 			ShowBooksAndAuthors();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
