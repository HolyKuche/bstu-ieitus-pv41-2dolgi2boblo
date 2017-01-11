angular.module('tdtb').controller('LoginCtrl', function ($scope, $mdDialog) {
    function DialogController($scope, $http) {
        $scope.user = {};
        $scope.selectedTab = 0;
        $scope.loginAlreadyExistError = false;

        $scope.register = function () {
            $http.post("/api/user/", $scope.user)
                .then(function (res) {
                    $scope.loginAlreadyExistError = false;
                    $scope.selectedTab = 0;
                }, function (res) {
                    $scope.loginAlreadyExistError = true;
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
