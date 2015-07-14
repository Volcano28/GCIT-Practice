package com.gcit.lms.service;

import java.sql.Connection;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibBranch;
import com.gcit.lms.domain.Publisher;

public class AdministrativeService {
		
		
		/***********************************AUTHOR SECTION*******************************************************/


		public void createAuthor(Author author) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if (author == null 
						|| author.getAuthorName() == null
						|| author.getAuthorName().length() == 0
						|| author.getAuthorName().length() > 45) {
					throw new Exception(
							"Author Name cannot be empty or more than 45 Chars");
				} else {
					AuthorDAO adao = new AuthorDAO(conn);
					adao.create(author);
					conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/**********************************************************************************************/

		public void updateAuthor(Author author) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(author == null
						|| author.getAuthorName() == null
						|| author.getAuthorName().length() == 0
						|| author.getAuthorName().length() > 45){ 
					throw new Exception("The Author cannot be null"); 
				}else{
					AuthorDAO adao = new AuthorDAO(conn);
					adao.update(author); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}


		/******************************************************************************************/

		public void deleteAuthor(Author author) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(author == null){ 
					throw new Exception("The Author cannot be null to delete"); 
				}else{
					AuthorDAO adao = new AuthorDAO(conn);
					adao.delete(author); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/******************************************************************************************/

		public Author listOneAuthor(int authorId) throws Exception {

			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 

			try {

				AuthorDAO adao = new AuthorDAO(conn);
				Author author = adao.readOne(authorId);
				return author;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			} finally{
				conn.close();
			}

		}

		public List<Author> listAuthors(int pageNo, int pageSize) throws Exception { 
			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 
			try { 
				AuthorDAO adao = new AuthorDAO(conn); 
				List<Author> authors = adao.readAll(pageNo,pageSize); 
				conn.commit();
				return authors; 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return null; 
			} finally { 
				conn.close(); 
			} 
		} 

		public List<Author> searchAuthors(String searchString) throws Exception{
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readByAuthorName(searchString);
		}

		/***********************************END OF AUTHOR SECTION*******************************************************/

		public void createGenre(Genre genre) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if (genre == null || genre.getGenreName() == null
						|| genre.getGenreName().length() == 0
						|| genre.getGenreName().length() > 45) {
					throw new Exception(
							"Genre Name cannot be empty or more than 45 Chars");
				} else {
					GenreDAO gdao = new GenreDAO(conn);
					gdao.create(genre);
					conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/**********************************************************************************************/
		public void updateGenre(Genre genre) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(genre == null){ 
					throw new Exception("The Genre cannot be null"); 
				}else{
					GenreDAO gdao = new GenreDAO(conn);
					gdao.update(genre); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/**********************************************************************************************/

		public void deleteGenre(Genre genre) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(genre == null){ 
					throw new Exception("The Genre cannot be null"); 
				}else{
					GenreDAO gdao = new GenreDAO(conn);
					gdao.delete(genre); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/******************************************************************************************/

		public Genre listOneGenre(int genre_id) throws Exception {

			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 

			try {

				GenreDAO gdao = new GenreDAO(conn);
				Genre genre = gdao.readOne(genre_id);
				return genre;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			} finally{
				conn.close();
			}

		}

		public List<Genre> listGenres(int pageNo, int pageSize) throws Exception { 
			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 
			try { 
				GenreDAO gdao = new GenreDAO(conn); 
				List<Genre> genres = gdao.readAll(pageNo, pageSize); 
				conn.commit();
				return genres; 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return null; 
			} finally { 
				conn.close(); 
			} 
		} 
		
		public List<Genre> searchGenre(String searchString) throws Exception{
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readByGenreName(searchString);
		}


		/***********************************END OF GENRE SECTION*******************************************************/
		public void createPublisher(Publisher publisher) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if (publisher == null 
						|| publisher.getPublisherName() == null
						|| publisher.getPublisherName().length() == 0
						|| publisher.getPublisherName().length() > 45 
						|| publisher.getPublisherAddress() == null
						|| publisher.getPublisherAddress().length() == 0
						|| publisher.getPublisherAddress().length() > 45
						|| publisher.getPublisherPhone() == null
						|| publisher.getPublisherPhone().length() == 0
						|| publisher.getPublisherPhone().length() > 45 ) {
					throw new Exception(
							"Publisher Name, Address or Phone cannot be empty or more than 45 Chars");
				} else {
					PublisherDAO pdao = new PublisherDAO(conn);
					pdao.create(publisher);
					conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/**********************************************************************************************/
		public void updatePublisher(Publisher publisher) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(publisher == null){ 
					throw new Exception("The Publisher cannot be updated"); 
				}else{
					PublisherDAO pdao = new PublisherDAO(conn);
					pdao.update(publisher); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/**********************************************************************************************/
		public void deletePublisher(Publisher publisher) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(publisher == null){ 
					throw new Exception("The Publisher cannot be deleted"); 
				}else{
					PublisherDAO pdao = new PublisherDAO(conn);
					pdao.delete(publisher); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/******************************************************************************************/

		public Publisher listOnePublisher(int PublisherId) throws Exception {

			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 

			try {

				PublisherDAO pdao = new PublisherDAO(conn);
				Publisher publisher = pdao.readOne(PublisherId);
				return publisher;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			} finally{
				conn.close();
			}

		}

		public List<Publisher> listPublishers(int pageNo, int pageSize) throws Exception { 
			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 
			try { 
				PublisherDAO pdao = new PublisherDAO(conn); 
				List<Publisher> publishers = pdao.readAll(pageNo,pageSize);
				conn.commit();
				return publishers; 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return null; 
			} finally { 
				conn.close(); 
			} 
		} 
		
		public List<Publisher> searchPublishers(String searchString) throws Exception{
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readByPublisherName(searchString);
		}


		/***********************************END OF PUBLISHER SECTION*******************************************************/

		/**************************************BOOK SECTION*********************************************************************/
		public void createBook(Book book) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if (book == null 
						|| book.getTitle() == null
						|| book.getTitle().length() == 0
						|| book.getTitle().length() > 45 ) {
					throw new Exception(
							"Book title cannot be empty or more than 45 Chars");
				} else {
					BookDAO bdao = new BookDAO(conn);
					bdao.create(book);
					conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/**********************************************************************************************/
		public void updateBook(Book book) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(book == null){ 
					throw new Exception("The Book cannot be null"); 
				}else{
					BookDAO bdao = new BookDAO(conn);
					bdao.update(book); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/**********************************************************************************************/
		public void deleteBook(Book book) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(book == null ){ 
					throw new Exception("The Book cannot be null"); 
				}else{
					BookDAO bdao = new BookDAO(conn);
					bdao.delete(book); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/******************************************************************************************/

		public Book listOneBook(int bookId) throws Exception {

			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 

			try {

				BookDAO bdao = new BookDAO(conn);
				Book book = bdao.readOne(bookId);
				return book;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			} finally{
				conn.close();
			}

		}

		public List<Book> listBooks(int pageNo, int pageSize) throws Exception { 
			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 
			try { 
				BookDAO bdao = new BookDAO(conn); 
				List<Book> books = bdao.readAll(pageNo, pageSize);
				conn.commit(); 
				return books; 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return null; 
			} finally { 
				conn.close(); 
			} 
		} 
		
		public List<Book> searchBooks(String searchString) throws Exception{
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readByBookName(searchString);
		}


		/***********************************END OF BOOK SECTION*******************************************************/
		
		/*************************************** LIBRARY SECTION*********************************************************************/

		public void createLibraryBranch(LibBranch branch) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if (branch == null 
						|| branch.getLibraryBranchName() == null
						|| branch.getLibraryBranchName().length() == 0
						|| branch.getLibraryBranchName().length() > 45 
						|| branch.getLibraryBranchAddress() == null
						|| branch.getLibraryBranchAddress().length() == 0
						|| branch.getLibraryBranchAddress().length() > 45 ) {
					throw new Exception(
							"Library Name or Address cannot be empty or more than 45 Chars");
				} else {
					LibBranchDAO lib = new LibBranchDAO(conn);
					lib.create(branch);
					conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/******************************************************************************************/

		public void UpdateLibBranch(LibBranch branch) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(branch == null){ 
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

		/******************************************************************************************/

		public void deleteLibBranch(LibBranch branch) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(branch == null){ 
					throw new Exception("The Book cannot be null"); 
				}else{
					LibBranchDAO lib = new LibBranchDAO(conn);
					lib.delete(branch); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}
		/******************************************************************************************/

		public LibBranch ListOneBranch(int branchId) throws Exception {

			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 

			try {

				LibBranchDAO lib = new LibBranchDAO(conn);
				LibBranch branch = lib.readOne(branchId);
				return branch;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			} finally{
				conn.close();
			}

		}

		public List<LibBranch> listBranches(int pageNo,int pageSize) throws Exception { 
			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 
			try { 
				LibBranchDAO lib = new LibBranchDAO(conn); 
				List<LibBranch> branches = lib.readAll(pageNo,pageSize); 
				conn.commit(); 
				return branches; 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return null; 
			} finally { 
				conn.close(); 
			} 
		} 
		
		public List<LibBranch> searchLibBranch(String searchString) throws Exception{
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			LibBranchDAO libdao = new LibBranchDAO(conn);
			return libdao.readByLibBranchName(searchString);
		}


		/***********************************END OF LIBRARY SECTION*******************************************************/
		
}

	
	


