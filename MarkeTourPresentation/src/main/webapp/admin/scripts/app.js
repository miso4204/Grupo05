(function () {
    var app = angular.module("user", ["ngRoute"]);


    /*app.config(function ($httpProvider) {
     if (!$httpProvider.defaults.headers.get) {
     $httpProvider.defaults.headers.get = {};
     }
     $httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
     $httpProvider.defaults.headers.get["If-Modified-Since"] = '0';
     });*/

    app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }]);
}());