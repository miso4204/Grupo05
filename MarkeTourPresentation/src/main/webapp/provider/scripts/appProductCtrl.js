(function () {
    var ctrl = function ($scope, $http, $routeParams, $location) {
        $scope.id = $location.search().id || 0;
        $scope.idUsr = 0;
        $scope.rolUsr = "";
        $scope.appName = "Marketour";
        $scope.moduleName = "Gestionar Productos";
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
            }else{
                toastr.success("Por favor inicie sesión", $scope.moduleName);
            }
        };
        $scope.FindProductById = function (id) {
            $http.get($scope.serviceProduct + id).success(function (response) {
                $scope.data = response;
                toastr.success("Se obtuvo la información de Producto", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.FindCategories = function () {
            $http.get($scope.serviceCategory).success(function (response) {
                $scope.categories = response;
                toastr.success("Se obtuvo la información de Categorias", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.FindContentTypes = function () {
            $http.get($scope.serviceContent).success(function (response) {
                $scope.contents = response;
                toastr.success("Se obtuvo la información de Tipos de Contenido", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.FindMealPlan = function () {
            $http.get($scope.serviceMealPlan).success(function (response) {
                $scope.mealPlans = response;
                toastr.success("Se obtuvo la información de Planes Alimenticios", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.FindCities = function () {
            $http.get($scope.serviceCity).success(function (response) {
                $scope.cities = response;
                toastr.success("Se obtuvo la información de Ciudades", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.FindDepartments = function () {
            $http.get($scope.serviceDepartment).success(function (response) {
                $scope.departments = response;
                toastr.success("Se obtuvo la información de Departamentos", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
            });
        };
        $scope.AddContent = function (data) {
            $scope.data.contenidos.push(data);
            $scope.content = GetContentData();
            toastr.success("Se agregó la información de Contenido", $scope.moduleName);
        };
        $scope.PersistProduct = function (data) {
            var editKey = "$edit";
            var hasKey = "$$hashKey";
            if (data[editKey])
                delete data[editKey];
            if (data[hasKey])
                delete data[hasKey];
            data.proveedor.id = $scope.idUsr;
            $http({
                method: "PUT",
                url: $scope.serviceProduct,
                data: angular.toJson(data, true),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (response) {
                $scope.data = response;
                toastr.success("Se registró la información de Producto", $scope.moduleName);
            }).error(function (error) {
                toastr.success("Se presentó un problema, contacte a su Administrador", $scope.moduleName);
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
