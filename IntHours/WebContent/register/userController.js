app.controller('RegisterController',function(RegisterService,$scope,$location,$rootScope,$cookieStore)
        {
    
    $scope.register=function()
   {
    	console.log("register....")
    	RegisterService.registerUser($scope.user).then (function(response)
        {
            $scope.message="Registered successfully..... Please Login...."
             console.log("done")
             $location.path('/login')
        }, function(response)
        {
            $scope.error=response.data;
            $location.path('/register')
        })
    
    }
    
 
})