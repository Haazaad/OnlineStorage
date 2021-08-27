angular.module('storage').controller('productController', function ($scope, $http, $location, $routeParams) {
    const contextPath = 'http://localhost:8888/webapp/api/v1/products';

    $scope.prepareProductForView = function () {
        $http.get(contextPath + '/' + $routeParams.productId)
            .then(function successCallback (response) {
                console.log(response);
                $scope.view_product = response.data;
            }, function failureCallback (response) {
                alert(response.data.userMessage);
                $location.path('/products');
            });
    }

    $scope.moveToEditProduct = function (productId) {
        $location.path('/edit_product/' + productId);
    }

    $scope.prepareProductForView();
});