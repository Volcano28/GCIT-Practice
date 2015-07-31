package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class BookDAO extends BaseDAO<Book> implements Serializable, ResultSetExtractor<List<Book>> {

	private static final long serialVersionUID = 1619700647002508164L;
	
	@Autowired
	PublisherDAO pDAO;
	
	
	private static final String BOOK_COLLECTION = "Books";
	
	public void addBook(Book book) throws SQLException {


		book.setBookId(getNextSequenceId(BOOK_COLLECTION));
		mongoOps.insert(book, BOOK_COLLECTION);
	}

	public void updateBook(Book book) throws SQLException {
		this.mongoOps.save(book, BOOK_COLLECTION);
	}

	public void removeBook(Book book) throws SQLException {
		this.mongoOps.remove(book, BOOK_COLLECTION);
	}

	public List<Book> readAll() throws SQLException {
		return mongoOps.findAll(Book.class,BOOK_COLLECTION);
	}

	public Book readOne(long bookId) throws SQLException {
		Query query = new Query(Criteria.where("_id").is(bookId));
		return this.mongoOps.findOne(query, Book.class, BOOK_COLLECTION);
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		
		while (rs.next()) {
			Book b = new Book();
			//b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			//b.setPublisher(pDAO.readOne(rs.getInt("pubId")));
			books.add(b);
		}
		return books;
	}
	
	public List<Book> search(String title, int pageNo, int pageSize) throws Exception {
		
		Query query = new Query(Criteria.where("title").regex(Pattern.compile(title,Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		return mongoOps.find(query, Book.class,BOOK_COLLECTION);
	}
}
