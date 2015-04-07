(function() {

	var ctrl = function($scope, $http) {
        var articulos=[];
        $scope.service = "http://localhost:8080/ProductService/";
		$scope.allItems = [];
		$scope.item = {};
		$scope.allProducts = [];
		$scope.product = {};
        alert(localStorage.getItem("articulos")+ " "+window.location);
        var html=window.location+"";
        var entro=false;
		if (localStorage.getItem("articulos")!=null && html.indexOf("ShoppingCartView.html")>-1 && entro==false){
			entro=true;
			alert("entro");
			 $scope.allItems=localStorage.getItem("articulos");;
			 alert("entro");
		}
		

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

		

		$scope.addItem = function(data) {
			articulos.push(data);
			$scope.allItems.push(data);
			localStorage.setItem("articulos",$scope.allItems);
			
		};
		$scope.FindAll();
	};

	var module = angular.module("MarkeTour");
	module.controller("ShoppingCartCtrl", ctrl);

}());