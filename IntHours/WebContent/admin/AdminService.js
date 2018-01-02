app.factory('AdminService', function($http, $q, $rootScope) {
	console.log("Admin Service")
	var BASE_URL = 'http://localhost:7070/IHours';

	var blogPostService = {}
	blogPostService.getBlogPost=function(id)
	{
		return $http.get("http://localhost:7070/IHours/getblogpost/"+id)
		}

	blogPostService.blogsWaitingForApproval = function() {
		return $http.get(BASE_URL + '/listofblogs/0')
	}

	blogPostService.blogsApproved = function() {
		return $http.get(BASE_URL + '/listofblogs/1')
	}

	 blogPostService.updateApproval = function(blogpost) {
		return $http.put(BASE_URL + "/updateApproval", blogpost)
	}

	return blogPostService;

});