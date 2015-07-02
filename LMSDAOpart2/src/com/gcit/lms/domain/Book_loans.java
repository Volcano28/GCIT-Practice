package com.gcit.lms.domain;

import java.lang.reflect.Method;

public class Book_loans {
	
	private int bookId;
	private int branchId;
	private int cardNo;
	private String DateOut;
	private String DateIn;
	private String DueDate;
	
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
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getDateOut() {
		return DateOut;
	}
	public void setDateOut(String dateOut) {
		DateOut = dateOut;
	}
	public String getDateIn() {
		return DateIn;
	}
	public void setDateIn(String dateIn) {
		DateIn = dateIn;
	}
	public String getDueDate() {
		return DueDate;
	}
	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}
	
//	public static void main(String[] args){
//		try{
//			String className = "com.gcit.lms.domain.Book_loans";
//			String attrib = "CardNo";
//			
//			LibraryBranch a = new LibraryBranch();
//			Class c = Class.forName(className);
//			Object obj = c.newInstance();
//			
//			//a.setCardNo("test");
//			Method setter = c.getMethod("set"+attrib.substring(0,1).toUpperCase()+attrib.substring(1, attrib.length()), String.class);
//			setter.invoke(obj, "test");
//			
//			System.out.println(a.getLibraryBranchId());
//			
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}

}
