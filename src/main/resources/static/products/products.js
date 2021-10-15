angular.module('storage').controller('productsController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8888/webapp/api/v1';
    let currentIndexPage = 1;

    $scope.getProducts = function (pageIndex = 1) {
        currentIndexPage = pageIndex;
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                p: pageIndex,
                title: $scope.filter ? $scope.filter.title : null,
                minPrice: $scope.filter ? $scope.filter.minPrice : null,
                maxPrice: $scope.filter ? $scope.filter.maxPrice : null
            }
        }).then(function (response) {
            console.log(response)
            $scope.products = response.data.content;
            $scope.paginationArray = $scope.generateIndexPage(1, response.data.totalPages);
        });
    }

    $scope.deleteProduct = function (productId) {
        $http({
            url: contextPath + '/products/delete/' + productId,
            method: 'DELETE'
        }).then(function successCallback(response) {
            $scope.getProducts(currentIndexPage);
        }, function failureCallback(response) {
            alert(response.data.userMessage);
        });
    }

    $scope.addProductInCart = function (productId) {
        $http.get(contextPath + '/cart/' + $localStorage.webappGuestCartId + '/add/' + productId)
            .then(function successCallback(response) {
            })
    }

    $scope.moveToViewProduct = function (productId) {
        $location.path('/product/' + productId);
    }

    $scope.generateIndexPage = function (startIndex, endIndex) {
        let arr = [];
        for (let i = startIndex; i < endIndex + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.getProducts();
});