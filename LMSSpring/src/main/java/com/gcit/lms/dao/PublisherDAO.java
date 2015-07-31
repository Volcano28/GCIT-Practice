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
import com.gcit.lms.domain.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> implements Serializable, ResultSetExtractor<List<Publisher>> {


	private static final long serialVersionUID = 1619700647002508164L;
	private static final String PUB_COLLECTION = "Publishers";

	public void addPublisher(Publisher publisher) throws SQLException {
		publisher.setPublisherId(getNextSequenceId(PUB_COLLECTION));
		mongoOps.insert(publisher, PUB_COLLECTION);
	} 

	public void updatePublisher(Publisher publisher) throws SQLException {
		mongoOps.save(publisher, PUB_COLLECTION);
	}

	public void removePublisher(Publisher publisher) throws SQLException {
		mongoOps.remove(publisher,PUB_COLLECTION);
	}

	public List<Publisher> readAll() throws SQLException {
		return this.mongoOps.findAll(Publisher.class,PUB_COLLECTION);	
	}

	public Publisher readOne(long publisherId) throws SQLException {
		Query query = new Query(Criteria.where("_id").is(publisherId));
		return this.mongoOps.findOne(query, Publisher.class, PUB_COLLECTION);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while (rs.next()) {
			Publisher a = new Publisher();
			//a.setId(rs.getInt("publisherId"));
			//a.setName(rs.getString("publisherName"));

			publishers.add(a);
		}
		return publishers;
	}
	
public List<Publisher> search( String publisherName, int pageNo, int pageSize){
		
		Query query = new Query(Criteria.where("publisherName").regex(Pattern.compile(publisherName, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		return mongoOps.find(query, Publisher.class, PUB_COLLECTION);
		
	}
}
