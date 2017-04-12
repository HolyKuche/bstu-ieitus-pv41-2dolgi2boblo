'use strict';

(function () {

    angular.module('tdtb', [
        'ngRoute',
        'ngMaterial',
        'angularFileUpload'
    ]).config(function ($routeProvider) {
        $routeProvider.when("/", {
            redirectTo: "/debts"
        });
        $routeProvider.when("/debts", {
            controller: 'DebtsCtrl',
            templateUrl: "/debts"
        });
    }).run(function ($rootScope, $http, $mdDialog) {
        $http.get("/api/user/current").then(function (user) {
            $rootScope.currentUser = user.data;
        });

        $rootScope.editUserData = function () {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'user/dialog.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            });
        };

        function DialogController($scope, $http, $mdDialog) {
            $scope.currentUser = jQuery.extend({}, $rootScope.currentUser);
            $scope.ok = function () {
                $http.put("/api/user", $scope.currentUser).then(function () {
                    jQuery.extend($rootScope.currentUser, $scope.currentUser);
                    $mdDialog.hide();
                });
            };
            $scope.cancel = function () {
                $mdDialog.cancel();
            };
        }
    });
})();