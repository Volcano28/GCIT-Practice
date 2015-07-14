<%@include file="include.html"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	background-image: url("template_files/Screen-Shot-2014-08-30-at-3.40.49-PM-800x500.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}
</style>
</head>
<body>

	<h2>Hello Admin - Welcome to GCIT Library Management System</h2>
	${result}
	<ul class="media-list">
		<li class="media"><a class="pull-left" href="/viewBooks.jsp">
				<img class="media-object" src="template_files/openb.jpg"
				alt="Generic placeholder image">
		</a>
			<div class="media-body">
				<h2 class="media-heading">
					<strong>Book Section</strong>
				</h2>
				<p>
					<font size="4" color="white">This is the book section. If Admin clicks on the
					picture or the botton, Admin can view, edit or delete a book or
					books from the library.</font>
				</p>
				<a href="addBook.jsp" class="btn btn-default">Click here to a
					book to the library</a><br /> <a href="viewBooks.jsp"
					class="btn btn-default">Click here to check all books</a><br /> <br />
				<!-- Nested media object -->
				<div class="media">
					<a class="pull-left" href="/viewAuthors.jsp"> <img
						class="media-object" src="template_files/tolkien.jpg"
						alt="Generic placeholder image">
					</a>
					<div class="media-body">
						<h2 class="media-heading">
							<strong>Author Section</strong>
						</h2>
						<p>
							<font size="4" color="white">This is the author section. If Admin clicks on
								the picture or the botton, Admin can view, edit or delete an
								author or authors from the library.</font>
						</p>
						<a href="addAuthor.jsp" class="btn btn-default">Click here to
							a Author to the library</a><br /> <a href="viewAuthors.jsp"
							class="btn btn-default">Click here to check all Authors</a><br />
						<!-- Nested media object -->
						<div class="media">
							<a class="pull-left" href="#"> <img class="media-object"
								src="template_files/boks.jpg" alt="Generic placeholder image">
							</a>
							<div class="media-body">
								<h2 class="media-heading">
									<strong>Publisher Section</strong>
								</h2>
								<p>
									<font size="4" color="white">This is the publisher section. If Admin clicks
										on the picture or the botton, Admin can view, edit or delete
										an publisher or publishers from the library.</font>
								</p>
								<a href="addPublisher.jsp" class="btn btn-default">Click here to add a 
									Publisher</a><br /> <a href="viewPublishers.jsp"
									class="btn btn-default">Click here to check all the publishers</a><br />
							</div>
						</div>
					</div>
				</div>
				<!-- Nested media object -->
				<div class="media">
					<a class="pull-left" href="#"> <img class="media-object"
						src="template_files/genrebok1.jpg" alt="Generic placeholder image">
					</a>
					<div class="media-body">
						<h2 class="media-heading">
							<strong>Genre Section</strong>
						</h2>
						<p>
							<font size="4" color="white">This is the genre section. If Admin clicks on
								the picture or the botton, Admin can view, edit or delete an
								genre or genres from the library.</font>
						</p>
						<a href="addGenre.jsp" class="btn btn-default">Click here to add a 
									Genre</a><br /> <a
							href="viewGenres.jsp" class="btn btn-default">Click here to check all the Genres</a><br />
					</div>
				</div>
			</div></li>
		<li class="media"><a class="pull-right" href="#"> <img
				class="media-object" src="template_files/bok.jpg"
				alt="Generic placeholder image">
		</a>
			<div class="media-body">
				<h4 class="media-heading">
					<strong>Branch Section</strong>
				</h4>
				<p>
					<font size="4" color="white">This is the Library Branch section. If Admin
						clicks on the picture or the botton, Admin can view, edit or
						delete a branch or branches from the library.</font>
				</p>
				<a href="addBranch.jsp" class="btn btn-default">Click here to Add a Branch</a><br /> <a
					href="viewBranches.jsp" class="btn btn-default">Click here to check all the Branches</a><br />
			</div></li>
	</ul>
</body>
</html>



