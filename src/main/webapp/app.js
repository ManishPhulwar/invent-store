/**
 * 
 */
'use strict';

var app = angular.module('inventStoreWeb',[
      "ngAnimate",
      "ngCookies",
      "ngResource",
      "ngRoute",
      "ngSanitize",
      "ngTouch",
      "ui.bootstrap",
      "angularUtils.directives.dirPagination",
      "ngCsv",
      "inventstore.inventory"
      ]);

app.config(function ($routeProvider,$locationProvider){
	$routeProvider
	.when('/authFailure',{
		templateUrl: 'views/authFailure.html',
	})
	.when('/error',{
		templateUrl: 'views/error.html'
	})
	.otherwise({
		redirectTo: '/'
	});
	$locationProvider.hashPrefix('');
	
});


