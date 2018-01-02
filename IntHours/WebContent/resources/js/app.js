var app = angular.module('myApp', [ 'ngRoute', 'ngCookies' ]);

app.config(function($routeProvider, $locationProvider) 
		{
	$locationProvider.hashPrefix('');
	
	$routeProvider.
	
		when('/home', {
		templateUrl : 'home/home.html',
		controller : 'mainController'
	})
		.when('/login', {
		templateUrl : 'Login/Login.html',
		controller :   'LoginController'
			
	})
	.when('/register', {
		templateUrl : 'register/register.html',
		controller : 'RegisterController'
	})

	.when('/createblog', {
		templateUrl : 'blog/createNewBlog.html',
		controller :    'BlogPostController'
	})

	.when('/viewblog', {
		templateUrl :    'blog/viewBlogs.html',
		controller :      'BlogPostController'
		})
		.when('/manageBlogs',
			{
				templateUrl : 'admin/manageBlogs.html',
				controller :    'AdminController'
			})
		.when('/getBlogForApproval/:id',{
		templateUrl:'admin/approvalform.html',
		controller:'AdminController'
	})
	.when('/getBlogDetail/:id',{
		templateUrl:'blog/viewUserBlogs.html',
		controller:'BlogPostController'
	})		
			
			
		.when('/manageEvents',
			{
				templateUrl : 'admin/manageEvents.html',	
				controller : 'AdminController'
			})
			.when('/addEvents',
			{
				templateUrl :'admin/manageEvents.html',	
//				controller : 'AdminController'
			})
		.when('/manageForums',
			{
				templateUrl : 'C_admin/manageForums.html',
				controller : 'AdminController'
			})
		.when('/manageJobs',
			{
				templateUrl : 'admin/manageJobs.html',
				controller : 'AdminController'
			})
		.when('/addJobs',
			{
				templateUrl : 'admin/manageJobs.html',	
//				controller : 'AdminController'
			})
		.when('/manageUsers',
			{
				templateUrl : 'admin/manageUsers.html',	
				controller : 'AdminController'
		}).
		
		when("/mailus", {
		templateUrl : "mailus/mailus.html",
		controller : "mailctrl"
			
	})
	.otherwise({
		redirectTo : '/'
	});

	
		})
.run(function run($rootScope, $location, $routeParams,$cookies, $http) {
						    // keep user logged in after page refresh
						 	$rootScope.isAdmin = false; 	
					       $rootScope.globals = $cookies.getObject('globals') || {};
						 //  $rootScope.currentuser =  $cookies.getObject('currentuser') || {};
						    console.log(" $rootScope.globals = "+ $rootScope.globals)
						  //alert( $rootScope.globals)
						    console.log(" $rootScope.globals.currentUser = "+ $rootScope.globals.currentUser)
						    if ($rootScope.globals.currentUser) {
						    	
						        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
						    }
						
						   // alert( $rootScope.globals.currentUser)
						    $rootScope.$on('$locationChangeStart', function (event, next, current) {
						        // redirect to login page if not logged in and trying to access a restricted page
						    	console.log("$location.path() ="+$location.path());
						    	console.log($routeParams);
						    	
						    	//var	blogid = $routeParams.id;
						    	
						        var restrictedPage = $.inArray($location.path(), ['/register','/viewBlogs','','/viewForum']) === -1;
						    	var adminPage = $.inArray($location.path(), ['/manageBlogs']) === -1;
						        var loggedInUser =  $rootScope.globals.currentUser;
						    console.log("loggedInUser : ")
						    console.log(loggedInUser)
						   // alert(loggedInUser)
						        if(loggedInUser){
						      //  	alert("Logged In user")
						        	   console.log(" $rootScope.islogged = "+ $rootScope.islogged)
						        	   $rootScope.islogged =true;
						        	   if($rootScope.globals.currentUser.role === 'admin')
										   	$rootScope.isAdmin = true;
						        	   else
						        		   $rootScope.isAdmin = false;
						        }
						       
						        if(($location.path().includes('/viewBlogDetail/')||$location.path().includes('/viewForumDetail/')) && !loggedInUser){
						        	 console.log("Not restricted : ")
							           
							    }else if (restrictedPage && !loggedInUser) {
						        	 console.log("Redirect to login : ")
						            $location.path('/login');
						        }
						    });
					});