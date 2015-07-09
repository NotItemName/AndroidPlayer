/**
 * Created by Mykola on 21.01.2015.
 */

var app = angular.module('player.web.controllers.tab', []);

app.controller('TabController', ['$scope','$location', function ($scope,$location) {
    $scope.tab = 1;

    $scope.isSet = function (checkTab) {
        return $scope.tab === checkTab;
    };

    $scope.setTab = function (setTab, path) {
        $location.path(path);
        $scope.tab = setTab;
    };
}]);
