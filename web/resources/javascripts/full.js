/* File generated automatically by grunt concat, do not modify it (it is senselessly) */
(function () {
    'use strict';

    angular.module('tdtb', [
        'ngRoute',
        'ngMaterial'
    ]).factory('responseObserver', function responseObserver($q, $window, $rootScope) {
        return {
            'response': function (response) {
                if (typeof response.data === 'string' && response.data.indexOf("j_spring_security_check") > -1) {
                    $window.location.href = "./";
                    return $q.reject(response);
                } else {
                    return response;
                }
            },
            'responseError': function (errorResponse) {
                $rootScope.$broadcast('global_error', errorResponse.status);
                return $q.reject(errorResponse);
            }
        };
    }).config(function ($routeProvider) {

    });
})();