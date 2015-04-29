(function () {
    var ctrl = function ($scope, $http, $routeParams, $location) {
        $scope.id = $location.search().id || 0;
        $scope.appName = "Marketour";
        $scope.moduleName = "Gestión Productos";
        $scope.moduleDetailsName = "Contenidos";
        $scope.menuItems = [{
            "name": "Agregar Paquete",
            "href": "agregarPaquete.html"
        }, {
            "name": "Agregar Producto",
            "href": "agregarProducto.html"
        }];
        $scope.serviceProduct = "http://localhost:8080/ProductService/";
        $scope.serviceCategory = "http://localhost:8080/CategoryServices/";
        $scope.serviceCity = "http://localhost:8080/CityServices/";
        $scope.serviceDepartment = "http://localhost:8080/DepartmentServices/";
        $scope.data = {};
        $scope.details = [];
        $scope.detail = {};
        $scope.categories = [];
        $scope.cities = [];
        $scope.departments = [];
        $scope.FindProductById = function (id) {
            $http.get($scope.serviceProduct + id).success(function (response) {
                $scope.data = response;
            });
        };
        $scope.FindCategories = function () {
            $http.get($scope.serviceCategory).success(function (response) {
                $scope.categories = response;
            });
        };
        $scope.FindCities = function () {
            $http.get($scope.serviceCity).success(function (response) {
                $scope.cities = response;
            });
        };
        $scope.FindDepartments = function () {
            $http.get($scope.serviceDepartment).success(function (response) {
                $scope.departments = response;
            });
        };
        $scope.PersistProduct = function (data) {
            var editKey = "$edit";
            var hasKey = "$$hashKey";
            if (data[editKey]) delete data[editKey];
            if (data[hasKey]) delete data[hasKey];
            $http({
                method: "PUT",
                url: $scope.serviceProduct,
                data: angular.toJson(data, true),
                headers: {'Content-Type': 'application/json'}
            }).success(function (response) {
                $scope.data = response;
            });
        };
        $scope.Delete = function (id) {
            $http.get($scope.serviceProduct + "delete/" + id).success(function (response) {
                $scope.FindAll();
            });
        };
        $scope.FindProductById($scope.id);
        $scope.FindCategories();
        $scope.FindCities();
        $scope.FindDepartments();
    };
    var module = angular.module("provider");
    module.controller("appProductCtrl", ctrl);
}());