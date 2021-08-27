angular.module('storage').controller('cartController', function ($scope, $http, ) {
    const contextPath = 'http://localhost:8888/webapp/api/v1';

    $scope.getProductsInCart = function () {
        $http.get(contextPath + '/carts')
            .then(function successCallback (response) {
                $scope.productsInCart = response.data;
            })
    }

    $scope.deleteProductFromCart = function (productId) {
        $http({
            url: contextPath + '/carts/' + productId,
            method: 'DELETE'
        }).then(function successCallback (response) {
            alert("Successful delete product from cart")
            $scope.getProductsInCart();
        }, function failureCallback(response) {
            alert(response.data.userMessage);
        });
    }

    $scope.getProductsInCart();
});