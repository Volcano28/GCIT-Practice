package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.LibraryBranch;

public class LibBranchDAO extends BaseDAO implements ResultSetExtractor<List<LibraryBranch>>{


	
	private static final String LIB_COLLECTION = "Branches";

	public void addLibraryBranch(LibraryBranch librarybranch) throws SQLException {
//		template.update("insert into tbl_author (authorName) values (?)",
//				new Object[] { author.getAuthorName() });
		librarybranch.setBranchId(getNextSequenceId(LIB_COLLECTION));
		mongoOps.insert(librarybranch, LIB_COLLECTION);
		
	}

	public void updateLibraryBranch(LibraryBranch librarybranch) throws SQLException {
//		template.update("update tbl_author set authorName = ? where authorId = ?",
//				new Object[] { author.getAuthorName(), author.getAuthorId() });
		this.mongoOps.save(librarybranch, LIB_COLLECTION);
	}

	public void removeLibraryBranch(LibraryBranch librarybranch) throws SQLException {
//		template.update("delete from tbl_author where authorId=?",
//				new Object[] { author.getAuthorId() });
		this.mongoOps.remove(librarybranch, LIB_COLLECTION);
	
		
	}

	public List<LibraryBranch> readAll() throws SQLException {
		return this.mongoOps.findAll(LibraryBranch.class,LIB_COLLECTION);	
		}

	public LibraryBranch readOne(long branchId) throws SQLException {
		Query query = new Query(Criteria.where("_id").is(branchId));
		return this.mongoOps.findOne(query, LibraryBranch.class, LIB_COLLECTION);
		//		List<Author> authors = (List<Author>) template.query(
//				"select * from tbl_author where authorId = ?",
//				new Object[] { authorId }, this);
//		if (authors != null && authors.size() > 0) {
//			return authors.get(0);
//		} else {
//			return null;
//		}
	}

	@Override
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> authors = new ArrayList<LibraryBranch>();
		while (rs.next()) {
			LibraryBranch a = new LibraryBranch();
			//a.setAuthorId(rs.getInt("authorId"));
			//a.setAuthorName(rs.getString("authorName"));
			
		}
		return authors;
}
	
	public List<LibraryBranch> search( String branchName, int pageNo, int pageSize){
		
		Query query = new Query(Criteria.where("branchName").regex(Pattern.compile(branchName, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		return mongoOps.find(query, LibraryBranch.class, LIB_COLLECTION);
		
	}

}
