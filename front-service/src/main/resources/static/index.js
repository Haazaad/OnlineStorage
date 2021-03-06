(function () {
    angular.module('storage', ['ngRoute', 'ngStorage'])
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
            .when('/create_new_user', {
                templateUrl: 'users/create_user.html',
                controller: 'createUserController'
            })
            .when('/checkout', {
                templateUrl: 'check_out/check_out.html',
                controller: 'checkOutController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        const contextPath = 'http://localhost:5555';
        if ($localStorage.appUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.appUser.token;
        }
        if (!$localStorage.webappGuestCartId){
            $http.get(contextPath + '/webapp-cart/api/v1/cart/generate')
                .then(function successCallback (response) {
                    $localStorage.webappGuestCartId = response.data.value
                })
        }
    }
})();

angular.module('storage').controller('indexController', function ($rootScope, $http, $scope, $location, $localStorage) {
    const contextPath = 'http://localhost:5555';

    $scope.moveToViewProduct = function () {
        $location.path('/product/' + $scope.view_productId.id);
    };

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/webapp-core/auth', $scope.user)
            .then(function successCallback (response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.appUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $http.get(contextPath + '/webapp-cart/api/v1/cart/' + $localStorage.webappGuestCartId + '/merge')
                        .then(function successCallback (response) {
                            $localStorage.webappGuestCartId = response.data.value
                        })
                }
            }, function errorCallback (response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $location.path("/");
    };

    $scope.clearUser = function () {
        delete $localStorage.appUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        return !!$localStorage.appUser;
    };
});