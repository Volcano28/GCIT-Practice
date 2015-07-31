package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;

public class AuthorDAO extends BaseDAO<Author> implements Serializable, ResultSetExtractor<List<Author>> {

	private static final long serialVersionUID = 1619700647002508164L;
	
	private static final String AUTH_COLLECTION = "Authors";

	public void addAuthor(Author author) throws SQLException {
//		template.update("insert into tbl_author (authorName) values (?)",
//				new Object[] { author.getAuthorName() });
		author.setAuthorId(getNextSequenceId(AUTH_COLLECTION));
		mongoOps.insert(author, AUTH_COLLECTION);
		
	}

	public void updateAuthor(Author author) throws SQLException {
//		template.update("update tbl_author set authorName = ? where authorId = ?",
//				new Object[] { author.getAuthorName(), author.getAuthorId() });
		this.mongoOps.save(author, AUTH_COLLECTION);
	}

	public void removeAuthor(Author author) throws SQLException {
//		template.update("delete from tbl_author where authorId=?",
//				new Object[] { author.getAuthorId() });
		this.mongoOps.remove(author, AUTH_COLLECTION);
	
		
	}

	public List<Author> readAll() throws SQLException {
		return this.mongoOps.findAll(Author.class,AUTH_COLLECTION);	
		}

	public Author readOne(long authorId) throws SQLException {
		Query query = new Query(Criteria.where("_id").is(authorId));
		return this.mongoOps.findOne(query, Author.class, AUTH_COLLECTION);
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
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		while (rs.next()) {
			Author a = new Author();
			//a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}
	
	public List<Author> search( String authorName, int pageNo, int pageSize){
		
		Query query = new Query(Criteria.where("authorName").regex(Pattern.compile(authorName, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		return mongoOps.find(query, Author.class, AUTH_COLLECTION);
		
	}
}
