app.factory('RegisterService',function($http)
{
    var userService={}
    
    userService.registerUser = registerUser;
  
    

    function registerUser(user)
    {
    	console.log("registration start")
        return $http.post("http://localhost:7070/IHours/registration",user)
        console.log(user);
        console.log("Registration done")
    }
    
 

    
return userService;
})