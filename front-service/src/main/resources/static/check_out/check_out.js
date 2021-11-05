angular.module('storage').controller('checkOutController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555';

    $scope.loadCart = function () {
        $http.get(contextPath + '/webapp-cart/api/v1/cart/' + $localStorage.webappGuestCartId)
            .then(function successCallback(response) {
                console.log(response);
                $scope.cart = response.data;
            })
    }

    $scope.createOrder = function () {
        $http.post(contextPath + '/webapp-core/api/v1/orders', $scope.orderDetails)
            .then(function successCallback(response) {
                alert("Order successfully completed.");
                $location.path("/");
            })
    }

    $scope.loadCart();
});