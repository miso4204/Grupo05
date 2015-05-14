(function () {
    var ctrl = function ($scope, $http, $routeParams, $location) {
        $scope.id = 0;
        $scope.rol = "";
        $scope.appName = "Marketour";
        $scope.moduleName = "Gestionar Usuario";
        $scope.serviceUser = "http://localhost:8080/UserService/";
        $scope.serviceProvider = "http://localhost:8080/ProviderService/";
        $scope.serviceCustomer = "http://localhost:8080/CustomerService/";
        $scope.serviceCurrency = "http://localhost:8080/CurrencyServices/";
        $scope.data = {};
        $scope.dataProvider = {};
        $scope.dataCustomer = {};
        $scope.currencies = [];
        $scope.typeUser = ["Proveedor", "Cliente"];
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
        $scope.FindUserById = function (id) {
            $http.get($scope.serviceUser + id).success(function (response) {
                $scope.data = response;
                $scope.ChangeUserType();
                toastr.success("Se obtuvo la información", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.ChangeUserType = function () {
            if ($scope.data.tipoUsuario === "Cliente") {
                $scope.FindCustomerById($scope.data.id);
            }
            if ($scope.data.tipoUsuario === "Proveedor") {
                $scope.FindProviderById($scope.data.id);
            }
        };
        $scope.FindProviderById = function (id) {
            $http.get($scope.serviceProvider + id).success(function (response) {
                $scope.dataProvider = response;
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.FindCustomerById = function (id) {
            $http.get($scope.serviceCustomer + id).success(function (response) {
                $scope.dataCustomer = response;
            });
        };
        $scope.FindCurrencies = function () {
            $http.get($scope.serviceCurrency).success(function (response) {
                $scope.currencies = response;
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.PersistUser = function () {
            var editKey = "$edit";
            var hasKey = "$$hashKey";
            if ($scope.data[editKey])
                delete $scope.data[editKey];
            if ($scope.data[hasKey])
                delete $scope.data[hasKey];
            $http({
                method: "PUT",
                url: $scope.serviceUser,
                data: angular.toJson($scope.data, true),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (response) {
                $scope.data.id = response.id;
                if ($scope.data.tipoUsuario === "Cliente") {
                    $scope.PersistCustomer($scope.data.id);
                    response.tipoUsuario = "Cliente";
                }
                if ($scope.data.tipoUsuario === "Proveedor") {
                    $scope.PersistProvider($scope.data.id);
                    response.tipoUsuario = "Proveedor";
                }
                $scope.data = response;
                toastr.success("Se registró la información de Usuario", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.PersistCustomer = function (id) {
            $scope.dataCustomer.id = id;
            var editKey = "$edit";
            var hasKey = "$$hashKey";
            if ($scope.dataCustomer[editKey])
                delete $scope.dataCustomer[editKey];
            if ($scope.dataCustomer[hasKey])
                delete $scope.dataCustomer[hasKey];
            $http({
                method: "PUT",
                url: $scope.serviceCustomer,
                data: angular.toJson($scope.dataCustomer, true),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (response) {
                $scope.dataCustomer = response;
                toastr.success("Se registró la información de Cliente", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.PersistProvider = function (id) {
            $scope.dataProvider.id = id;
            var editKey = "$edit";
            var hasKey = "$$hashKey";
            if ($scope.dataProvider[editKey])
                delete $scope.dataProvider[editKey];
            if ($scope.dataProvider[hasKey])
                delete $scope.dataProvider[hasKey];
            $http({
                method: "PUT",
                url: $scope.serviceProvider,
                data: angular.toJson($scope.dataProvider, true),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (response) {
                $scope.dataProvider = response;
                toastr.success("Se registró la información de Proveedor", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.Delete = function (id) {
            $http.get($scope.serviceUser + "delete/" + id).success(
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
        $scope.FindUserById($scope.id);
        $scope.FindCurrencies();
    };
    var module = angular.module("user");
    module.controller("appUserCtrl", ctrl);
}());