'use strict';

angular.module('inventstore.inventory').controller('inventoryCtrl',inventoryCtrl);

function inventoryCtrl($scope, $http, $location, $rootScope, inventoryService){
	
	$scope.sortType = 'ItemCode';
	$scope.sortReverse = false;
	$scope.searchFilter = {};
	$scope.prefilledData = {};
	
	$scope.getStockData = function(initialLoad){
		$scope.showSpinner = true;
		
		inventoryService.getStockData().then(function(response){
			$scope.showSpinner = false;
			
			$scope.inventoryData = response.data;
			if(initialLoad == true){
				$scope.prefilledData = angular.copy($scope.inventoryData);
			}
			
		}, function(error){
			$scope.showSpinner = false;
			var errorStatus = error.status;
			switch(errorStatus){
			case 403:
				$rootScope.userName = false;
				$rootScope.authenticated = false;
				$location.path('/authFailure');
				break;
			case 401:
				$rootScope.userName = false;
				$rootScope.authenticated = false;
				$location.path('/error');
				break;
			default:
				$scope.inventoryData = error.data;
			}
		});
			
		
		
	};

};