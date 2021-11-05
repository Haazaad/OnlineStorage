angular.module('storage').controller('editProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:5555/webapp-core/api/v1/products';

    $scope.prepareProductForEdit = function () {
        $http.get(contextPath + '/' + $routeParams.productId)
            .then(function successCallback (response) {
                console.log(response);
                $scope.updated_product = response.data;
            }, function failureCallback (response) {
               console.log(response);
               alert(response.data.userMessage);
               $location.path('/products');
            });
    }

    $scope.modifyProduct = function () {
        $http.put(contextPath, $scope.updated_product)
            .then(function successCallback(response) {
                $scope.updated_product = null;
                alert("Product was successful edited.")
                $location.path('/products');
            }, function failureCallback(response) {
                alert(response.data.userMessage);
            });
    }

    $scope.prepareProductForEdit();
})