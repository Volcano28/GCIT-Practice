package com.gcit.lms.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibBranch;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministrativeService;
import com.gcit.lms.service.LibraryService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/selectLibraryBranch","/addCopies", "/editBranch", "/selectBooks", "/editNoOfCopies","/searchLibraryBranch"})

public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {

		case "/selectLibraryBranch":
			selectLibraryBranch(request, response);
			break;
		case "/searchLibraryBranch":
			searchLibBranches(request, response);
			break;
		case "/selectBooks":
			selectBooks(request, response);
			break;

		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {

		case "/selectLibraryBranch":
			selectLibraryBranch(request, response);
			break;
		case "/editBranch": 
			editBranch(request, response);
			break;
		case "/selectBooks":
			selectBooks(request, response);
			break;
		case "/addCopies": 
			addCopies(request, response);
			break;
	
			



		default:
			break;
		}
	}

	private void pageLibBranches(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));

		try {
			List<LibBranch> libbranches = new AdministrativeService().listBranches(pageNo, 10);
			request.setAttribute("libbranches", libbranches);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/mainLibrarian.jsp");
		rd.forward(request, response);
	}

	private void searchLibBranches(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("searchString");
		try {
			List<LibBranch> libranches =  new AdministrativeService().searchLibBranch(searchString);
			request.setAttribute("libranches", libranches);
			StringBuilder str = new StringBuilder();
			str.append("<tr><th>Branch ID</th><th>Branch Name</th><th>Branch Adress</th><th>Select Branch</th></tr>");
			for(LibBranch lib: libranches){
				str.append("<tr><td>"+lib.getLibraryBranchId()+"</td><td>"+lib.getLibraryBranchName()+"</td><td>"+lib.getLibraryBranchAddress()+"</td><td><button type='button' class='btn btn-md btn-success' onclick='javascript:location.href="
						+ "'selectLibraryBranch?branchId="+lib.getLibraryBranchId()+"'>Select</button></td></tr>");
			}
			response.getWriter().write(str.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void selectLibraryBranch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 

		String branchId = request.getParameter("branchId"); 
		RequestDispatcher rd =getServletContext().getRequestDispatcher("/mainLibrarian.jsp"); 
		request.setAttribute("branchId",branchId); 
		rd.forward(request, response); 
	} 

	private void selectBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		String branchId = request.getParameter("branchId"); 
		RequestDispatcher rd =getServletContext().getRequestDispatcher("/selectBooks.jsp"); 
		request.setAttribute("branchId",branchId); 
		rd.forward(request, response); 
	} 


	private void editBranch(HttpServletRequest request,
			HttpServletResponse response) {
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		LibBranch lib = new LibBranch();
		lib.setLibraryBranchName(branchName);
		lib.setLibraryBranchAddress(branchAddress);
		lib.setLibraryBranchId(branchId);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.UpdateLibBranch(lib);
			request.setAttribute("result", "LibBranch updated Successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"LibBranch update failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/librarian.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addCopies(HttpServletRequest request, 
			HttpServletResponse response) { 
		String numbCopies = request.getParameter("noOfCopies"); 
		String branchId = request.getParameter("branchId"); 
		String bookId = request.getParameter("bookId"); 


		LibraryService ls = new LibraryService(); 

		try { 
			 
			ls.updateBookCopies(Integer.parseInt(branchId), Integer.parseInt(bookId), Integer.parseInt(numbCopies)); 
			request.setAttribute("result", "Library Branch updated Successfully"); 
			request.setAttribute("branchId", branchId); 


		} catch (Exception e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
			request.setAttribute("result", 
					"Copy update failed " + e.getMessage()); 
		} 
		RequestDispatcher rd = getServletContext().getRequestDispatcher( 
				"/mainLibrarian.jsp"); 
		try { 
			rd.forward(request, response); 
		} catch (ServletException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
	} 


}
