angular.module('storage').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:8888/webapp-core/api/v1';

    $scope.getOrders = function () {
        $http.get(contextPath + '/orders')
            .then(function successCallback(response) {
                console.log(response);
                $scope.orders = response.data;
            })
    }

    $scope.getOrders();
});