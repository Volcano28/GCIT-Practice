package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;

@SuppressWarnings("unchecked")
public class GenreDAO extends BaseDAO<Genre> implements ResultSetExtractor<List<Genre>>{
	
	private static final String GEN_COLLECTION = "Genres";

	public void addGenre(Genre genre) throws Exception {
		genre.setGenre_id(getNextSequenceId(GEN_COLLECTION));
		mongoOps.insert(genre,GEN_COLLECTION);
	}

	public void updateGenre(Genre genre) throws Exception {
		this.mongoOps.save(genre,GEN_COLLECTION);
	}

	public void removeGenre(Genre genre) throws Exception {
		this.mongoOps.remove(genre, GEN_COLLECTION);

	}

	public List<Genre> readAll() throws Exception{
	return this.mongoOps.findAll(Genre.class,GEN_COLLECTION);	

	}

	public Genre readOne(long genre_id) throws Exception {
		Query query = new Query(Criteria.where("_id").is(genre_id));
		return this.mongoOps.findOne(query, Genre.class,GEN_COLLECTION);
	}

	@Override
	public List extractData(ResultSet rs) throws SQLException {
		List<Genre> genres =  new ArrayList<Genre>();
		
		while(rs.next()){
			Genre g = new Genre();
			//g.setGenre_id(rs.getInt("genre_id"));
			g.setGenre_name(rs.getString("genre_name"));
			genres.add(g);
		}
		return genres;
	}

public List<Genre> search(String genre_name, int pageNo, int pageSize){
	
	Query query = new Query(Criteria.where("genre_name").regex(Pattern.compile(genre_name,Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
	return mongoOps.find(query, Genre.class,GEN_COLLECTION);
	
}

}
