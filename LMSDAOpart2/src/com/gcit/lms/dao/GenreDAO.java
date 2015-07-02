package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Genre;

public class GenreDAO extends BaseDAO {

	public GenreDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void create(Genre genre) throws Exception {
		save("insert into tbl_genre (genre_name) values(?)",
				new Object[] { genre.getGenreName() });
	}

	public void update(Genre genre) throws Exception {
		save("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName() });

	}

	public void delete(Genre genre) throws Exception {
		save("delete from tbl_genre where genre_id = ?",
				new Object[] { genre.getGenreName()});
	}

	public List<Genre> readAll() throws Exception{
		return (List<Genre>) read("select * from tbl_genre", null);
		
	}

	public Genre readOne(int genre_id) throws Exception {
		List<Genre> genres = (List<Genre>) read("select * from tbl_genre", new Object[] {genre_id});
		if(genres!=null && genres.size()>0){
			return genres.get(0);
		}
		return null;
	}

	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<Genre> genres =  new ArrayList<Genre>();
		
		while(rs.next()){
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genreId"));
			a.setGenreName(rs.getString("genreName"));
			
			genres.add(a);
		}
		return genres;
	}

}
