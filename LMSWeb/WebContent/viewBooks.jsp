<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Book> books = null;
	if (request.getAttribute("books") != null) {
		books = (List<Book>) request.getAttribute("books");
	} else {
		books = adminService.listBooks(0, 10);

	}
%>
<%@include file="include.html"%>
<script>
	function searchBooks(){
		$.ajax({
			  url: "searchBooks",
			  data: {
				  searchString: $('#searchString').val()
			  }
			}).done(function(data) {
				$('#booksTable').html(data);
			});
	}

</script>
${result }
 <form action="searchLibBranches" method="post" class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search" id="searchString" name="searchString" >
           <input
		type="button"  value="Submit "class="btn btn-default" onclick="javascript:searchBooks(); ">
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

<table class="table" id="booksTable">
	<tr>
		<th>Book ID</th>
		<th>Title Name</th>
		<th>Author of The Book</th>
		<th>Publisher of the Book</th>
		<th>Genre of the Book</th>
		<th>Edit Book</th>
		<th>Delete Book</th>
	</tr>
	<%
		for (Book b: books) {
	%>
	<tr>
		<td>
			<%
				out.println(b.getBookId());
			%>
		</td>
		<td>
			<%
				out.println(b.getTitle());
			%>
		</td>
		<td>
			<%
				out.println(b.getPublisher().getPublisherName());
			%>
		</td>
		<td>
			<%
			for (Author a : b.getAuthors())
			{
			out.println(a.getAuthorName());
			}
			%>
		</td>
		<td>
			<%
			for (Genre g : b.getGenres())
			{
			out.println(g.getGenreName());
			}
			%>
		</td>

		<td><button type="button" class="btn btn-md btn-success"
				data-toggle="modal" data-target="#myModal1"
				href="editBook.jsp?bookId=<%=b.getBookId()%>">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger"
				onclick="javascript:location.href='deleteBook?bookId=<%=b.getBookId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>

<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>