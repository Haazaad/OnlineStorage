angular.module('storage').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/webapp-cart/api/v1';

    $scope.loadCart = function () {
        $http.get(contextPath + '/cart/' + $localStorage.webappGuestCartId)
            .then(function successCallback(response) {
                console.log(response);
                $scope.cart = response.data;
            })
    }

    $scope.incremetItem = function (productId) {
        $http.get(contextPath + '/cart/' + $localStorage.webappGuestCartId + '/add/' + productId)
            .then(function successCallback(response) {
                $scope.loadCart();
            })
    }

    $scope.decrementItem = function (productId) {
        $http.get(contextPath + '/cart/' + $localStorage.webappGuestCartId + '/decrement/' + productId)
            .then(function successCallback(response) {
                $scope.loadCart();
            })
    }

    $scope.removeItem = function (productId) {
        $http.get(contextPath + '/cart/' + $localStorage.webappGuestCartId + '/remove/' + productId)
            .then(function successCallback(response) {
                $scope.loadCart();
            })
    }

    $scope.checkOut = function () {
        $location.path("/checkout");
    }

    $scope.disabledCheckOut = function () {
        alert("To place an order, you need to log in.");
    }

    $scope.loadCart();
});