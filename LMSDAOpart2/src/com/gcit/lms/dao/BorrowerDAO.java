package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Borrower;

public class BorrowerDAO extends BaseDAO {

	public void create(Borrower bor) throws Exception {
		save("insert into tbl_borrower (cardNo, name, address, phone) values(?, ?, ?, ?)",
				new Object[] { bor.getCardNo(), bor.getBorrowerName(), bor.getBorrowerAddress(), bor.getBorrowerPhone() });
	}

	public void update(Borrower bor) throws Exception {
		save("update tbl_borrower set name = ?, address, phone where CardNo = ?",
				new Object[] { bor.getBorrowerName(), bor.getBorrowerAddress(), bor.getBorrowerPhone()});
	}

	public void delete(Borrower bor) throws Exception {
		save("delete from tbl_borrower where CardNo = ?",
				new Object[] { bor.getBorrowerName(),bor.getBorrowerAddress(), bor.getBorrowerPhone()});
	}

	public List<Borrower> readAll() throws Exception{
		return (List<Borrower>) read("select * from tbl_borrower", null);
		
	}

	public Borrower readOne(int cardNo) throws Exception {
		List<Borrower> borrowers = (List<Borrower>) read("select * from tbl_borrower", new Object[] {cardNo});
		if(borrowers!=null && borrowers.size()>0){
			return borrowers.get(0);
		}
		return null;
	}

	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<Borrower> borrowers =  new ArrayList<Borrower>();
		
		while(rs.next()){
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("CardNo"));
			a.setBorrowerName(rs.getString("name"));
			a.setBorrowerAddress(rs.getString("address"));
			a.setBorrowerPhone(rs.getString("phone"));
			borrowers.add(a);
		}
		return borrowers;
	}
	
}
