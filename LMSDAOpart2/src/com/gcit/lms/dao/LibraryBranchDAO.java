package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO{

	public LibraryBranchDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void create(LibraryBranch lib) throws Exception {
		save("insert into tbl_library_branch (branchName, branchAddress) values(?, ?)",
				new Object[] { lib.getLibraryBranchName(), lib.getLibraryBranchAddress()});
	}

	public void update(LibraryBranch lib) throws Exception {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { lib.getLibraryBranchName(), lib.getLibraryBranchAddress()});
	}

	public void delete(LibraryBranch lib) throws Exception {
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] { lib.getLibraryBranchName(),lib.getLibraryBranchAddress()});
	}

	public List<LibraryBranch> readAll() throws Exception{
		return (List<LibraryBranch>) read("select * from tbl_library_branch", null);
		
	}

	public LibraryBranch readOne(int branchId) throws Exception {
		List<LibraryBranch> branches = (List<LibraryBranch>) read("select * from tbl_library_branch", new Object[] {branchId});
		if(branches!=null && branches.size()>0){
			return branches.get(0);
		}
		return null;
	}

	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<LibraryBranch> branches =  new ArrayList<LibraryBranch>();
		
		while(rs.next()){
			LibraryBranch a = new LibraryBranch();
			a.setLibraryBranchId(rs.getInt("branchId"));
			a.setLibraryBranchName(rs.getString("branchName"));
			a.setLibraryBranchAddress(rs.getString("branchAddress"));
			
			branches.add(a);
		}
		return branches;
	}

}
