angular.module('webapp-front', []).controller('productsController', function ($scope, $http) {
    const contextPath = 'http://localhost:8888/webapp/products';

    $scope.getProducts = function (pageIndex = 1) {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response)
            $scope.products = response.data.content;
            $scope.totalPages = response.data.totalPages
        });
    };

    $scope.deleteProduct = function (product) {
        $http({
            url: contextPath + '/delete/' + product.id,
            method: 'GET'
        }).then(function (response) {
            $scope.getProducts();
        });
    };


    $scope.getProducts();
});