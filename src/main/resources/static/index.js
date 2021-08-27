(function () {
    angular.module('storage', ['ngRoute'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/products', {
                templateUrl: 'products/products.html',
                controller: 'productsController'
            })
            .when('/create_product', {
                templateUrl: 'create_product/create_product.html',
                controller: 'createProductController'
            })
            .when('/edit_product/:productId', {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            .when('/product/:productId', {
                templateUrl: 'product/product.html',
                controller: 'productController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http) {
    }
})();

angular.module('storage').controller('indexController', function ($rootScope, $http, $scope, $location) {
    const contextPath = 'http://localhost:8888/webapp/api/v1/';

    $scope.moveToViewProduct = function () {
        $location.path('/product/' + $scope.view_productId.id)
    }
});