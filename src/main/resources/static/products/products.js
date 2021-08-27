angular.module('storage').controller('productsController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8888/webapp/api/v1';
    let currentIndexPage = 1;

    $scope.getProducts = function (pageIndex = 1) {
        currentIndexPage = pageIndex;
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                p: pageIndex
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

    $scope.addProductInCart = function(product) {
        $http.post(contextPath + '/carts', product)
            .then(function successCallback (response) {
                alert("Product was successful added in a cart");
            }, function failureCallback (response) {
                alert("Product wasn't added in a cart")
            })
    }

    $scope.moveToEditProduct = function (productId) {
      $location.path('/edit_product/' + productId);
    }

    $scope.moveToCreateNewProduct = function () {
      $location.path('/create_product');
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