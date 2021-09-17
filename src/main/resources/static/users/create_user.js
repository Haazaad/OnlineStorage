angular.module('storage').controller('createUserController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8888/webapp';

    $scope.createNewUser = function () {
        if ($scope.new_user == null) {
            alert("Form is not completed");
            return;
        }
        if ($scope.new_user.passwordConfirm !== $scope.new_user.password) {
            alert("Incorrect password confirmation.");
        } else {
            $http.post(contextPath + '/users', $scope.new_user)
                .then(function successCallback (response) {
                    $scope.new_user = null;
                    alert("Create new user successfully");
                    $location.path("/");
                }, function failureCallback(response) {
                    alert(response.data.userMessage);
                });
        };
    };
})