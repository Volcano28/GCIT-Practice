package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.LibBranch;

public class LibBranchDAO extends BaseDAO{

	public LibBranchDAO(Connection conn) throws Exception {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	
	public void create(LibBranch lib) throws Exception {
		save("insert into tbl_library_branch (branchName, branchAddress) values(?, ?)",
				new Object[] { lib.getLibraryBranchName(), lib.getLibraryBranchAddress()});
	}

	public void update(LibBranch lib) throws Exception {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { lib.getLibraryBranchName(), lib.getLibraryBranchAddress()});
	}

	public void delete(LibBranch lib) throws Exception {
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] { lib.getLibraryBranchName(),lib.getLibraryBranchAddress()});
	}

	public List<LibBranch> readAll() throws Exception{
		return (List<LibBranch>) read("select * from tbl_library_branch", null);
		
	}

	public LibBranch readOne(int branchId) throws Exception {
		List<LibBranch> branches = (List<LibBranch>) read("select * from tbl_library_branch where branchId = ?", new Object[] {branchId});
		if(branches!=null && branches.size()>0){
			return branches.get(0);
		}
		return null;
	}

	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<LibBranch> branches =  new ArrayList<LibBranch>();
		
		while(rs.next()){
			LibBranch lib = new LibBranch();
			lib.setLibraryBranchId(rs.getInt("branchId")); 
 			lib.setLibraryBranchAddress(rs.getString("branchAddress")); 
 			lib.setLibraryBranchName(rs.getString("branchName")); 
 			
 			branches.add(lib); 
		}
		return branches;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		List<LibBranch> branches = new ArrayList<LibBranch>(); 
		 		while(rs.next()){ 
		 			LibBranch lib = new LibBranch(); 
		 			lib.setLibraryBranchId(rs.getInt("branchId")); 
		 			lib.setLibraryBranchAddress(rs.getString("branchAddress")); 
		 			lib.setLibraryBranchName(rs.getString("branchName")); 
		 			branches.add(lib); 
		 		} 
		 		return branches; 

	}

}
