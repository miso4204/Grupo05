(function() {
	var ctrl = function($scope, $http, $routeParams, $location) {
		$scope.id = $location.search().id || 0;
		$scope.appName = "Marketour";
		$scope.moduleName = "Gestión Paquetes";
		$scope.subModuleName = "Promoción";
		$scope.moduleDetailsName = "Productos";
		$scope.servicePackage = "http://localhost:8080/PackageServices/";
		$scope.serviceProduct = "http://localhost:8080/ProductService/";
		$scope.data = {};
		$scope.details = [];
		$scope.detail = {};
		$scope.FindPackageById = function(id) {
			$http.get($scope.servicePackage + id).success(function(response) {
				$scope.data = response;
			});
		};
		$scope.FindProducts = function() {
			$http.get($scope.serviceProduct).success(function(response) {
				$scope.details = response;
			});
		};
		$scope.PersistPackage = function(data) {
			var editKey = "$edit";
			var hasKey = "$$hashKey";
			if (data[editKey])
				delete data[editKey];
			if (data[hasKey])
				delete data[hasKey];
			$http({
				method : "PUT",
				url : $scope.servicePackage,
				data : data,
				headers : {
					'Content-Type' : 'application/json'
				}
			}).success(function(response) {
				$scope.data = response;
			});
		};
		$scope.PersistProduct = function(data) {
			$scope.data.productos.push(data);
		};
		$scope.Delete = function(id) {
			$http.get($scope.servicePackage + "delete/" + id).success(
					function(response) {
						$scope.FindAll();
					});
		};
		$scope.FindPackageById($scope.id);
		$scope.FindProducts();
	};
	var module = angular.module("provider");
	module.controller("appPackageCtrl", ctrl);
}());