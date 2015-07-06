package com.gcit.lms.service;

import java.sql.Connection;
import java.util.List;

import com.gcit.lms.dao.LibBranchDAO;
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

}
