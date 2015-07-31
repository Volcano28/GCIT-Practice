package com.gcit.lms.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Genre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5406550796957034888L;

	@Id
	private long genre_id;
	
	private String genre_name;
	
	private List<Book> books;
	
	
	/**
	 * @return the genre_id
	 */
	public long getGenre_id() {
		return genre_id;
	}
	/**
	 * @param genre_id the genre_id to set
	 */
	public void setGenre_id(long genre_id) {
		this.genre_id = genre_id;
	}
	/**
	 * @return the genre_name
	 */
	public String getGenre_name() {
		return genre_name;
	}
	/**
	 * @param genre_name the genre_name to set
	 */
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	

	
}
