app.factory('BlogPostService',function($http)
		{
var blogPostService={}

blogPostService.saveBlog=function(blogPost)
{
return $http.post("http://localhost:7070/IHours/createBlog", blogPost)
}

blogPostService.blogsApproved=function()
{
	return $http.get("http://localhost:7070/IHours/listofblogs/"+1)
	}


blogPostService.getBlogPost=function(id)
{
	return $http.get("http://localhost:7070/IHours/getblogpost/"+id)
	}


blogPostService.userLikes=function(id)
{
	return $http.get(BASE_URL + "/userLikes/"+id)
}	

blogPostService.updateLikes=function(blogPost){
	return $http.put(BASE_URL + "/updatelikes",blogPost);
}

blogPostService.addComment=function(body,id){
	//http://......../Good/12  -> @PathVariable
	return $http.post(BASE_URL + "/addcomment?body="+body +'&id='+id)
	//http://localhost:8080/project2middleware/addcomment?commentText='Thanks'&id=484 
}

return blogPostService;
})
