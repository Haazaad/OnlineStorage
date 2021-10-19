angular.module('storage').controller('adminController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8888/webapp';

    $scope.moveToCreateNewProduct = function () {
        $location.path('/create_product');
    };

    $scope.moveToCreateNewUser = function () {
        $location.path('/create_new_user');
    };
});