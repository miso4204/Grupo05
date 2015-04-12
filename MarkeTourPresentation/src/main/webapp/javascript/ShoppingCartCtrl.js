(function() {

	var ctrl = function($scope, $http) {
        var articulos=[];
        $scope.service = "http://localhost:8080/ProductService/";
		$scope.allItems = [];
		$scope.item = {};
		$scope.allProducts = [];
		$scope.product = {};        
		$scope.total="";
		$scope.totalPlanes="";
        var html=window.location+"";
        
	    if (localStorage.getItem("articulos")!=null && html.indexOf("ShoppingCartView.html")>-1){		
			articulos=JSON.parse(localStorage.getItem("articulos"));
			 $scope.allItems=articulos;
			 var suma =0;
			 for (var i=0;i<articulos.length;i++){
				 suma=suma+parseInt(articulos[i].valor);
			 }
			 $scope.total="Total a pagar: $ "+suma;	 
			 $scope.totalPlanes="Número de planes: " + articulos.length;
			 
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
			 if (localStorage.getItem("articulos")!=null ){		
					articulos=JSON.parse(localStorage.getItem("articulos"));			 
					 
				}
			articulos.push(data);
			localStorage.setItem("articulos",JSON.stringify(articulos));
			
		};
		$scope.FindAll();
	};

	var module = angular.module("MarkeTour");
	module.controller("ShoppingCartCtrl", ctrl);

}());