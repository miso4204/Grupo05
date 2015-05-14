(function () {
    var ctrl = function ($scope, $http, $routeParams, $location) {
        $scope.id = 0;
        $scope.rol = "";
        $scope.appName = "Marketour";
        $scope.moduleName = "Gestionar Usuario";
        $scope.serviceUser = "http://localhost:8080/UserService/";
        $scope.data = {};
        $scope.GetSession = function () {
            try {
                if ($location.search().id > 0)
                    $scope.id = $location.search().id;
                else {
                    if (window.sessionStorage.getItem("usuario") > 0)
                        $scope.id = window.sessionStorage.getItem("usuario");
                }
            } catch (e) {
                try {
                    $scope.id = window.sessionStorage.getItem("usuario");
                } catch (e) {
                    $scope.id = 0;
                }
            }
            if ($scope.id > 0) {
                $http.get("http://localhost:8080/UserService/" + $scope.id).success(function (response) {
                    $scope.rol = response.tipoUsuario;
                })
            }
        };
        $scope.FindUsers = function () {
            $http.get($scope.serviceUser).success(function (response) {
                $scope.data = response;
                toastr.success("Se obtuvo la información", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.Filter = function (data) {
            if ($scope.rol == "Administrador") {
                return true;
            }
            if ($scope.rol == "Proveedor" && data.tipoUsuario == "Cliente") {
                return true;
            }
            if ($scope.rol == "Proveedor" && data.tipoUsuario == "Proveedor") {
                return $scope.id == data.id;
            }
            if ($scope.rol == "Cliente") {
                return $scope.id == data.id;
            }
            return false;
        };
        //INIT
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "newestOnTop": false,
            "progressBar": true,
            "positionClass": "toast-top-right",
            "preventDuplicates": false,
            "showDuration": "150",
            "hideDuration": "500",
            "timeOut": "2500",
            "extendedTimeOut": "500",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
        $scope.GetSession();
        $scope.FindUsers();
    };
    var module = angular.module("user");
    module.controller("appIndexCtrl", ctrl);
}());
