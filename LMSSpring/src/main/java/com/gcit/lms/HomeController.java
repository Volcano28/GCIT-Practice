package com.gcit.lms;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.domain.Publisher;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	@Autowired
	AuthorDAO authorDAO;
	
	@Autowired
	BookDAO bookDAO;
	
	@Autowired
	PublisherDAO publisherDAO;
	
	@Autowired
	GenreDAO genreDAO;
	
	@Autowired
	LibBranchDAO libraryDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
//	
//	@RequestMapping(value="/author/get", method={RequestMethod.GET, RequestMethod.POST} , produces="application/json")
//	public List<Author> getAuthors(){
//		try {
//			return authorDAO.readAll();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		
//	}
	
	//****************************************Book Controller******************************************************//
	
	@RequestMapping(value="/book/get", method={RequestMethod.GET, RequestMethod.POST} , produces="application/json")
	public List<Book> getBooks(){
		try {
			return bookDAO.readAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Transactional
	@RequestMapping(value="/book/add", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
	public Book addBook(@RequestBody Book book){
		try {
			bookDAO.addBook(book);
			return book;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Transactional
	@RequestMapping(value="/book/update", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
	public Book updateBook(@RequestBody Book book){
		try {
			bookDAO.updateBook(book);
			return book;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value="/book/search/", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
	public List<Book> searchAllBook( ){
		try {
			return bookDAO.search("", 0, 0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}return null;
		
	}	
	
	@RequestMapping(value="/book/search/{title}", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
	public List<Book> searchBook( @PathVariable String title){
		try {
			return bookDAO.search(title, 0, 0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}return null;
	
	}
	@Transactional
	@RequestMapping(value="/book/delete", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
	public Book deleteBook(@RequestBody Book book){
		try {
			bookDAO.removeBook(book);
			return book;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value="/book/getOne", method={RequestMethod.GET, RequestMethod.POST} , produces="application/json", consumes="application/json")
	public Book getOneBook(@RequestBody Book book){
		try {
			return bookDAO.readOne(book.getBookId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//**************************************End Of Book***************************************************************//
	//**************************************Author Home Controller ***************************************************//
	@Transactional
	@RequestMapping(value="/author/add", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
	public Author addAuthor(@RequestBody Author author){
		try {
			authorDAO.addAuthor(author);
			return author;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Transactional
	@RequestMapping(value="/author/update", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json", produces="application/json")
	public Author updateAuthor(@RequestBody Author author){
		try {
			authorDAO.updateAuthor(author);
			return author;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	@Transactional
	@RequestMapping(value="/author/delete", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
	public Author deleteAuthor(@RequestBody Author author){
		try {
			authorDAO.removeAuthor(author);
			return author;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value="/author/search/{authorName}", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
	public List<Author> searchAuthor( @PathVariable String authorName){
		try {
			return authorDAO.search(authorName, 0, 0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}return null;
		
	}	
	
	@RequestMapping(value="/author/search/", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
	public List<Author> searchAll( ){
		try {
			return authorDAO.search("", 0, 0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}return null;
		
	}	
	
	@RequestMapping(value="/author/searchAllAuthors/", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
	public List<Author> searchAllAuthors( ){
		try {
			return authorDAO.readAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}return null;
		
	}	
	
	@RequestMapping(value="/genre/searchAllGenres/", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
	public List<Genre> searchAllGenres( ){
		try {
			return genreDAO.readAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}return null;
		
	}
	
	@RequestMapping(value="/publisher/searchAllPublishers/", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
	public List<Publisher> searchAllPublishers( ){
		try {
			return publisherDAO.readAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}return null;
		
	}
	

	@RequestMapping(value="/author/getOne", method={RequestMethod.GET, RequestMethod.POST} , produces="application/json", consumes="application/json")
	public Author getOneAuthor(@RequestBody Author author){
		try {
			return authorDAO.readOne(author.getAuthorId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	///////*******************************************************End Of Author***************************************************//
	
	//**************************************Publisher Home Controller ***************************************************//
		@Transactional
		@RequestMapping(value="/publisher/add", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
		public Publisher addPublisher(@RequestBody Publisher publisher){
			try {
				publisherDAO.addPublisher(publisher);
				return publisher;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		@Transactional
		@RequestMapping(value="/publisher/update", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json", produces="application/json")
		public Publisher updatePublisher(@RequestBody Publisher publisher){
			try {
				publisherDAO.updatePublisher(publisher);
				return publisher;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}	
		}
		
		@Transactional
		@RequestMapping(value="/publisher/delete", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
		public Publisher deletePublisher(@RequestBody Publisher publisher){
			try {
				publisherDAO.removePublisher(publisher);
				return publisher;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		@RequestMapping(value="/publisher/search/{publisherName}", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
		public List<Publisher> searchPublisher( @PathVariable String publisherName){
			try {
				return publisherDAO.search(publisherName, 0, 0);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}return null;
			
		}	
		
		@RequestMapping(value="/publisher/search/", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
		public List<Publisher> searchAllPub( ){
			try {
				return publisherDAO.search("", 0, 0);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}return null;
			
		}	
		

		@RequestMapping(value="/publisher/getOne", method={RequestMethod.GET, RequestMethod.POST} , produces="application/json", consumes="application/json")
		public Publisher getOnePublisher(@RequestBody Publisher publisher){
			try {
				return publisherDAO.readOne(publisher.getPublisherId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		
		//******************************************************************************End Of Publisher***********************************************************//
//***************************************************************************Genre*****************************************************//
		@Transactional
		@RequestMapping(value="/genre/add", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
		public Genre addGenre(@RequestBody Genre genre) throws Exception{
			try {
				genreDAO.addGenre(genre);
				return genre;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		@Transactional
		@RequestMapping(value="/genre/update", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json", produces="application/json")
		public Genre updateGenre(@RequestBody Genre genre) throws Exception{
			try {
				genreDAO.updateGenre(genre);
				return genre;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}	
		}
		
		@Transactional
		@RequestMapping(value="/genre/delete", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
		public Genre deleteGenre(@RequestBody Genre genre) throws Exception{
			try {
				genreDAO.removeGenre(genre);
				return genre;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		@RequestMapping(value="/genre/search/{genre_name}", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
		public List<Genre> searchGenre( @PathVariable String genre_name){
			try {
				return genreDAO.search(genre_name, 0, 0);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}return null;
			
		}	
		
		@RequestMapping(value="/genre/search/", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
		public List<Genre> searchAllGen( ){
			try {
				return genreDAO.search("", 0, 0);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}return null;
			
		}	
		

		@RequestMapping(value="/genre/getOne", method={RequestMethod.GET, RequestMethod.POST} , produces="application/json", consumes="application/json")
		public Genre getOneGenre(@RequestBody Genre genre) throws Exception{
			try {
				return genreDAO.readOne(genre.getGenre_id());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		///////*******************************************************End Of Genre***************************************************//
		
		//**************************************Library Home Controller ***************************************************//
				@Transactional
				@RequestMapping(value="/libraryBranch/add", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
				public LibraryBranch addLibraryBranch(@RequestBody LibraryBranch libraryBranch){
					try {
						libraryDAO.addLibraryBranch(libraryBranch);
						return libraryBranch;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
					
				}
				
				@Transactional
				@RequestMapping(value="/libraryBranch/update", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json", produces="application/json")
				public LibraryBranch updateLibraryBranch(@RequestBody LibraryBranch libraryBranch){
					try {
						libraryDAO.updateLibraryBranch(libraryBranch);
						return libraryBranch;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}	
				}
				
				@Transactional
				@RequestMapping(value="/libraryBranch/delete", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
				public LibraryBranch deleteLibraryBranch(@RequestBody LibraryBranch libraryBranch){
					try {
						libraryDAO.removeLibraryBranch(libraryBranch);
						return libraryBranch;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
					
				}
				
				@RequestMapping(value="/libraryBranch/search/{branchName}", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
				public List<LibraryBranch> searchLibraryBranch( @PathVariable String branchName){
					try {
						return libraryDAO.search(branchName, 0, 0);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}return null;
					
				}	
				
				@RequestMapping(value="/libraryBranch/search/", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json")
				public List<LibraryBranch> searchAllLib( ){
					try {
						return libraryDAO.search("", 0, 0);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}return null;
					
				}	
				

				@RequestMapping(value="/libraryBranch/getOne", method={RequestMethod.GET, RequestMethod.POST} , produces="application/json", consumes="application/json")
				public LibraryBranch getOneLibraryBranch(@RequestBody LibraryBranch libraryBranch){
					try {
						return libraryDAO.readOne(libraryBranch.getBranchId());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				}
				
				
				//**************************************************End of Library Branch****************************************//
		
		
}
