<%@page import="com.gcit.lms.domain.LibBranch"%>
<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.LibBranch"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<LibBranch> branches = null;
	if (request.getAttribute("branches") != null) {
		branches = (List<LibBranch>) request.getAttribute("branches");
	} else {
		branches = adminService.listBranches(0, 10);

	}
%>
<%@include file="include.html"%>
<script>
	function searchLibBranches(){
		$.ajax({
			  url: "searchLibBranches",
			  data: {
				  searchString: $('#searchString').val()
			  }
			}).done(function(data) {
				$('#libBranchesTable').html(data);
			});
	}
</script>
	
${result }



   <form action="searchLibBranches" method="post" class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search" id="searchString" name="searchString" >
           <input
		type="button"  value="Submit "class="btn btn-default" onclick="javascript:searchLibBranches(); ">
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
		<th>Edit Branch</th>
		<th>Delete Branch</th>
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

		<td><button type="button" class="btn btn-md btn-success"
				data-toggle="modal" data-target="#myModal1"
				href="editBranch.jsp?branchId=<%=lib.getLibraryBranchId()%>">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger"
				onclick="javascript:location.href='deleteLibBranch?branchId=<%=lib.getLibraryBranchId()%>';">Delete</button></td>
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