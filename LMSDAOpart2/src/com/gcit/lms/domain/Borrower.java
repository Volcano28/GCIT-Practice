package com.gcit.lms.domain;

import java.lang.reflect.Method;

public class Borrower {

	private String borrowerName;
	private String borrowerAddress;
	private String borrowerPhone;


public Borrower() {
	// TODO Auto-generated constructor stub
}

private int cardNo;

public int getCardNo() {
	return cardNo;
}
public void setCardNo(int cardNo) {
	this.cardNo = cardNo;
}
public String getBorrowerName() {
	return borrowerName;
}
public void setBorrowerName(String name) {
	this.borrowerName = name;
}
public String getBorrowerAddress() {
	return borrowerAddress;
}
public void setBorrowerAddress(String address) {
	this.borrowerAddress = address;
}
public String getBorrowerPhone() {
	return borrowerPhone;
}
public void setBorrowerPhone(String phone) {
	this.borrowerPhone = phone;
}

public static void main(String[] args){
	try{
		String className = "com.gcit.lms.domain.Borrower";
		String attrib = "BorrowerName";

		Author a = new Author();
		Class c = Class.forName(className);
		Object obj = c.newInstance();

		a.setAuthorName("test");
		Method setter = c.getMethod("set"+attrib.substring(0,1).toUpperCase()+attrib.substring(1, attrib.length()), String.class);
		setter.invoke(obj, "test");

		System.out.println(a.getAuthorName());

	}catch (Exception e){
		e.printStackTrace();
	}

}
}

