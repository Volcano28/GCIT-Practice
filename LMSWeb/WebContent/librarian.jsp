<%@page import="com.gcit.lms.domain.LibBranch"%>
<%@page import="com.gcit.lms.service.LibraryService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.LibBranch"%>
<%
	LibraryService libraryService = new LibraryService();
 String branchId = (String) request.getAttribute("branchId");
	List<LibBranch> branches = null;
	if (request.getAttribute("branches") != null) {
		branches = (List<LibBranch>) request.getAttribute("branches");
	} else {
		branches = libraryService.listBranches(0, 10);
	}
%>

<h2>Hello Librarian - Welcome to GCIT Library Management System</h2>
${result}
<h2>Select a branch:</h2>

<%@include file="include.html"%>
<script>
	function searchLibBranch(){
		$.ajax({
			  url: "searchLibraryBranch",
			  data: {
				  searchString: $('#searchString').val()
			  }
			}).done(function(data) {
				$('#libBranchesTable').html(data);
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
			onclick="javascript:searchLibBranch(); ">
	</div>
</form>


<nav>
	<ul class="pagination">
		<li><a href="pageLibBranches?pageNo=1">1</a></li>
		<li><a href="pageLibBranches?pageNo=2">2</a></li>
		<li><a href="pageLibBranches?pageNo=3">3</a></li>
		<li><a href="pageLibBranches?pageNo=4">4</a></li>
		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>

<table class="table" id="libBranchesTable">
	<tr>
		<th>Branch ID</th>
		<th>Branch Name</th>
		<th>Branch Address</th>
		<th>Select a Branch</th>
	</tr>
	<%
		for (LibBranch lib : branches) {
	%>
	<tr>
		<td>
			<%
				out.println(lib.getLibraryBranchId());
			%>
		</td>
		<td>
			<%
				out.println(lib.getLibraryBranchName());
			%>
		</td>
		<td>
			<%
				out.println(lib.getLibraryBranchAddress());
			%>
		</td>

	
		<td><button type="button" class="btn btn-md btn-success" onclick="javascript:location.href='selectLibraryBranch?branchId=<%=lib.getLibraryBranchId()%>';">Select</button></td> 
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