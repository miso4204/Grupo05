(function() {

	var app = angular.module('MarkeTour', []);

	app.config([ '$httpProvider', function($httpProvider) {
		$httpProvider.defaults.useXDomain = true;
		delete $httpProvider.defaults.headers.common['X-Requested-With'];
	} ]);

}());
