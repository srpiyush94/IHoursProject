app.controller('AdminController', function($scope, $location,AdminService, $rootScope, $http,$routeParams)
{
	console.log("Admin Controller")
	
var id;
	
	if(id==undefined)
		{
		
		id=$routeParams.id

		}
		
 	
	$scope.blogPost=AdminService.getBlogPost(id).then(function(response){
		$scope.blogPost=response.data;
	},function(response){
		console.log(response.status);
	})
	  $scope.blogsWaitingForApproval=AdminService.blogsWaitingForApproval().then(function(response){
	  $scope.blogsWaitingForApproval=response.data;
  },function(response){
	  console.log(response.status)
  })
  
  $scope.blogsApproved=AdminService.blogsApproved().then(function(response){
	  $scope.blogsApproved=response.data;
  },function(response){
	  console.log(response.status)
  })
     $scope.updateApproval=function(){
		console.log("Blog approval start")
		AdminService.updateApproval($scope.blogPost).then(function(response){
			console.log("Blog approved")
		  $location.path('/manageBlogs')
		},function(response){
			console.log(response.status);
			$location.path('/getBlogForApproval/'+id)
		})
	}
	    })