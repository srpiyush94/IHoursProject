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
    
 
/*
     function  getUserByUsername()
    {
        return $http.get("http://localhost:7070/IHours/getuserdetails")
    }
    
    userService.updateUserProfile=function(user)
    {
        return $http.put("http://localhost:7070/IHours/updateprofile",user)
    }*/
    
return userService;
})