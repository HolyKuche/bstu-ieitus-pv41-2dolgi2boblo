angular.module('tdtb').controller('DebtsCtrl', function ($scope, $http, $mdDialog) {
    $scope.outgoingDebts = [];
    $scope.incomingDebts = [];
    $scope.searchOutgoingDebtsText = "";
    $scope.searchIncomingDebtsText = "";
    $scope.friends = [];
    $scope.searchUserText = "";
    $scope.searching = false;

    $http.get("/api/debt/all_outgoings_for_current_user").then(function (debts) {
        $scope.outgoingDebts = debts.data;
    });
    $http.get("/api/debt/all_incomings_for_current_user").then(function (debts) {
        $scope.incomingDebts = debts.data;
    });
    $http.get("/api/user/friends").then(function (friends) {
        $scope.friends = friends.data;
    });

    function DialogController($scope, $mdDialog) {
        $scope.debt = {
            description: ""
        };

        $http.get("/api/user/friends").then(function (friends) {
            $scope.friends = friends.data;
        });

        $scope.createDebt = function () {
            $http.post("/api/debt/", $scope.debt).then(function (debtId) {
                $scope.debt.id = debtId.data;
                $scope.debt.initDateTime = new Date();
                $mdDialog.hide($scope.debt);
            });
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        }
    }

    $scope.createDebt = function () {
        $mdDialog.show({
            controller: DialogController,
            templateUrl: 'debts/dialog.tmpl.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true
        }).then(function (debt) {
            $scope.outgoingDebts.splice(0, 0, debt);
        });
    };

    var removeDebtConfirm = $mdDialog.confirm()
        .title('Вы действительно хотите удалить долг?')
        .ok('Да')
        .cancel('Нет');

    $scope.removeDebt = function (debt) {
        $mdDialog.show(removeDebtConfirm).then(function () {
            $http.delete("/api/debt/" + debt.id).then(function () {
                for (var i = 0; i < $scope.incomingDebts.length; i++ ) {
                    if (debt.id === $scope.incomingDebts[i].id) {
                        $scope.incomingDebts.splice(i, 1);
                        break;
                    }
                }
            });
        });
    };

    $scope.searchDebts = function (searchDebtText, debtsArray, outgoing) {
        var searchDebtTextLower = searchDebtText.toLowerCase();
        return debtsArray.filter(function (debt) {
            return !outgoing && (debt.who.login.indexOf(searchDebtTextLower) > -1
                || debt.who.firstName.toLowerCase().indexOf(searchDebtTextLower) > -1
                || debt.who.lastName.toLowerCase().indexOf(searchDebtTextLower) > -1)
                || outgoing && (debt.whom.login.indexOf(searchDebtTextLower) > -1
                || debt.whom.firstName.toLowerCase().indexOf(searchDebtTextLower) > -1
                || debt.whom.lastName.toLowerCase().indexOf(searchDebtTextLower) > -1)
                || debt.money.toString().indexOf(searchDebtTextLower) > -1;
        });
    };

    $scope.searchUsers = function (searchUserText) {
        $http.get("/api/user/search/" + searchUserText).then(function (users) {
            $scope.searching = true;
            $scope.foundedUsers = users.data.filter(function (user) {
                return !containsById(user, $scope.friends);
            });
        });
    };

    $scope.searchUsersAmongFriends = function (searchUserText) {
        var searchUserTextLower = searchUserText.toLowerCase();
        return $scope.friends.filter(function (friend) {
            return friend.login.indexOf(searchUserTextLower) > -1
                || friend.firstName.toLowerCase().indexOf(searchUserTextLower) > -1
                || friend.lastName.toLowerCase().indexOf(searchUserTextLower) > -1;
        });
    };

    $scope.clearSearch = function () {
        $scope.searching = false;
        $scope.searchUserText = "";
        $scope.foundedUsers = [];
    };

    $scope.addFriend = function (user) {
        $http.post("/api/user/friends", user).then(function () {
            $scope.clearSearch();
            $scope.friends.push(user);
        });
    };

    $scope.removeFriend = function (friend, index) {
        $http.delete("/api/user/friends/" + friend.id).then(function () {
            $scope.friends.splice(index, 1);
        });
    };

    function containsById(item, array) {
        for (var i = 0; i < array.length; i++) {
            if (item.id === array[i].id) {
                return true;
            }
        }
        return false;
    }
});