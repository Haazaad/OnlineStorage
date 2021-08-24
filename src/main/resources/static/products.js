angular.module('webapp-front', []).controller('productsController', function ($scope, $http, $window) {
    const contextPath = 'http://localhost:8888/webapp/api/v1/products';
    let currentIndexPage = 1;

    $scope.getProducts = function (pageIndex = 1) {
        currentIndexPage = pageIndex;
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response)
            $scope.products = response.data.content;
            $scope.paginationArray = $scope.generateIndexPage(1, response.data.totalPages);
        });
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath, $scope.product)
            .then(function successCallback(response) {
                $window.location.href = 'products.html';
                $scope.getProducts(currentIndexPage);
            }, function failureCallback(response) {
                alert(response.data.userMessage);
            });
    };

    $scope.deleteProduct = function (product) {
        $http({
            url: contextPath + '/delete/' + product.id,
            method: 'DELETE'
        }).then(function successCallback(response) {
            $scope.getProducts(currentIndexPage);
        }, function failureCallback(response) {
            alert(response.data.userMessage);
        });
    };

    $scope.modifyProduct = function (product) {
        console.log(product);
        $http.put(contextPath + '/' + product.id, product)
            .then(function successCallback(response) {
                $scope.getProducts(currentIndexPage);
            }, function failureCallback(response) {
                alert(response.data.userMessage);
            });
    }

    $scope.generateIndexPage = function (startIndex, endIndex) {
        let arr = [];
        for (let i = startIndex; i < endIndex + 1; i++) {
            arr.push(i);
        }
        return arr;
    };

    $scope.getProducts();
});