(function () {
    var ctrl = function ($scope, $http, $routeParams, $location) {
        $scope.id = $location.search().id || 0;
        $scope.appName = "Marketour";
        $scope.moduleName = "Gestión Usuarios";
        $scope.serviceUser = "http://localhost:8080/UserService/";
        $scope.serviceProvider = "http://localhost:8080/ProviderService/";
        $scope.serviceCustomer = "http://localhost:8080/CustomerService/";
        $scope.serviceCurrency = "http://localhost:8080/CurrencyServices/";
        $scope.data = {};
        $scope.dataProvider = {};
        $scope.dataCustomer = {};
        $scope.currencies = [];
        $scope.typeUser = ["Proveedor", "Cliente"];
        $scope.FindUserById = function (id) {
            $http.get($scope.serviceUser + id).success(function (response) {
                $scope.data = response;
                $scope.ChangeUserType();
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
            });
        };
        $scope.Delete = function (id) {
            $http.get($scope.serviceUser + "delete/" + id).success(
                function (response) {
                    $scope.FindAll();
                });
        };
        $scope.FindUserById($scope.id);
        $scope.FindCurrencies();
    };
    var module = angular.module("user");
    module.controller("appUserCtrl", ctrl);
}());