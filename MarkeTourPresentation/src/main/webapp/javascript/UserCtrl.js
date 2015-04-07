(function() {

	var ctrl = function($scope, $http, $cookies) {

		$scope.service = "http://localhost:8080/UserService/";
		$scope.allUsers = {};
		$scope.usuario = {};
		$scope.alerts = [];

		$scope.FindAll = function() {
			$http.get($scope.service).success(function(response) {
				$scope.allUsers = response;
			});
		};

		$scope.getUser = function(){
			console.log($cookies.authenticated);
			if($cookies.authenticated){
				var userid = $cookies.userid;
				console.log(userid);
				$http.get($scope.service+userid).success(function(response) {
					console.log(response);
					$scope.usuario = response;
				});
			}
		}
		
		$scope.Authenticate = function(data) {
			console.log(data);
			$http.post($scope.service, data).success(function(response) {
				$cookies.authenticated = response.response;
				if($cookies.authenticated){
					//redireccionar a pagina inicial
					$cookies.userid=response.id;
				}else{
					$scope.alerts = [];
					$scope.addAlert();
				}
			});
		};

		$scope.Persist = function(data) {
			$http({
				method : "PUT",
				url : $scope.service,
				data : data,
				headers : {
					'Content-Type' : 'application/json'
				}
			}).success(function(response) {
				//Set message
			});
		};

		$scope.Delete = function(id) {
			$http.get($scope.service + "delete/" + id).success(
			function(response) {
				$scope.FindAll();
			});
		};

		$scope.addAlert = function() {
			$scope.alerts.push({type: 'danger', msg: 'Error de autenticación. Usuario o contraseña incorrectas'});
		};

		$scope.closeAlert = function(index) {
			$scope.alerts.splice(index, 1);
		};
		
		$scope.getUser();

	};

	var module = angular.module("MarkeTour");
	module.controller("UserCtrl", ctrl);

}());