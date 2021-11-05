angular.module('storage').controller('adminController', function ($scope, $http, $location) {

    $scope.moveToCreateNewProduct = function () {
        $location.path('/create_product');
    };

    $scope.moveToCreateNewUser = function () {
        $location.path('/create_new_user');
    };
});