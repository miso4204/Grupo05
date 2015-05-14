(function() {

	var ctrl = function($scope, $http,$filter) {
        var articulos=[];
        var paquetes=[];
        $scope.purchaseService = "http://localhost:8080/PuschaseService/";
        $scope.service = "http://localhost:8080/ProductService/";
        $scope.packageService = "http://localhost:8080/PackageServices/";
        $scope.formaPago = "http://localhost:8080/PuschaseService/FormasPago";
        $scope.servicioMedioPago = "http://localhost:8080/PuschaseService/MedioPagoCliente/"+sessionStorage.getItem('usuario')+"/"; //Recordar poner el clinete logeado
		$scope.allItems = [];
		$scope.allProducts = [];
		$scope.allPackages = [];
		$scope.packagesList = [];
		$scope.listaFormaPago = [];
		$scope.total="";
		$scope.totalPlanes="";
		$scope.error="";
		$scope.medioPago=[];
		$scope.signoMoneda="$";
        var html=window.location+"";
        function buscarArticulo(idArticulo){
        for(i=0;i<articulos.length;i++){
        	if (idArticulo==articulos[i].id){
        		return i;
        	}
        }	
        return -1;
        }
        function buscarPaquete(idPaquete){
            for(i=0;i<paquetes.length;i++){
            	if (idPaquete==paquetes[i].id){
            		return i;
            	}
            }	
            return -1;
            }
       function calcularTotal(){
    	   var suma =0;
			 for (var i=0;i<articulos.length;i++){
				 suma=suma+parseInt(articulos[i].valor);
			 }
			 var descuento=0;
			 for (var i=0;i<paquetes.length;i++){
				 suma=suma+parseInt(paquetes[i].valor);
				 if (paquetes[i].descuento>0){
					 descuento=descuento+((paquetes[i].descuento/100)*paquetes[i].valor);
				 }
			 }
			 $scope.total=(suma-descuento);	  
			 if (descuento>0){				 
				 $scope.totalPlanes=descuento;
			 }else{
				 $scope.totalPlanes=0;	 
			 }
			 
       }
        
	    if (localStorage.getItem("articulos")!=null && html.indexOf("ShoppingCart.html")>-1){		
			articulos=JSON.parse(localStorage.getItem("articulos"));
			for (var i=0;i<articulos.length;i++){
				delete articulos[i].$$hashKey;	
			}			
			 $scope.allItems=articulos;
			 calcularTotal();
			 
		}
	    if (localStorage.getItem("paquetes")!=null && html.indexOf("ShoppingCart.html")>-1){		
	    	paquetes=JSON.parse(localStorage.getItem("paquetes"));
	    	for (var i=0;i<paquetes.length;i++){
				delete paquetes[i].$$hashKey;	
			}
			 $scope.allPackages=paquetes;
			 calcularTotal();
			 
		}
	    $scope.getUsuario = function() {
			$http.get("http://localhost:8080/UserService/"+sessionStorage.getItem('usuario')).success(function(response) {
				var moneda=response.moneda;
				if(moneda=="Dolar"){
					$scope.signoMoneda = "USD";	
				}else if(moneda=="Euro"){
					$scope.signoMoneda = "EUR";
				}else if(moneda=="Peso Colombiano"){
					$scope.signoMoneda = "COP";
				}
				
			});
		};
	   
	    $scope.FindAll = function() {
			$http.get($scope.service).success(function(response) {
				$scope.allProducts = response;
			});
		};
	    
		

		
		
		$scope.FindAllPackages = function() {
			$http.get($scope.packageService).success(function(response) {
				$scope.packagesList = response;
			});
		};
		$scope.FindAllFormaPago = function() {
			$http.get($scope.formaPago).success(function(response) {
				$scope.listaFormaPago = response;
			});
		};
		
		$scope.buscarMedioPago = function(idforma) {
			var url=$scope.servicioMedioPago+idforma+"";
			$http.get(url).success(function(response) {
				$scope.medioPago = response;				
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
				url : $scope.purchaseService,
				data : data,
				headers : {
					'Content-Type' : 'application/json'
				}
			}).success(function(response) {
				$scope.limpiarCarrito();
				localStorage.removeItem('jsonCompleto');
				localStorage.removeItem('medioPago');
				window.location="Success.html";
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
				articulos[pos].cantidad=articulos[pos].cantidad+1;
				articulos[pos].valor=articulos[pos].valor+data.valor;
			}else{
				articulos.push(data);	
			}
			
			localStorage.setItem("articulos",JSON.stringify(articulos));
			
		};
		
		$scope.addPackage = function(data) {
			data.cantidad=1;
			 if (localStorage.getItem("paquetes")!=null ){		
				 paquetes=JSON.parse(localStorage.getItem("paquetes"));			 
					 
				}
			var pos=buscarPaquete(data.id);
			if (pos>=0){
				paquetes[pos].cantidad=paquetes[pos].cantidad+1;
				paquetes[pos].valor=paquetes[pos].valor+data.valor;
			}else{
				paquetes.push(data);	
			}
			
			localStorage.setItem("paquetes",JSON.stringify(paquetes));
			
		};
		
		$scope.sumar = function(idArticulo) {    
            		var pos=buscarArticulo(idArticulo);
            		if (pos>=0){
            			articulos[pos].valor=articulos[pos].valor+(articulos[pos].valor/articulos[pos].cantidad);
                		articulos[pos].cantidad=articulos[pos].cantidad+1;
                		localStorage.setItem("articulos",JSON.stringify(articulos));
                		calcularTotal();
            		}
            	
		};
		$scope.sumarPaquete = function(idPaquete) {
			var pos=buscarPaquete(idPaquete);
    		if (pos>=0){          		
            		paquetes[pos].valor=paquetes[pos].valor+(paquetes[pos].valor/paquetes[pos].cantidad);
            		paquetes[pos].cantidad=paquetes[pos].cantidad+1;
            		localStorage.setItem("paquetes",JSON.stringify(paquetes));
            		calcularTotal();
            	
            }
		};
		$scope.restar = function(idArticulo) {
			var pos=buscarArticulo(idArticulo);
    		if (pos>=0){
        		if (articulos[pos].cantidad>1){
        			articulos[pos].valor=articulos[pos].valor-(articulos[i].valor/articulos[pos].cantidad);
            		articulos[pos].cantidad=articulos[pos].cantidad-1;
            		localStorage.setItem("articulos",JSON.stringify(articulos));
            		calcularTotal();
        		}
            }
		};
		$scope.restarPaquete = function(idPaquete) {
			var pos=buscarPaquete(idPaquete);
    		if (pos>=0){
        		if (paquetes[pos].cantidad>1){
        			paquetes[pos].valor=paquetes[pos].valor-(paquetes[i].valor/paquetes[pos].cantidad);
        			paquetes[pos].cantidad=paquetes[pos].cantidad-1;
            		localStorage.setItem("paquetes",JSON.stringify(paquetes));
            		calcularTotal();
        		}
            }
		};
		
		$scope.eliminar = function(idArticulo) {
			var pos=buscarArticulo(idArticulo);
    		if (pos>=0){          		
    			articulos.splice(pos,1);
        		localStorage.setItem("articulos",JSON.stringify(articulos));
        		calcularTotal();        		
            	
            }
		};
		
		$scope.eliminarPaquete  = function(idPaquete ) {
			var pos=buscarPaquete (idPaquete);
    		if (pos>=0){          		
    			paquetes.splice(pos,1);
        		localStorage.setItem("paquetes",JSON.stringify(paquetes));
        		calcularTotal();        		
            	
            }
		};
		
		$scope.limpiarCarrito = function() {
			articulos=[];			
			$scope.allItems=articulos;
			localStorage.setItem("articulos",JSON.stringify(articulos));
			paquetes=[];			
			$scope.allPackages=paquetes;
			localStorage.setItem("paquetes",JSON.stringify(paquetes));
			calcularTotal();
		};
		
		$scope.comprar = function() {
			if (sessionStorage.getItem('usuario')!=null && sessionStorage.getItem('usuario')!=""){
				if($scope.total>0){
					var select_id = document.getElementById("forma");

					var fp=select_id.options[select_id.selectedIndex].value;
					if (fp=="Seleccione >>" ||fp==""){
						$scope.error="Debe selecionar una forma de pago.";
					}else{
						$scope.error="";
						localStorage.setItem("medioPago",fp);
						$scope.buscarMedioPago(fp);
						var json=armarJson($scope.medioPago[0].id);
						localStorage.setItem("jsonCompleto",JSON.stringify(json));
						if (fp==1){						
							window.location="creditCardConfirm.html";
						}else if(fp==2){
							window.open("https://www.pse.com.co/inicio");
							$scope.confirmarComprar();						
						}else if(fp==3){
							window.location="cashOnDeliveryConfirm.html";					
						}
					}
				}else{
					$scope.error="El carrito esta vacio.";
				}
			}else{
				$scope.error="Debe iniciar sesión para realizar una compra.";
			}
				
			
			
			
		};
		
		$scope.confirmarComprar = function() {
			var compra=JSON.parse(localStorage.getItem("jsonCompleto"));
			$scope.Persist(compra);
			
		};
		$scope.cambioMedioPago = function() {
			if ($scope.f!=""){
				localStorage.setItem("medioPago",$scope.f);
				$scope.buscarMedioPago($scope.f);				
			}
			
		};
		
		function armarJson(medioPago){
			var today = new Date();
		    var dd = today.getDate();
		    var mm = today.getMonth()+1; 
		    var yyyy = today.getFullYear();
		    var fecha=yyyy+"-"+mm+"-"+dd;
		    
		    var json =new Object();
		    json.cliente=sessionStorage.getItem('usuario');
		    json.calificacion=-1;
		    json.fechaCompra=fecha;
		    json.medioPago=parseInt(medioPago);
		    json.estado=1;
		    json.itemCompras=[];
		    
		    for(i=0;i<paquetes.length;i++){
		    	var jsonPaquetes=new Object();
		    	jsonPaquetes.paquete=paquetes[i].id;
		    	jsonPaquetes.valor=paquetes[i].valor-((paquetes[i].descuento/100)*paquetes[i].valor);
		    	jsonPaquetes.cantidad=paquetes[i].cantidad;
		    	json.itemCompras.push(jsonPaquetes);
		    }
		    for(i=0;i<articulos.length;i++){
		    	var jsonProductos=new Object();
		    	jsonProductos.producto=articulos[i].id;
		    	jsonProductos.valor=articulos[i].valor;
		    	jsonProductos.cantidad=articulos[i].cantidad;
		    	json.itemCompras.push(jsonProductos);
		    }
		    return json;
		}
		//$scope.FindAll();
		//$scope.FindAllPackages();
		$scope.getUsuario();
		$scope.FindAllFormaPago();
		 if (localStorage.getItem("medioPago")!=null){
				$scope.buscarMedioPago(localStorage.getItem("medioPago"));			 
			}
		
	};

	var module = angular.module("MarkeTour");
	module.controller("ShoppingCartCtrl", ctrl);

}());



