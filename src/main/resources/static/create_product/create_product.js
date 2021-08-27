angular.module('storage').controller('createProductController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8888/webapp/api/v1/products';

    $scope.createNewProduct = function () {
        if ($scope.new_product == null) {
            alert("Form is not completed");
            return;
        }
        $http.post(contextPath, $scope.new_product)
            .then(function successCallback(response) {
                alert("Product was successful created.")
                $location.path('/products');
            }, function failureCallback(response) {
                alert(response.data.userMessage);
            });
    };
})