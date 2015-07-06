package com.gcit.lms.service;

import java.sql.Connection;
import java.util.List;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.LibBranchDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.LibBranch;

public class LibraryService {

	public List<LibBranch> ListBranches() throws Exception { 
		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		try { 
			LibBranchDAO lib = new LibBranchDAO(conn); 
			List<LibBranch> branches = lib.readAll(); 
			conn.commit(); 
			return branches; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} finally { 
			conn.close(); 
		} 
	} 

	public void UpdateLibBranch(LibBranch branch) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(branch == null
					|| branch.getLibraryBranchName() == null
					|| branch.getLibraryBranchName().length() == 0
					|| branch.getLibraryBranchName().length() > 45 
					|| branch.getLibraryBranchAddress() == null
					|| branch.getLibraryBranchAddress().length() == 0
					|| branch.getLibraryBranchAddress().length() > 45 ){ 
				throw new Exception("The LiBranch cannot be null"); 
			}else{
				LibBranchDAO lib = new LibBranchDAO(conn);
				lib.update(branch); 
				conn.commit(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public List<Book> ListBook() throws Exception { 
		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 


		try { 
			BookDAO bdao = new BookDAO(conn); 
			List<Book> books = bdao.readAll(); 
			conn.commit();//not sure if needed 
			return books; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			conn.rollback();//not sure if needed 
			return null; 
		} finally { 
			conn.close(); 
		} 
	} 

	public void UpdateBookCopies(int branchId, int bookId, int noOfCopies ) throws Exception{

		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		BookCopiesDAO bcdao = new BookCopiesDAO(conn); 
		BookDAO bookdao = new BookDAO(conn); 
		LibBranchDAO branchdao = new LibBranchDAO(conn); 

		try{

			if(bookdao.readOne(bookId) == null || branchdao.readOne(branchId) == null){ 
				throw new Exception("The book id or The Branch id does not match any book or branch"); 
			} else {
			
			BookCopies bookcop = new BookCopies(); 
			bookcop.setBookId(bookId); 
			bookcop.setBranchId(branchId); 
			bookcop.setNoOfCopies(noOfCopies); 


			LibBranch lb = branchdao.readOne(branchId); 


			}}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			conn.rollback();//not sure if needed 
		} finally { 
			conn.close(); 
		} 

	}
}
