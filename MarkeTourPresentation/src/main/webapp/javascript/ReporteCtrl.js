(function() {

	var ctrl = function($scope, $http) {

		$scope.service = "http://localhost:8080/ReporteServices/";
		$scope.allfacturas = {};
		$scope.publication = {};

		$scope.FindAll = function() {
			$http.get($scope.service).success(function(response) {
				$scope.allfacturas = response;
			});
		};

		

		$scope.FindAll();

	};

	var module = angular.module("MarkeTour");
	module.controller("ReporteCtrl", ctrl);

}());