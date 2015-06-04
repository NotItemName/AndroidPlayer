/**
 * Created by Mykola on 21.01.2015.
 */

var app = angular.module('player.web.controllers.tab', []);

app.controller('TabController', ['$scope', function ($scope) {
    $scope.tab = 1;

    $scope.isSet = function (checkTab) {
        return $scope.tab === checkTab;
    };

    $scope.setTab = function (setTab) {
        $scope.tab = setTab;
    };
}]);
