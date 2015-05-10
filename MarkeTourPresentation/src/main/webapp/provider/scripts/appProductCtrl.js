(function () {
    var ctrl = function ($scope, $http, $routeParams, $location) {
        $scope.id = $location.search().id || 0;
        $scope.appName = "Marketour";
        $scope.moduleName = "Gestión Productos";
        $scope.moduleDetailsName = "Contenidos";
        $scope.serviceProduct = "http://localhost:8080/ProductService/";
        $scope.serviceCategory = "http://localhost:8080/CategoryServices/";
        $scope.serviceMealPlan = "http://localhost:8080/MealPlanServices/";
        $scope.serviceCity = "http://localhost:8080/CityServices/";
        $scope.serviceDepartment = "http://localhost:8080/DepartmentServices/";
        $scope.serviceContent = "http://localhost:8080/ContentTypeServices/";
        $scope.data = {};
        $scope.details = [];
        $scope.detail = {};
        $scope.categories = [];
        $scope.mealPlans = [];
        $scope.cities = [];
        $scope.departments = [];
        $scope.contents = [];
        $scope.content = GetContentData();
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
        $scope.FindContentTypes = function () {
            $http.get($scope.serviceContent).success(function (response) {
                $scope.contents = response;
            });
        };
        $scope.FindMealPlan = function () {
            $http.get($scope.serviceMealPlan).success(function (response) {
                $scope.mealPlans = response;
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
        $scope.AddContent = function (data) {
            $scope.data.contenidos.push(data);
            $scope.content = GetContentData();
        };
        $scope.PersistProduct = function (data) {
            var editKey = "$edit";
            var hasKey = "$$hashKey";
            if (data[editKey])
                delete data[editKey];
            if (data[hasKey])
                delete data[hasKey];
            $http({
                method: "PUT",
                url: $scope.serviceProduct,
                data: angular.toJson(data, true),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (response) {
                $scope.data = response;
            });
        };
        $scope.Delete = function (id) {
            $http.get($scope.serviceProduct + "delete/" + id).success(
                function (response) {
                    $scope.FindAll();
                });
        };
        function GetContentData() {
            return {
                id: 0,
                tipoContenido: "",
                contenido: "",
                estado: 0,
                producto: null,
                idTipoContenido: 1
            };
        };
        $scope.FindProductById($scope.id);
        $scope.FindCategories();
        $scope.FindMealPlan();
        $scope.FindCities();
        $scope.FindDepartments();
        $scope.FindContentTypes();
    };
    var module = angular.module("provider");
    module.controller("appProductCtrl", ctrl);
}());