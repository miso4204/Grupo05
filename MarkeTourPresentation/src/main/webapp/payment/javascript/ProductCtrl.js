(function() {

	var ctrl = function($scope, $http) {

		$scope.service = "http://localhost:8080/ProductService/";
		$scope.allProducts = {};
		$scope.product = {};

		$scope.FindAll = function() {
			$http.get($scope.service).success(function(response) {
				$scope.allProducts = response;
			});
		};

		$scope.Persist = function(data) {
			var editKey = "$edit";
			var hasKey = "$$hashKey";

			if (data[editKey])
				delete data[editKey];

			if (data[hasKey])
				delete data[hasKey];

			$http({
				method : "PUT",
				url : $scope.service,
				data : data,
				headers : {
					'Content-Type' : 'application/json'
				}
			}).success(function(response) {
				$scope.FindAll();
			});
		};

		$scope.Delete = function(id) {
			$http.get($scope.service + "delete/" + id).success(
					function(response) {
						$scope.FindAll();
					});
		};

		$scope.FindAll();

	};

	var module = angular.module("MarkeTour");
	module.controller("ProductCtrl", ctrl);

}());