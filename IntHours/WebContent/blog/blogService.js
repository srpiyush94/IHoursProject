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


blogPostService.addComment=function(blogComment){
    return $http.post("http://localhost:7070/IHours/addblogcomment",blogComment)
    }

blogPostService.getBlogComments=function(blogId){
    return $http.get("http://localhost:8081/7070/IHours/getblogcomments/"+blogId)
    }

return blogPostService;
})
