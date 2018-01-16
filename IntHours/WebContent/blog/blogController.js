app.controller('BlogPostController',function($scope,BlogPostService,$location,$routeParams){
	
	var id=$routeParams.id
	$scope.isRejected=false;
	$scope.showComment=false;
	
	//select * from blogpost_s180133 where id=?
	BlogPostService.getBlogPost(id).then(function(response){
		$scope.blogPost=response.data
	},function(response){
		if(response.status==401){
			$location.path('/login')
		}
	})
	
	$scope.message=''
	$scope.saveBlogPost=function(){
		BlogPostService.saveBlog($scope.blogPost).then(function(response){
			alert("Blog post inserted successfully and waiting for approval");
			$location.path('/viewblog')
		},function(response){
			$scope.message=response.data.message
			if(response.status==401){
		$location.path('/login')
			}
			if(response.status==500){
				$location.path('/saveblogpost')
		    }
		})
	}
	
  $scope.blogsApproved=BlogPostService.blogsApproved().then(function(response){
	  $scope.blogsApproved=response.data;
  },function(response){
	  console.log(response.status)
  })
  
  
 
	
	//select * from blogpostlikes_s180133 where blogpost_id=? and user_username=?
  
	BlogPostService.userLikes	(id).then(function(response){
		if(response.data=='')//user has not yet liked the blogpost
			$scope.liked=false
			else
				$scope.liked=true//user has liked the blogpost already
			alert($scope.liked)
	},function(response){
		if(response.status==401){
			$location.path('/login')
		}
	})
	
	
	$scope.showRejectionTxt=function(val){
		$scope.isRejected=val
	}
	
	$scope.updateBlogPost=function(){
		BlogPostService.updateBlogPost($scope.blogPost,$scope.rejectionReason).then(function(response){
        $location.path('/getblogs')			
		},function(response){
			if(response.status==401){
				$location.path('/login')
			}
			if(response.status==500){
				alert(response.data)
				$scope.error=response.data
			}
		})
	}

	
	$scope.updateLikes=function(){
		BlogPostService.updateLikes($scope.blogPost).then(function(response){
			$scope.blogPost=response.data;
			$scope.liked=!$scope.liked;
		},function(response){
			if(response.status==401){
				$location.path('/login')
			}
		})
	}
	
	$scope.addComment=function(){
		if($scope.body==undefined){
			alert('Please enter comment')
		}
		else
			BlogPostService.addComment($scope.body,id).then(function(response){
			alert(response.status)
			$scope.body=''
			$scope.blogPost=response.data //blogPost with lists of blogcomments
		},function(response){
			if(response.status==401){
				$location.path('/login')
			}
			if(response.status==500){
				$scope.error=response.data
			}
		})	
	}
	
	$scope.showComments=function(){
		alert('show comments')
		$scope.showComment=!$scope.showComment
	}	


  
  
})