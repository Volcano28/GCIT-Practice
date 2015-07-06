package com.gcit.lms.domain;

import java.sql.Date;

public class BookLoans {


	private int bookId; 
	private int branchId;
	private int cardNo;
	private Date DateOut;
	private Date DateIn;
	private Date DueDate;

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public Date getDateOut() {
		return DateOut;
	}
	public void setDateOut(Date dateOut) {
		DateOut = dateOut;
	}
	public Date getDateIn() {
		return DateIn;
	}
	public void setDateIn(Date dateIn) {
		DateIn = dateIn;
	}
	public Date getDueDate() {
		return DueDate;
	}
	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}


}
