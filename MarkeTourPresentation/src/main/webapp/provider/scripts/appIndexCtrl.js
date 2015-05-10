(function() {
	var ctrl = function($scope, $http, $routeParams, $location) {
		$scope.appName = "Marketour";
		$scope.moduleName = "Gestión Paquetes";
		$scope.subModuleName = "Gestión Productos";
		$scope.servicePackage = "http://localhost:8080/PackageServices/";
		$scope.serviceProduct = "http://localhost:8080/ProductService/";
		$scope.data = {};
		$scope.detail = {};
		$scope.FindPackages = function() {
			$http.get($scope.servicePackage).success(function(response) {
				$scope.data = response;
			});
		};
		$scope.FindProducts = function() {
			$http.get($scope.serviceProduct).success(function(response) {
				$scope.detail = response;
			});
		};
		$scope.FindPackages();
		$scope.FindProducts();
	};
	var module = angular.module("provider");
	module.controller("appIndexCtrl", ctrl);
}());