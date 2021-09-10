angular.module('storage').controller('checkOutController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8888/webapp/api/v1';

    $scope.loadCart = function () {
        $http.get(contextPath + '/cart')
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