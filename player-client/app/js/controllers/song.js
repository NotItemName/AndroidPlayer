/**
 * Created by Mykola on 21.01.2015.
 */

var app = angular.module('player.web.controllers.song', []);

app.controller('SongController', ['$scope', 'Rest', function ($scope, Rest) {
    var type = 'song';
    $scope.songs = {};

    $scope.getAllSongs = function () {
        Rest.getAll(type).$promise.then(function (res) {
            $scope.songs = res.songs;
        })
    };

    $scope.getAllSongs();
}]);
