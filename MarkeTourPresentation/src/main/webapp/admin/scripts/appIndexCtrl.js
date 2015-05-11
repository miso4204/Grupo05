(function () {
    var ctrl = function ($scope, $http, $routeParams, $location) {
        $scope.appName = "Marketour";
        $scope.moduleName = "Gestión Usuarios";
        $scope.serviceUser = "http://localhost:8080/UserService/";
        $scope.data = {};
        $scope.FindUsers = function () {
            $http.get($scope.serviceUser).success(function (response) {
                $scope.data = response;
            });
        };
        $scope.FindUsers();
    };
    var module = angular.module("user");
    module.controller("appIndexCtrl", ctrl);
}());