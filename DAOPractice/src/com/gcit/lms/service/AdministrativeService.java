package com.gcit.lms.service;

import java.sql.Connection;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.BookLoans;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibBranch;
import com.gcit.lms.domain.Publisher;

public class AdministrativeService {

	/***********************************AUTHOR SECTION*******************************************************/


	public void createAuthor(Author author) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if (author == null || author.getAuthorName() == null
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

	public void UpdateAuthor(Author author) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(author == null){ 
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

	public void DeleteAuthor(Author author) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(author == null){ 
				throw new Exception("The Author parameter cant be null"); 
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

	public Author ListOneAuthor(int authorId) throws Exception {

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

	public List<Author> ListAuthors() throws Exception { 
		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		try { 
			AuthorDAO adao = new AuthorDAO(conn); 
			List<Author> authors = adao.readAll(); 
			conn.commit();
			return authors; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} finally { 
			conn.close(); 
		} 
	} 


	/***********************************END OF AUTHOR SECTION*******************************************************/

	/**************************************GENRE SECTION*********************************************************************/

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
	public void UpdateGenre(Genre genre) throws Exception {
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

	public void DeleteGenre(Genre genre) throws Exception {
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

	public Genre ListOneGenre(int genre_id) throws Exception {

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

	public List<Genre> ListGenres() throws Exception { 
		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		try { 
			GenreDAO gdao = new GenreDAO(conn); 
			List<Genre> genres = gdao.readAll(); 
			conn.commit();
			return genres; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} finally { 
			conn.close(); 
		} 
	} 


	/***********************************END OF GENRE SECTION*******************************************************/

	/**************************************PUBLISHER SECTION*********************************************************************/
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
	public void UpdatePublisher(Publisher publisher) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(publisher == null){ 
				throw new Exception("The Publisher cannot be null"); 
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
	public void DeletePublisher(Publisher publisher) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(publisher == null){ 
				throw new Exception("The Publisher cannot be null"); 
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

	public Publisher ListOnePublisher(int PublisherId) throws Exception {

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

	public List<Publisher> ListPublisher() throws Exception { 
		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		try { 
			PublisherDAO pdao = new PublisherDAO(conn); 
			List<Publisher> publishers = pdao.readAll(); 
			conn.commit();
			return publishers; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} finally { 
			conn.close(); 
		} 
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
	public void UpdateBook(Book book) throws Exception {
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
	public void DeleteBook(Book book) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(book == null){ 
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

	public Book ListOneBook(int bookId) throws Exception {

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

	public List<Book> ListBook() throws Exception { 
		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		try { 
			BookDAO bdao = new BookDAO(conn); 
			List<Book> books = bdao.readAll(); 
			conn.commit(); 
			return books; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} finally { 
			conn.close(); 
		} 
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

	public void DeleteLibBranch(LibBranch branch) throws Exception {
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

	public LibBranch ListOneBranche(int branchId) throws Exception {

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


	/***********************************END OF LIBRARY SECTION*******************************************************/
	/*************************************** COPIES SECTION*********************************************************************/

	public void createBorrower(Borrower bor) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if (bor == null 
					|| bor.getBorrowerName() == null
					|| bor.getBorrowerName().length() == 0
					|| bor.getBorrowerName().length() > 45 
					|| bor.getBorrowerAddress() == null
					|| bor.getBorrowerAddress().length() == 0
					|| bor.getBorrowerAddress().length() > 45
					|| bor.getBorrowerPhone() == null
					|| bor.getBorrowerPhone().length() == 0
					|| bor.getBorrowerPhone().length() > 45) {
				throw new Exception(
						"Borrower Name, Address, Phone cannot be empty or more than 45 Chars");
			} else {
				BorrowerDAO bdao = new BorrowerDAO(conn);
				bdao.create(bor);
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

	public void UpdateBorrower(Borrower borrow) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(borrow == null || borrow.getCardNo() == 0){ 
				throw new Exception("The Borrower cannot be null or CardNo can not be 0 "); 
			}else{
				BorrowerDAO bordao = new BorrowerDAO(conn);
				bordao.update(borrow); 
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

	public void DeleteBorrower(Borrower bor) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(bor == null){ 
				throw new Exception("The Borrower cannot be null"); 
			}else{
				BorrowerDAO bordao = new BorrowerDAO(conn);
				bordao.delete(bor); 
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

	public Borrower ListOneBorrower(int cardNo) throws Exception {

		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 

		try {

			BorrowerDAO bdao = new BorrowerDAO(conn);
			Borrower bor = bdao.readOne(cardNo);
			return bor;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally{
			conn.close();
		}

	}

	public List<Borrower> ListBorrower() throws Exception { 
		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		try { 
			BorrowerDAO bdao = new BorrowerDAO(conn); 
			List<Borrower> bor = bdao.readAll(); 
			conn.commit(); 
			return bor; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} finally { 
			conn.close(); 
		} 
	} 


	/***********************************END OF BORROWER SECTION*******************************************************/
	/*************************************** LOANS SECTION*********************************************************************/

	public void createBookLoans(BookLoans bookloan) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(bookloan == null || bookloan.getDateOut() == null 
					|| bookloan.getDueDate() == null 
					|| bookloan.getDateOut().after(bookloan.getDueDate())
					) { 
						throw new Exception( 
								"The due date is expired "); 
					} else {
						BookLoansDAO bldao = new BookLoansDAO(conn);
						bldao.create(bookloan);
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

	public void UpdateBookloans(BookLoans bookloans) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(bookloans == null || bookloans.getCardNo() == 0 || bookloans.getBookId() == 0 || bookloans.getBranchId() == 0 ){ 
				throw new Exception("The Borrower cannot be null or CardNo can not be 0 "); 
			}else{
				BookLoansDAO bookldao = new BookLoansDAO(conn);
				bookldao.update(bookloans); 
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

	public void DeleteBookLoans(BookLoans bookL) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(bookL == null){ 
				throw new Exception("The Borrower cannot be null"); 
			}else{
				BookLoansDAO bookLdao = new BookLoansDAO(conn);
				bookLdao.delete(bookL); 
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
	/******************************************************************************************/

	public BookLoans ListOneBookLoan(int bookId, int branchId, int cardNo) throws Exception {

		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 

		try {

			BookLoansDAO bldao = new BookLoansDAO(conn);
			BookLoans bookloans = bldao.readOne(bookId, branchId, cardNo);
			return bookloans;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally{
			conn.close();
		}

	}

	public List<BookLoans> ListBookLoans() throws Exception { 
		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		try { 
			BookLoansDAO bldao = new BookLoansDAO(conn); 
			List<BookLoans> bookloanslist = bldao.readAll(); 
			conn.commit(); 
			return bookloanslist; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} finally { 
			conn.close(); 
		} 
	} 

}
