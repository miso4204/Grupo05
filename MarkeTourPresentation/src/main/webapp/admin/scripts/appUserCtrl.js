(function () {
    var ctrl = function ($scope, $http, $routeParams, $location) {
        $scope.id = $location.search().id || 0;
        $scope.appName = "Marketour";
        $scope.moduleName = "Gestión Productos";

        $scope.serviceUser = "http://localhost:8080/UserService/";
        $scope.serviceProvider = "http://localhost:8080/UserService/proveedor/";
        $scope.serviceCustomer = "http://localhost:8080/UserService/cliente/";

        $scope.serviceCurrency = "http://localhost:8080/CurrencyServices/";
        $scope.data = {};

        $scope.dataProvider = {};
        $scope.dataCustomer = {};

        $scope.currencies = [];
        $scope.typeUser = ["Proveedor", "Cliente"];
        $scope.FindUserById = function (id) {
            $http.get($scope.serviceUser + id).success(function (response) {
                $scope.data = response;
            });
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

        $scope.PersistUser = function (data) {
            var editKey = "$edit";
            var hasKey = "$$hashKey";
            if (data[editKey])
                delete data[editKey];
            if (data[hasKey])
                delete data[hasKey];
            $http({
                method: "PUT",
                url: $scope.serviceUser,
                data: angular.toJson(data, true),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (response) {
                $scope.data = response;
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