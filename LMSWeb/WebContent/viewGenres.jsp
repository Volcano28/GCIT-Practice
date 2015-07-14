<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Genre> genres = null;
	if (request.getAttribute("genres") != null) {
		genres = (List<Genre>) request.getAttribute("genres");
	} else {
		genres = adminService.listGenres(0, 10);

	}
%>
<%@include file="include.html"%>
<script>
	function searchGenres(){
		$.ajax({
			  url: "searchGenres",
			  data: {
				  searchString: $('#searchString').val()
			  }
			}).done(function(data) {
				$('#genresTable').html(data);
			});
	}

</script>
${result }
 <form action="searchLibBranches" method="post" class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search" id="searchString" name="searchString" >
           <input
		type="button"  value="Submit "class="btn btn-default" onclick="javascript:searchGenres(); ">
        </div>
      </form>
<nav>
  <ul class="pagination">
    <li><a href="pageGenres?pageNo=1">1</a></li>
    <li><a href="pageGenres?pageNo=2">2</a></li>
    <li><a href="pageGenres?pageNo=3">3</a></li>
    <li><a href="pageGenres?pageNo=4">4</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

<table class="table" id="genresTable">
	<tr>
		<th>Genre ID</th>
		<th>Genre Name</th>
		<th>Edit Genre</th>
		<th>Delete Genre</th>
	</tr>
	<%
		for (Genre g : genres) {
	%>
	<tr>
		<td>
			<%
				out.println(g.getGenreId());
			%>
		</td>
		<td>
			<%
				out.println(g.getGenreName());
			%>
		</td>
		<td><button type="button" class="btn btn-md btn-success"
				data-toggle="modal" data-target="#myModal1"
				href="editGenre.jsp?genreId=<%=g.getGenreId()%>">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger"
				onclick="javascript:location.href='deleteGenre?genreId=<%=g.getGenreId()%>';">Delete</button></td>
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