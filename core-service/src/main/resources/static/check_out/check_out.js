angular.module('storage').controller('checkOutController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8888/webapp-core/api/v1';
    const cartServicePath = 'http://localhost:8889/webapp-cart/api/v1/cart';

    $scope.loadCart = function () {
        $http.get(cartServicePath + '/' + $localStorage.webappGuestCartId)
            .then(function successCallback(response) {
                console.log(response);
                $scope.cart = response.data;
            })
    }

    $scope.createOrder = function () {
        $http.post(contextPath + '/orders', $scope.orderDetails)
            .then(function successCallback(response) {
                alert("Order successfully completed.");
                $location.path("/");
            })
    }

    $scope.loadCart();
});