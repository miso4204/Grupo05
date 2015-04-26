(function() {

	var ctrl = function($scope, $http) {
        var articulos=[];
        $scope.service = "http://localhost:8080/ProductService/";
		$scope.allItems = [];
		$scope.items = {};
		$scope.allProducts = [];
		$scope.items.id=0;
		$scope.items.nombre="";
		$scope.items.descripcion="kkkk";
		$scope.items.valor=0;
		$scope.items.cantidad=0;
		$scope.product = {};        
		$scope.total="";
		$scope.totalPlanes="";
        var html=window.location+"";
        function buscarArticulo(idArticulo){
        for(i=0;i<articulos.length;i++){
        	if (idArticulo==articulos[i].id){
        		return i;
        	}
        }	
        return -1;
        }
       
        
	    if (localStorage.getItem("articulos")!=null && html.indexOf("ShoppingCart.html")>-1){		
			articulos=JSON.parse(localStorage.getItem("articulos"));
			 $scope.allItems=articulos;
			 var suma =0;
			 for (var i=0;i<articulos.length;i++){
				 suma=suma+parseInt(articulos[i].valor);
			 }
			 $scope.total=suma;	 
			 $scope.totalPlanes="Numero de planes: " + articulos.length;
			 
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
			data.cantidad=1;
			 if (localStorage.getItem("articulos")!=null ){		
					articulos=JSON.parse(localStorage.getItem("articulos"));			 
					 
				}
			var pos=buscarArticulo(data.id);
			if (pos>=0){
				articulos[i].cantidad=articulos[i].cantidad+1;
				articulos[i].valor=articulos[i].valor+data.valor;
			}else{
				articulos.push(data);	
			}
			
			localStorage.setItem("articulos",JSON.stringify(articulos));
			
		};
		
		$scope.sumar = function(idArticulo) {
			for(i=0;i<articulos.length;i++){
            	if (idArticulo==articulos[i].id){            		
            		articulos[i].valor=articulos[i].valor+(articulos[i].valor/articulos[i].cantidad);
            		articulos[i].cantidad=articulos[i].cantidad+1;
            		localStorage.setItem("articulos",JSON.stringify(articulos));
            		break;
            	}
            }
		};
		$scope.restar = function(idArticulo) {
			for(i=0;i<articulos.length;i++){
            	if (idArticulo==articulos[i].id){
            		if (articulos[i].cantidad>1){
            			articulos[i].valor=articulos[i].valor-(articulos[i].valor/articulos[i].cantidad);
                		articulos[i].cantidad=articulos[i].cantidad-1;
                		localStorage.setItem("articulos",JSON.stringify(articulos));
            		}
            		
            		break;
            	}
            }
		};
		$scope.FindAll();
	};

	var module = angular.module("MarkeTour");
	module.controller("ShoppingCartCtrl", ctrl);

}());

