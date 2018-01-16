/**
 * FriendController
 */
app.controller('FriendController',function($scope,$location,$routeParams,FriendService){
	function getAllSuggestedUsers()
	{
		FriendService.getAllSuggestedUsers().then(function(response){
			$scope.suggestedusers=response.data //A- (B U C) query
		},function(response)
		{
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.getListofMutualFriends=function(toId)
	{
		FriendService.getListofMutualFriends(toId).then(function(response)
		{
			$scope.mutualfriends=response.data
		},function(response)
		{
			if(response.status==401)
				$location.path('/login')			
		})
	}
				
		
	
	function getListofFriends()
	{
		FriendService.getListofFriends().then(function(response){
			$scope.friends=response.data
			
		},function(response)
		{
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	
	function getAllPendingRequests(){    //to find pending requests for logged in user
		FriendService.getAllPendingRequests().then(function(response){
			$scope.pendingRequests=response.data   //select * from friend where toId=? and status=P 
		},function(response)
		{
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.addFriendRequest=function(toId)
	{
		FriendService.addFriendRequest(toId).then(function(response) {
		alert('Friend Request has been sent successfully')	
		getAllSuggestedUsers()
		},function(response)
		{
			if(response.status==401)
				$location.path('/login')
		})
	}
	//pendingrequest-friend object with status P
	//update status char[ A / R ] from pendingrequest.html
	//pending request -[id=2313,"fromId"="ser","toId":"Bhavana","status='P']
	//updated status ='A'
	$scope.updatePendingRequest=function(pendingrequest,updatedstatus)
	{
		pendingrequest.status=updatedstatus
		//pending request -[id=2313,"fromId"="ser","toId":"Bhavana","status='A']
		//pending request with status as 'A'/'R'
		FriendService.updatePendingRequest(pendingrequest).then(function(response){
			getAllPendingRequests()
		},function(response){
			
			if(response.status==401)
				$location.path('/login')
			
		})
	}
	
	getAllSuggestedUsers()
	getAllPendingRequests()
	getListofFriends()
})