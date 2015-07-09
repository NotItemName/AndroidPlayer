/**
 * Created by Mykola on 21.01.2015.
 */

var app = angular.module('player.web.controllers.song', []);

app.controller('SongController', ['$scope', "$http", 'Rest', function ($scope, $http, Rest) {
    var type = 'song';
    $scope.songs = {};

    $scope.setFile = function (element) {
        $scope.currentFile = element.files[0];
    };

    $scope.uploadFileToUrl = function () {
        var fd = new FormData();
        fd.append('file', $scope.currentFile);
        $http.post("http://localhost:8090/player-server/song/add", fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .success(function () {
            })
            .error(function () {
            });
    };

    $scope.getAllSongs = function () {
        Rest.getAll(type).$promise.then(function (res) {
            $scope.songs = res.songs;
        })
    };

    $scope.getAllSongs();
}]);
