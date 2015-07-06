package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.BookLoans;

public class BookLoansDAO extends BaseDAO {

	public BookLoansDAO(Connection conn) throws Exception {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(BookLoans bookL) throws Exception { 
		//dateIn? 
		save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values(?,?,?,?,?)", 
				new Object[] { bookL.getBookId(), bookL.getBranchId(),  
				bookL.getCardNo(), bookL.getDateOut(), bookL.getDueDate() }); 
	}

	public void update(BookLoans bookL) throws Exception {
		save("update tbl_book_loans set dateOut = ?, dueDate = ?, dateIn = ? where bookId = ? and cardNo = ? and branchId = ?", 
				new Object[] {bookL.getDateOut(), bookL.getDueDate(), bookL.getDateIn(), bookL.getBookId(), 
				bookL.getCardNo(), bookL.getBranchId()}); 
	}

	public void delete(BookLoans bookL) throws Exception { 
		save("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?", 
				new Object[] { bookL.getBookId(), bookL.getBranchId(), bookL.getCardNo()});

	}

	public List<BookLoans> readAll() throws Exception{ 
		return (List<BookLoans>) read("select * from tbl_book_loan", null); 

	} 


	public BookLoans readOne(int bookId, int cardNo, int branchId) throws Exception { 
		List<BookLoans> bookLoans = (List<BookLoans>) read("select * from tbl_book_loans where bookId = ? and branchId = ?" 
				+ " and cardNo = ?", new Object[] {bookId, branchId, cardNo}); 
		if(bookLoans!=null && bookLoans.size()>0){ 
			return bookLoans.get(0); 
		} 
		return null; 
	} 


	public List<BookLoans> extractData(ResultSet rs) throws Exception { 
		return extractData(rs);
	} 


	@Override 
	public List<BookLoans> extractDataFirstLevel(ResultSet rs) throws Exception { 
		List<BookLoans> bookLoans =  new ArrayList<BookLoans>(); 

		while(rs.next()){ 
			BookLoans blns = new BookLoans(); 
			blns.setBookId(rs.getInt("bookId")); 
			blns.setBranchId(rs.getInt("branchId")); 				 			
			blns.setCardNo(rs.getInt("cardNo")); 
			blns.setDateOut(rs.getDate("dateOut")); 
			blns.setDueDate(rs.getDate("dueDate"));  
			blns.setDateIn(rs.getDate("dateIn")); 

			bookLoans.add(blns); 
		} 
		return bookLoans; 

	} 




}
