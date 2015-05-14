(function () {
    var ctrl = function ($scope, $http, $routeParams, $location) {
        $scope.id = $location.search().id || 0;
        $scope.idUsr = 0;
        $scope.rolUsr = "";
        $scope.appName = "Marketour";
        $scope.moduleName = "Gestionar Paquetes";
        $scope.subModuleName = "Promoción";
        $scope.moduleDetailsName = "Productos";
        $scope.servicePackage = "http://localhost:8080/PackageServices/";
        $scope.serviceProduct = "http://localhost:8080/ProductService/";
        $scope.data = {};
        $scope.details = [];
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
        $scope.FindPackageById = function (id) {
            $http.get($scope.servicePackage + id).success(function (response) {
                $scope.data = response;
                toastr.success("Se obtuvo la información de Paquete", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.FindProducts = function () {
            $http.get($scope.serviceProduct).success(function (response) {
                $scope.details = response;
                toastr.success("Se obtuvo la información de Productos", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.PersistPackage = function (data) {
            var editKey = "$edit";
            var hasKey = "$$hashKey";
            if (data[editKey])
                delete data[editKey];
            if (data[hasKey])
                delete data[hasKey];
            data.proveedor.id = $scope.idUsr;
            $http({
                method: "PUT",
                url: $scope.servicePackage,
                data: data,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (response) {
                $scope.data = response;
                toastr.success("Se registró la información de Paquete", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.PersistProduct = function (data) {
            $scope.data.productos.push(data);
            toastr.success("Se agregó la información de Producto", $scope.moduleName);
        };
        $scope.Delete = function (id) {
            $http.get($scope.servicePackage + "delete/" + id).success(
                function (response) {
                    $scope.FindAll();
                });
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
        $scope.FindPackageById($scope.id);
        $scope.FindProducts();
    };
    var module = angular.module("provider");
    module.controller("appPackageCtrl", ctrl);
}());
