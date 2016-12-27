angular.module('tdtb').controller('LoginCtrl', function ($scope, $mdDialog) {
    function DialogController($scope, $http) {
        $scope.user = {};

        $scope.login = function () {

        };

        $scope.register = function () {
            $http.post("/api/user/", $scope.user).then(function (res) {

            });
        };
    }

    $mdDialog.show({
        controller: DialogController,
        templateUrl: 'login/dialog.tmpl.html',
        parent: angular.element(document.body),
        clickOutsideToClose: false
    });
});
