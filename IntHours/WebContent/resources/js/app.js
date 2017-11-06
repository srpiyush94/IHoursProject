 var app = angular.module('myApp',['ngRoute','ngCookies']);

app.config(function ($routeProvider,$locationProvider) {
    $locationProvider.hashPrefix('');
    $routeProvider
		.when('/', {
			templateUrl: 'home/home.html',
			controller: 'mainController'
		})
		.when('/login', {
			templateUrl: 'login/login.html',
			controller: 'LoginController'
		})
		.when('/register',{
			
			templateUrl:'register/register.html',
			controller: 'UserController'
					
				})
		/*.otherwise({
			redirectTo: '/'
		});*/
});