app.directive('emptyToNull', function(){
	return{
		restrict: 'A',
		require: 'ngModel',
		link: function(scope, elem, attr, ctrl){
			ctrl.$parsers.push(function(viewValue){
				if(viewValue === ""){
					return null;
				}
				return viewValue;
			});
		}
	};
});