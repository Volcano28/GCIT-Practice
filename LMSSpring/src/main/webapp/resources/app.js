var libraryModule = angular.module('libraryApp', [ 'ngRoute', 'ngCookies', 'ui.bootstrap' ]);

libraryModule.config([ "$routeProvider", function($routeProvider) {
	return $routeProvider.when("/", {
		redirectTo : "/home"
	}).when("/home", {
		templateUrl : "home.html"
	}).when("/listAuthors", {
		templateUrl : "listAuthors.html"
	}).when("/listPublishers", {
		templateUrl : "listPublishers.html"
	}).when("/listBranches", {
		templateUrl : "listBranches.html"
	}).when("/listBooks", {
		templateUrl : "listBooks.html"
	}).when("/listGenres", {
		templateUrl : "listGenres.html"
	})
} ]);


//***********************************************Author Controller*******************************************************//

libraryModule.controller('authorCtrl', function($scope, $http, $cookieStore, $modal, $log) {
	// get all authors and display initially
	$scope.getAuthors = function getAuthors(){
		$http.get('http://localhost:8080/lms/author/search/')
		.success(
				function(data) {
					$scope.authors = data;
					console.log($scope.authors);
		
				    $scope.totalItems = $scope.authors.length;
				    $scope.$watch('currentPage + itemsPerPage', function() {
				      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
				        end = begin + $scope.itemsPerPage;
	
				      $scope.filteredAuthors = $scope.authors.slice(begin, end);
				   
						  });
				}
		);}
	$scope.getAuthors();

	$scope.addAuthor = function addAuthor() {
//		if($scope.addAuthorFrm.$valid){

		$http.post('http://localhost:8080/lms/author/add/', $scope.authorToInsert).
		success(function(data) {
			alert('Author Added');
			//$scope.authors = data;	
			//showAddAuthor();
			$scope.getAuthors();
		});
	}

	$scope.deleteAuthor = function deleteAuthor(author) {

		$http.post('http://localhost:8080/lms/author/delete/', author).
		success(function(data){
			alert('Author Deleted');
			$scope.getAuthors();
		});
	}

	$scope.updateAuthor = function updateAuthor(author) {

		$http.post('http://localhost:8080/lms/author/update/', author).
		success(function(data){
			alert('Author Updated');
			$scope.getAuthors();
		});
	}
	

	$scope.searchAuthor = function searchAuthor() {

		$http.get('http://localhost:8080/lms/author/search/'+ $scope.authorToSearchName).
		success(function(data){
			$scope.authors = data
		    $scope.totalItems = $scope.authors.length;
		    $scope.$watch('currentPage + itemsPerPage', function() {
		      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
		        end = begin + $scope.itemsPerPage;

		      $scope.filteredAuthors = $scope.authors.slice(begin, end);
		   
				  });
		});

	}
	
	

	$scope.openModal = function(author){
		
		$scope.copyAuthor= JSON.parse(JSON.stringify(author))
		
		$scope.open('lg', author)
		
		
	}
	
	

	$scope.animationsEnabled = true;

	$scope.open = function (size, author) {

		var modalInstance = $modal.open({
			animation: $scope.animationsEnabled,
			templateUrl: 'myModalContent.html',
			controller: 'ModalInstanceCtrl',
			size: size,
			resolve: {
				author: function () {
					return author;
				}
			}
		});

		modalInstance.result.then(function (author) {
			$scope.editedAuthor = author;
			$scope.updateAuthor(author)
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	$scope.toggleAnimation = function () {
		$scope.animationsEnabled = !$scope.animationsEnabled;
	};	
	
	  $scope.itemsPerPage = 5;
	  $scope.currentPage = 1;

	  // $scope.maxSize = 5;
	  // $scope.bigTotalItems = 175;
	  // $scope.bigCurrentPage = 1;

	  $scope.pageCount = function () {
	    return Math.ceil($scope.authors.length / $scope.itemsPerPage);
	  };


});



libraryModule.controller('ModalInstanceCtrl', function ($scope, $modalInstance, author) {

	$scope.author = author;
	
	$scope.ok = function () {
		$modalInstance.close($scope.author);
	};

	$scope.cancel = function () {
		
		$modalInstance.dismiss('cancel');
		
	};
});

//*********************************************End Of Author Contoller*********************************************************************************//

//***********************************************Publisher Controller*******************************************************//

libraryModule.controller('publisherCtrl', function($scope, $http, $cookieStore, $modal, $log) {
	// get all authors and display initially
	$scope.getPublishers = function getPublishers(){
		$http.get('http://localhost:8080/lms/publisher/search/')
		.success(
				function(data) {
					$scope.publishers = data;
					console.log($scope.publishers);
		
				    $scope.totalItems = $scope.publishers.length;
				    $scope.$watch('currentPage + itemsPerPage', function() {
				      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
				        end = begin + $scope.itemsPerPage;
	
				      $scope.filteredPublishers = $scope.publishers.slice(begin, end);
				   
						  });
				}
		);}
	$scope.getPublishers();

	$scope.addPublisher = function addPublisher() {
//		if($scope.addAuthorFrm.$valid){

		$http.post('http://localhost:8080/lms/publisher/add/', $scope.publisherToInsert).
		success(function(data) {
			alert('Publishers Added');
			//$scope.authors = data;	
			//showAddAuthor();
			$scope.getPublishers();
		});
	}

	$scope.deletePublisher = function deletePublisher(publisher) {

		$http.post('http://localhost:8080/lms/publisher/delete/', publisher).
		success(function(data){
			alert('Publisher Deleted');
			$scope.getPublishers();
		});
	}

	$scope.updatePublisher = function updatePublisher(publisher) {

		$http.post('http://localhost:8080/lms/publisher/update/', publisher).
		success(function(data){
			alert('publisher Updated');
			$scope.getPublishers();
		});
	}
	

	$scope.searchPublisher = function searcPublisher() {

		$http.get('http://localhost:8080/lms/publisher/search/'+ $scope.publisherToSearchName).
		success(function(data){
			$scope.publishers = data
		    $scope.totalItems = $scope.publishers.length;
		    $scope.$watch('currentPage + itemsPerPage', function() {
		      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
		        end = begin + $scope.itemsPerPage;

		      $scope.filteredPublishers = $scope.publishers.slice(begin, end);
		   
				  });
		});

	}
	
	

	$scope.openModal = function(publisher){
		
		$scope.copyPublisher= JSON.parse(JSON.stringify(publisher))
		
		$scope.open('lg', publisher)
		
		
	}
	
	

	$scope.animationsEnabled = true;

	$scope.open = function (size, publisher) {

		var modalInstance = $modal.open({
			animation: $scope.animationsEnabled,
			templateUrl: 'myModalContentpub.html',
			controller: 'ModalInstanceCtrlPub',
			size: size,
			resolve: {
				publisher: function () {
					return publisher;
				}
			}
		});

		modalInstance.result.then(function (publisher) {
			$scope.editedPublisher = publisher;
			$scope.updatePublisher(publisher)
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	$scope.toggleAnimation = function () {
		$scope.animationsEnabled = !$scope.animationsEnabled;
	};	
	
	  $scope.itemsPerPage = 5;
	  $scope.currentPage = 1;

	  // $scope.maxSize = 5;
	  // $scope.bigTotalItems = 175;
	  // $scope.bigCurrentPage = 1;

	  $scope.pageCount = function () {
	    return Math.ceil($scope.publishers.length / $scope.itemsPerPage);
	  };


});



libraryModule.controller('ModalInstanceCtrlPub', function ($scope, $modalInstance, publisher) {

	$scope.publisher = publisher;
	
	$scope.ok = function () {
		$modalInstance.close($scope.publisher);
	};

	$scope.cancel = function () {
		
		$modalInstance.dismiss('cancel');
		
	};
});

//*********************************************End Of Publisher Controller*********************************************************************************//

//********************************************************************************************************************************************************

//***********************************************Genre Controller*******************************************************//

libraryModule.controller('genreCtrl', function($scope, $http, $cookieStore, $modal, $log) {
	// get all authors and display initially
	$scope.getGenres = function getGenres(){
		$http.get('http://localhost:8080/lms/genre/search/')
		.success(
				function(data) {
					$scope.genres = data;
					console.log($scope.genres);
		
				    $scope.totalItems = $scope.genres.length;
				    $scope.$watch('currentPage + itemsPerPage', function() {
				      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
				        end = begin + $scope.itemsPerPage;
	
				      $scope.filteredGenres = $scope.genres.slice(begin, end);
				   
						  });
				}
		);}
	$scope.getGenres();

	$scope.addGenre = function addGenre() {
//		if($scope.addAuthorFrm.$valid){

		$http.post('http://localhost:8080/lms/genre/add/', $scope.genreToInsert).
		success(function(data) {
			alert('Genre Added');
			//$scope.authors = data;	
			//showAddAuthor();
			$scope.getGenres();
		});
	}

	$scope.deleteGenre = function deleteGenre(genre) {

		$http.post('http://localhost:8080/lms/genre/delete/', genre).
		success(function(data){
			alert('Genre Deleted');
			$scope.getGenres();
		});
	}

	$scope.updateGenre = function updateGenre(genre) {

		$http.post('http://localhost:8080/lms/genre/update/', genre).
		success(function(data){
			alert('Genre Updated');
			$scope.getGenres();
		});
	}
	

	$scope.searchGenre = function searchGenre() {

		$http.get('http://localhost:8080/lms/genre/search/'+ $scope.genreToSearchName).
		success(function(data){
			$scope.genres = data
		    $scope.totalItems = $scope.genres.length;
		    $scope.$watch('currentPage + itemsPerPage', function() {
		      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
		        end = begin + $scope.itemsPerPage;

		      $scope.filteredGenres = $scope.genres.slice(begin, end);
		   
				  });
		});

	}
	
	

	$scope.openModal = function(genre){
		
		$scope.copyGenre= JSON.parse(JSON.stringify(genre))
		
		$scope.open('lg', genre)
		
		
	}
	
	

	$scope.animationsEnabled = true;

	$scope.open = function (size, genre) {

		var modalInstance = $modal.open({
			animation: $scope.animationsEnabled,
			templateUrl: 'myModalContentGEN.html',
			controller: 'ModalInstanceCtrlGEN',
			size: size,
			resolve: {
				genre: function () {
					return genre;
				}
			}
		});

		modalInstance.result.then(function (genre) {
			$scope.editedGenre = genre;
			$scope.updateGenre(genre)
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	$scope.toggleAnimation = function () {
		$scope.animationsEnabled = !$scope.animationsEnabled;
	};	
	
	  $scope.itemsPerPage = 5;
	  $scope.currentPage = 1;

	  $scope.pageCount = function () {
	    return Math.ceil($scope.genres.length / $scope.itemsPerPage);
	  };


});



libraryModule.controller('ModalInstanceCtrlGEN', function ($scope, $modalInstance, genre) {

	$scope.genre = genre;
	
	$scope.ok = function () {
		$modalInstance.close($scope.genre);
	};

	$scope.cancel = function () {
		
		$modalInstance.dismiss('cancel');
		
	};
});

//*********************************************End Of Genre Controller*********************************************************************************//
//***********************************************Book Controller*******************************************************//

libraryModule.controller('bookCtrl', function($scope, $http, $cookieStore, $modal, $log) {
	// get all authors and display initially
	$scope.getBooks = function getBooks(){
		$http.get('http://localhost:8080/lms/book/search/')
		.success(
				function(data) {
					$scope.books = data;
					console.log($scope.books);
		
				    $scope.totalItems = $scope.books.length;
				    $scope.$watch('currentPage + itemsPerPage', function() {
				      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
				        end = begin + $scope.itemsPerPage;
	
				      $scope.filteredBooks = $scope.books.slice(begin, end);
				   
						  });
				}
		);}
	$scope.getBooks();

	$scope.addBook = function addBook() {
//		if($scope.addAuthorFrm.$valid){

		$http.post('http://localhost:8080/lms/book/add/', $scope.bookToInsert).
		success(function(data) {
			alert('Book Added');
			$scope.getBooks();
		});
	}
	
	
	$scope.getAuthors = function getAuthors() {
		
		$http.get('http://localhost:8080/lms/author/searchAllAuthors/').
		success(function(data) {
			$scope.authors = data;	
			
		})}
$scope.getGenres = function getGenres() {
		
		$http.get('http://localhost:8080/lms/genre/searchAllGenres/').
		success(function(data) {
			$scope.genres = data;	
			});
	}

$scope.getPublishers = function getPublishers() {
	
	$http.get('http://localhost:8080/lms/publisher/searchAllPublishers/').
	success(function(data) {
		$scope.publishers = data;	
		});
}
	
	$scope.getAuthors();
	$scope.getGenres();
	$scope.getPublishers();

	
	$scope.bookToInsert = {}
	$scope.bookToInsert.authors=[];
	$scope.bookToInsert.genres=[];
	  // toggle selection for a given employee by name
	
	  $scope.toggleSelection = function toggleSelection(author) {
	     var idx = $scope.CheckAuthorArray(author);
	     if (idx > -1) {
	       $scope.bookToInsert.authors.splice(idx, 1);
	     }
	     else {
	       $scope.bookToInsert.authors.push(author);
	     }}
		  
		  $scope.CheckAuthorArray = function CheckAuthorArray(author){
			  for(i=0; i< $scope.bookToInsert.authors.length; i++){
				 if (author.authorId == $scope.bookToInsert.authors[i].authorId)
					 return i
			  }
			return -1  
		 };
		 $scope.toggleSelectionGEN = function toggleSelectionGEN(genre) {
		     var idx = $scope.CheckGenreArray(genre);
		     if (idx > -1) {
		       $scope.bookToInsert.genres.splice(idx, 1);
		     }
		     else {
		       $scope.bookToInsert.genres.push(genre);
		     }}
			  
			  $scope.CheckGenreArray = function CheckGenreArray(genre){
				  for(i=0; i< $scope.bookToInsert.genres.length; i++){
					 if (genre.genre_id == $scope.bookToInsert.genres[i].genre_id)
						 return i
				  }
				return -1  
			 };     
	
	$scope.searchBook = function searchBook() {

		$http.get('http://localhost:8080/lms/book/search/'+ $scope.bookToSearchName).
		success(function(data){
			$scope.books = data
		    $scope.totalItems = $scope.books.length;
		    $scope.$watch('currentPage + itemsPerPage', function() {
		      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
		        end = begin + $scope.itemsPerPage;

		      $scope.filteredBooks = $scope.books.slice(begin, end);
		   
				  });
		});

	}
	
	$scope.updateBook = function updateBook(book) {

		$http.post('http://localhost:8080/lms/book/update/', book).
		success(function(data){
			alert('Book Updated');
			$scope.getBooks();
		});
	}
	
	
	$scope.deleteBook = function deleteBook(book) {

		$http.post('http://localhost:8080/lms/book/delete/', book).
		success(function(data){
			alert('Book Deleted');
			$scope.getBooks();
		});
	}
	
$scope.openModal = function(book){
		
		$scope.copyBook= JSON.parse(JSON.stringify(book))
		
		$scope.open('lg', book)
		
		
	}
	
	

	$scope.animationsEnabled = true;

	$scope.open = function (size, book) {

		var modalInstance = $modal.open({
			animation: $scope.animationsEnabled,
			templateUrl: 'myModalContentBOOK.html',
			controller: 'ModalInstanceCtrlBOOK',
			size: size,
			resolve: {
				book: function () {
					return book;
				}
			}
		});

		modalInstance.result.then(function (book) {
			$scope.editedBook = book;
			$scope.updateBook(book)
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	$scope.toggleAnimation = function () {
		$scope.animationsEnabled = !$scope.animationsEnabled;
	};	
	
	  $scope.itemsPerPage = 5;
	  $scope.currentPage = 1;
	  $scope.pageCount = function () {
	    return Math.ceil($scope.books.length / $scope.itemsPerPage);
	  };


});



libraryModule.controller('ModalInstanceCtrlBOOK', function ($scope, $modalInstance, book) {

	$scope.book = book;
	
	$scope.ok = function () {
		$modalInstance.close($scope.book);
	};

	$scope.cancel = function () {
		
		$modalInstance.dismiss('cancel');
		
	};
});

//*************************************************End of Book************************************************************//
//***********************************************Library Controller*******************************************************//

libraryModule.controller('libCtrl', function($scope, $http, $cookieStore, $modal, $log) {
	// get all authors and display initially
	$scope.getLibraryBranchs = function getLibraryBranchs(){
		$http.get('http://localhost:8080/lms/libraryBranch/search/')
		.success(
				function(data) {
					$scope.libraryBranchs = data;
					console.log($scope.libraryBranchs);
		
				    $scope.totalItems = $scope.libraryBranchs.length;
				    $scope.$watch('currentPage + itemsPerPage', function() {
				      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
				        end = begin + $scope.itemsPerPage;
	
				      $scope.filteredLibraryBranchs = $scope.libraryBranchs.slice(begin, end);
				   
						  });
				}
		);}
	$scope.getLibraryBranchs();

	$scope.addLibraryBranch = function addLibraryBranch() {
//		if($scope.addAuthorFrm.$valid){

		$http.post('http://localhost:8080/lms/libraryBranch/add/', $scope.libraryBranchToInsert).
		success(function(data) {
			alert('libraryBranchs Added');
			//$scope.authors = data;	
			//showAddAuthor();
			$scope.getLibraryBranchs();
		});
	}

	$scope.deleteLibraryBranch = function deleteLibraryBranch(libraryBranch) {

		$http.post('http://localhost:8080/lms/libraryBranch/delete/', libraryBranch).
		success(function(data){
			alert('Publisher Deleted');
			$scope.getLibraryBranch();
		});
	}

	$scope.updateLibraryBranch = function updateLibraryBranch(libraryBranch) {

		$http.post('http://localhost:8080/lms/libraryBranch/update/', libraryBranch).
		success(function(data){
			alert('libraryBranch Updated');
			$scope.getLibraryBranch();
		});
	}
	

	$scope.searchLibraryBranch = function searcLibraryBranch() {

		$http.get('http://localhost:8080/lms/libraryBranch/search/'+ $scope.libraryBranchToSearchName).
		success(function(data){
			$scope.libraryBranchs = data
		    $scope.totalItems = $scope.libraryBranchs.length;
		    $scope.$watch('currentPage + itemsPerPage', function() {
		      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
		        end = begin + $scope.itemsPerPage;

		      $scope.filteredLibraryBranchs = $scope.libraryBranchs.slice(begin, end);
		   
				  });
		});

	}
	
	

	$scope.openModal = function(libraryBranch){
		
		$scope.copyLibraryBranch= JSON.parse(JSON.stringify(libraryBranch))
		
		$scope.open('lg', libraryBranch)
	}
	
	

	$scope.animationsEnabled = true;

	$scope.open = function (size, libraryBranch) {

		var modalInstance = $modal.open({
			animation: $scope.animationsEnabled,
			templateUrl: 'myModalContentLib.html',
			controller: 'ModalInstanceCtrlLib',
			size: size,
			resolve: {
				libraryBranch: function () {
					return libraryBranch;
				}
			}
		});

		modalInstance.result.then(function (libraryBranch) {
			$scope.editedLibraryBranch = libraryBranch;
			$scope.updateLibraryBranch(libraryBranch)
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	$scope.toggleAnimation = function () {
		$scope.animationsEnabled = !$scope.animationsEnabled;
	};	
	
	  $scope.itemsPerPage = 5;
	  $scope.currentPage = 1;

	  // $scope.maxSize = 5;
	  // $scope.bigTotalItems = 175;
	  // $scope.bigCurrentPage = 1;

	  $scope.pageCount = function () {
	    return Math.ceil($scope.libraryBranchs.length / $scope.itemsPerPage);
	  };


});



libraryModule.controller('ModalInstanceCtrlLib', function ($scope, $modalInstance, libraryBranch) {

	$scope.libraryBranch = libraryBranch;
	
	$scope.ok = function () {
		$modalInstance.close($scope.libraryBranch);
	};

	$scope.cancel = function () {
		
		$modalInstance.dismiss('cancel');
		
	};
});


//*********************************************End Of Publisher Controller*********************************************************************************//


