<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.LibBranch"%>
<%AdministrativeService adminService = new AdministrativeService();
 String branchId = request.getParameter("branchId");
 LibBranch libranch = adminService.ListOneBranch(Integer.parseInt(branchId));
%>
<div class="modal-body">
<form action="editBranch" method="post">
			Enter Branch Name: <input type="text" name="branchName" value=<%=libranch.getLibraryBranchName()%>>
			Enter Branch Address: <input type="text" name="branchAddress" value=<%=libranch.getLibraryBranchAddress()%>>
			<input type="hidden" name="branchId" value=<%=libranch.getLibraryBranchId() %>>
		<input type="submit"/>
</form>
</div>