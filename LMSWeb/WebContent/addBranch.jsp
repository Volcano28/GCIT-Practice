<%@include file="include.html"%>
<form action="addBranch" method="post">
	<body>
		<h2>Hello Admin - Enter Library Branch Details</h2>
		<table class="table">
			<tr>
				<td>Enter Branch Name:</td>
				<td><input type="text" name="libraryBranchName" /></td>
			</tr>
			<tr>
				<td>Enter Branch Address:</td>
				<td><input type="text" name="libraryBranchAddress" /></td>
			</tr>
		</table>
		<input type="submit">
	</body>
</form>
