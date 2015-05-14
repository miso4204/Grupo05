(function () {
    var ctrl = function ($scope, $http, $routeParams, $location) {
        $scope.idUsr = 0;
        $scope.rolUsr = "";
        $scope.appName = "Marketour";
        $scope.moduleName = "Gestionar Paquetes";
        $scope.subModuleName = "Gestionar Productos";
        $scope.servicePackage = "http://localhost:8080/PackageServices/";
        $scope.serviceProduct = "http://localhost:8080/ProductService/";
        $scope.data = {};
        $scope.detail = {};
        $scope.GetSession = function () {
            try {
                $scope.idUsr = window.sessionStorage.getItem("usuario");
            } catch (e) {
                $scope.idUsr = 0;
            }
            if ($scope.idUsr > 0) {
                $http.get("http://localhost:8080/UserService/" + $scope.idUsr).success(function (response) {
                    $scope.rolUsr = response.tipoUsuario;
                    toastr.success("Se obtuvo la información de Usuario", $scope.moduleName);
                }).error(function (error) {
                    toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
                });
            } else {
                toastr.success("Por favor inicie sesión", $scope.moduleName);
            }
        };
        $scope.FindPackages = function () {
            $http.get($scope.servicePackage).success(function (response) {
                $scope.data = response;
                toastr.success("Se obtuvo la información de Paquetes", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.FindProducts = function () {
            $http.get($scope.serviceProduct).success(function (response) {
                $scope.detail = response;
                toastr.success("Se obtuvo la información de Productos", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.FilterPackage = function (data) {
            if (data.proveedor.id == $scope.idUsr) {
                return true;
            } else {
                return false;
            }
        };
        $scope.FilterProduct = function (data) {
            if (data.proveedor.id == $scope.idUsr) {
                return true;
            } else {
                return false;
            }
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
        $scope.FindPackages();
        $scope.FindProducts();
    };
    var module = angular.module("provider");
    module.controller("appIndexCtrl", ctrl);
}());
