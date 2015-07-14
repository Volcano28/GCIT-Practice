<%@page import="com.gcit.lms.service.LibraryService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@page import="com.gcit.lms.domain.BookCopies"%>
<%
LibraryService ls = new LibraryService();  
String branchId = (String) request.getAttribute("branchId");
String noOfCopies = (String) request.getAttribute("noOfCopies");

	List<Book> books = null;
	if (request.getAttribute("books") != null) {
		books = (List<Book>) request.getAttribute("books");
	} else {
		books = ls.listBooks(0, 10);

	}
%>
<%@include file="include.html"%>
<script>
	function searchBooks() {
		$.ajax({
			url : "searchBooks",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#booksTable').html(data);
		});
	}
</script>
${result }
<form action="searchLibBranches" method="post"
	class="navbar-form navbar-left" role="search">
	<div class="form-group">
		<input type="text" class="form-control" placeholder="Search"
			id="searchString" name="searchString"> <input type="button"
			value="Submit " class="btn btn-default"
			onclick="javascript:searchBooks(); ">
	</div>
</form>

<nav>
	<ul class="pagination">
		<li><a href="pageBooks?pageNo=1">1</a></li>
		<li><a href="pageBooks?pageNo=2">2</a></li>
		<li><a href="pageBooks?pageNo=3">3</a></li>
		<li><a href="pageBooks?pageNo=4">4</a></li>
		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>


 
 <%@include file="include.html"%> 
 
 
 <table class="table"> 
 	<tr> 
 		<th>Book Title</th> 
 		<th>Select Book To Add Copies</th> 
 	</tr> 
 	<%for(Book b: books){ %> 
 	<tr> 
 		<td><%out.println(b.getTitle()); %></td> 
 		<td><a  href="editNumberOfCopies.jsp?bookId=<%=b.getBookId()%>&branchId=<%=branchId%>&noOfCopies=<%=noOfCopies%>"><button type="button" class="btn btn-md btn-success">Select</button></a></td> 
 	</tr> 
 	<%} %> 
 </table> 


<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content" id="whatever">
		...
		</div>
	</div>
</div>
