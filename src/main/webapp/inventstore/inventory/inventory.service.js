appInventory.factory('inventoryService', ['$http', function($http){
	
	return{
		//method to get Stock Data
		getStockData: function(){
			return $http.get('/stock');
			
		}
	};
	
}]);