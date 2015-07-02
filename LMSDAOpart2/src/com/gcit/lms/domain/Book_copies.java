package com.gcit.lms.domain;

import java.lang.reflect.Method;

public class Book_copies {
	
	private int bookId;
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
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	private int branchId;
	private int noOfCopies;
	
//	public static void main(String[] args){
//		try{
//			String className = "com.gcit.lms.domain.Book_copies";
//			String attrib = "bookId";
//			
//			LibraryBranch a = new LibraryBranch();
//			Class c = Class.forName(className);
//			Object obj = c.newInstance();
//			
//			a.setLibraryBranchName("test");
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
