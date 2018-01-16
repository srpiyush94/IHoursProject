app.factory('ProfileService', function($http) {

	var profileService = {}

	profileService.getUserByUsername = function() {
		return $http.get("http://localhost:7070/IHours/users")
	}

	profileService.updateUserProfile = function(user) {
		return $http.put("http://localhost:7070/IHours/updateprofile", user)
	}

	return profileService;
})