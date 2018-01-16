app.controller('ProfileController',function($scope,ProfileService,$location){

	$scope.userobj=ProfileService.getUserByUsername().then(function(response)
    		{
    	$scope.userobj=response.data
    },function(response){
    	console.log(response.status)
    })
    $scope.update=function()
    {
		ProfileService.updateUserProfile($scope.userobj).then(function(response)
    			
    			{
    		$scope.message="Updated the profile successfully"
    	},function(response){
    		console.log(response.data)
    	})
    }
})