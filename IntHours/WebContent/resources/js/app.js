var app = angular.module('myApp', [ 'ngRoute', 'ngCookies','ngIdle' ]);

app.config(function($routeProvider, $locationProvider,IdleProvider, KeepaliveProvider) {
			$locationProvider.hashPrefix('');

			$routeProvider.

			when('/home', {
				templateUrl : 'home/home.html',
				controller : 'mainController'
			}).when('/login', {
				templateUrl : 'Login/Login.html',
				controller : 'LoginController'

			}).when('/register', {
				templateUrl : 'register/register.html',
				controller : 'RegisterController'
			})

			.when('/manageBlogs', {
				templateUrl : 'admin/manageBlogs.html',
				controller : 'AdminController'

			}).when('/getBlogForApproval/:id', {
				templateUrl : 'admin/approvalform.html',
				controller : 'AdminController'
			}).

			when('/createblog', {
				templateUrl : 'blog/createNewBlog.html',
				controller : 'BlogPostController'
			}).when('/viewblog', {
				templateUrl : 'blog/viewBlogs.html',
				controller : 'BlogPostController'

			}).when('/getBlogDetail/:id', {
				templateUrl : 'blog/viewUserBlogs.html',
				controller : 'BlogPostController'
			})

			.when('/manageEvents', {
				templateUrl : 'admin/manageEvents.html',
				controller : 'AdminController'
			}).when('/addEvents', {
				templateUrl : 'admin/manageEvents.html',
			// controller : 'AdminController'
			}).when('/manageForums', {
				templateUrl : 'C_admin/manageForums.html',
				controller : 'AdminController'
			}).when('/jobs', {
				templateUrl : "Job/CreateJob.html",
				controller : "jobctrl"
			}).when("/applyjob", {
				templateUrl : "Job/ViewJob.html",
				controller : "jobctrl"
			})
			.when("/jobslist", {
				templateUrl : "Job/jobslist.html",
				controller : "jobctrl"
			})
			.when("/forum", {
				templateUrl : "Forum/Forum.html",
				controller : 'forumctrl'
			}).when("/viewforum", {
				templateUrl : "Forum/viewforum.html",
				controller : 'forumctrl'
			}).when("/event", {
				templateUrl : "Event/event.html",
				controller : 'eventctrl'
			}).when("/viewevent", {
				templateUrl : "Event/viewevent.html",
				controller : "eventctrl"
			}).when('/manageUsers', {
				templateUrl : 'admin/manageUsers.html',
				controller : 'AdminController'
			}).when("/profile", {
				templateUrl : "profile/UserProfile.html",
				controller : "ProfileController"
			})

			.when('/edituserprofile', {
				templateUrl : 'profile/Updateprofile.html',
				controller : 'ProfileController'
			})
			.when('/suggestedusers', {
				templateUrl : 'friend/suggestedusers.html',
				controller : 'FriendController'
			}).when('/pendingrequests', {
				templateUrl : 'friend/pendingrequests.html',
				controller : 'FriendController'
			}).when('/friends', {
				templateUrl : 'friend/friendslist.html',
				controller : 'FriendController'
			})
			.when('/chat', {
				templateUrl : 'chat/chat.html',
				controller : 'ChatCtrl'
			})
			.when("/mailus", {
				templateUrl : "mailus/mailus.html",
				controller : "mailctrl"
			}).otherwise({
				redirectTo : '/'
			});
			
			IdleProvider.idle(900); // 15 min
			  IdleProvider.timeout(120);
			  KeepaliveProvider.interval(600); // heartbeat every 10 min
			//  KeepaliveProvider.http('/api/heartbeat'); // URL that makes sure session is alive
			 

		})
		.run(function run($rootScope, $location, $routeParams,$cookies, $http,AuthenticationService,Idle) {
						
						 Idle.watch();
						//  $rootScope.$on('IdleStart', function() { /* Display modal warning or sth */ });
						  $rootScope.$on('IdleTimeout', function() { /* Logout user */

							  console.log("logout func")
				              AuthenticationService.Logout(function (response) {	 
				                 if (response.success) {
				                	 
				                 	AuthenticationService.ClearCredentials();
				                     console.log("success"+response.data)
				                     $location.path('/login');
				                     
				                 } else {
				                	 
				                   //  FlashService.Error(response.message);
				                 //	     alert("error")
				                	
				                        console.log(response.data)
				                		 AuthenticationService.ClearCredentials();
				                         console.log("User is invalid"+response.data)
				                         $rootScope.islogged=false;
				                         $location.path('/login');
				                	
				                 }
				             });
						  });
						
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
						    	
						        var restrictedPage = $.inArray($location.path(), ['/register','/viewBlogs','','/viewForum','/home','/termandconditions','/privacypolicy','/contentpolicy']) === -1;
						    	var adminPage = $.inArray($location.path(), ['/admin/manageBlog','/admin/addjob','/admin/home']) !== -1;
						        var loggedInUser =  $rootScope.globals.currentUser;
							    console.log("loggedInUser : ")
							    console.log(loggedInUser)
							        if(loggedInUser){
							        	
						        	   console.log(" $rootScope.islogged = "+ $rootScope.islogged)
						        	   $rootScope.islogged = true;
						        	   
						        	   if($rootScope.globals.currentUser.role === 'ADMIN')
										   	$rootScope.isAdmin = true;
						        	   else
						        		   $rootScope.isAdmin = false;
						        }
							    
						        if(($location.path().includes('/viewBlogDetail/')||$location.path().includes('/viewForumDetail/')||$location.path().includes('/admin/viewBlogDetail/')||$location.path().includes('/admin/viewForumDetail/')) && !loggedInUser){
						        	 console.log("Not restricted : ")
							           
							    }else if (restrictedPage && !loggedInUser) {
						        	console.log("Redirect to login : ")
						            $location.path('/login');
						        }else if(adminPage && !$rootScope.isAdmin){
						        	alert("not authorized to view this page")
						        	$location.path('/home');
						        }
						        if($rootScope.userNames === undefined){
							        AuthenticationService.getAllApplicationUserNames(function(response){
						    			 if(response.success){
						    				 $rootScope.userNames = response.data;	
						    				 console.log($rootScope.userNames);
						    				 console.log("$rootScope.userNames");
							    		}else{
							    			console.log(response.data)
							    			
							    		}
						    		})
						        }
						 if(loggedInUser){
						       						        
						        if($rootScope.notificationNotViewed === undefined){
						        	 AuthenticationService.getNotificationNotViewed(function(response){
						        		 if(response.success){
						     			console.log(response.data)
						     			$rootScope.notificationNotViewed = response.data;
						     			$rootScope.notificationNotViewedLength = response.data.length;
						        		 }else{
							     			console.log(response.data)
							     			if(response.status==401){
							     				$location.path("/login");
							     			}
						        		 }
						     		});
						     		
						        }
						        if($rootScope.notificationViewed === undefined){
						     		AuthenticationService.getNotificationViewed(function(response){
						     			 if(response.success){
						     			console.log(response.data)
						     			$rootScope.notificationViewed = response.data;
						     		}else{
						     			console.log(response.data)
						     			if(response.status==401){
						     				$location.path("/login");
						     			}
						     		}
						     		});
						        }
						 }
					});
						    
					});