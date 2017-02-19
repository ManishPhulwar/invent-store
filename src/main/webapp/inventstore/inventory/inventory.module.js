'use strict';
var appInventory = angular.module('inventstore.inventory',[

]);

appInventory.config(function($routeProvider) {
	$routeProvider
	.when('/inventory',{
		templateUrl:'views/inventory.html',
		controller: 'inventoryCtrl'
	})
	
});