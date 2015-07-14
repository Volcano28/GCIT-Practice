<%@page import="com.gcit.lms.domain.LibBranch"%>
<%@page import="com.gcit.lms.service.LibraryService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.LibBranch"%>
<%
	LibraryService libraryService = new LibraryService();
 String branchId = (String) request.getAttribute("branchId");
 LibBranch lib = libraryService.ListOneBranch(Integer.parseInt(branchId)); 
 
%>
<%@include file="include.html"%>

<table class="table" id="libBranchesTable">
	<tr>
		<th>Branch ID</th>
		<th>Branch Name</th>
		<th>Branch Address</th>
	</tr>
	<%
	{
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
				href="editBranch.jsp?branchId=<%=lib.getLibraryBranchId()%>">Update the Details</button></td>
	</tr>
	
	<td><a onclick="javascript:location.href='selectBooks?branchId=<%=branchId%>';">Add copies of Book to the Branch</a></td></td>
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