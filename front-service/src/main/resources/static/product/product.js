angular.module('storage').controller('productController', function ($scope, $http, $location, $routeParams, $localStorage) {
    const contextPath = 'http://localhost:5555/webapp-core/api/v1';
    let curProductId = $routeParams.productId

    $scope.prepareProductForView = function () {
        $http.get(contextPath + '/products/' + curProductId)
            .then(function successCallback (response) {
                console.log(response);
                $scope.view_product = response.data;
            }, function failureCallback (response) {
                alert(response.data.userMessage);
                $location.path('/products');
            });
    }

    $scope.getCommentsForProduct = function () {
        $http({
            url: contextPath + '/comments',
            method: 'GET',
            params: {
                productId: curProductId
            }
        }).then(function (response) {
            console.log(response);
            $scope.comments = response.data;
        });
    }

    $scope.checkCustomer = function () {
        $http({
            url: contextPath + '/orders',
            method: 'GET',
            params: {
                productId: curProductId
            }
        }).then(function (response) {
            console.log(response);
            $localStorage.customer = response.data;
        });
    }

    $scope.postNewComment = function () {
        $scope.new_comment.productId = curProductId
        $http.post(contextPath + '/comments', $scope.new_comment)
            .then(function successCallback(response) {
                $location.path('/product/' + curProductId);
            }, function failureCallback(response) {
                alert(response.data.userMessage);
            });
    }

    $scope.isCustomer = function () {
        return !!$localStorage.customer;
    }

    $scope.moveToEditProduct = function (productId) {
        $location.path('/edit_product/' + productId);
    }

    $scope.prepareProductForView();
    $scope.getCommentsForProduct();
    $scope.checkCustomer();
});