/**
 * Created by Mykola on 21.01.2015.
 */

var app = angular.module('player.web.controllers.artist', []);

app.controller('ArtistController', ['$scope', 'Rest', function ($scope, Rest) {
    var type = 'artist';
    $scope.editArtistId = 0;
    $scope.newArtist = {};
    $scope.add = false;
    $scope.artists = {};

    $scope.getAllArtist = function () {
        Rest.getAll(type).$promise.then(function (res) {
            $scope.artists = res.artists;
        })
    };

    $scope.cancelEdit = function () {
        $scope.artists = angular.copy($scope.temp);
        $scope.temp = {};
        $scope.editArtistId = 0;
    };

    $scope.isEdit = function (id) {
        return $scope.editArtistId === id;
    };

    $scope.editArtist = function (artist) {
        Rest.update(type, artist.id, artist);
        $scope.editArtistId = 0;
    };

    $scope.edit = function (id) {
        $scope.cancelAdd();
        $scope.temp = angular.copy($scope.artists);
        $scope.editArtistId = id;
    };

    $scope.isAdd = function (add) {
        if ($scope.editArtistId !== 0) {
            $scope.cancelEdit();
        }
        $scope.add = add;
    };

    $scope.cancelAdd = function () {
        $scope.newArtist = {};
        $scope.add = false;
    };

    $scope.addArtist = function (artist) {
        Rest.add(type, artist).$promise.then(
            function (value) {
                artist.id = value.id;
                $scope.artists.push(artist);
                $scope.cancelAdd();
            },
            function (error) {
                //TODO
                ngDialog.open({
                    template: 'view/popup.html',
                    className: 'ngdialog-theme-plain',
                    scope: $scope
                });
            }
        );

    };

    $scope.deleteArtist = function (id) {
        Rest.remove(type, id).$promise.then(
            function (value) {
                for (var i in $scope.artists) {
                    if ($scope.artists[i].id === id) {
                        $scope.artists.splice(i, 1);
                    }
                }
            }, function (error) {
                //TODO
                ngDialog.open({
                    template: 'view/popup.html',
                    className: 'ngdialog-theme-plain',
                    scope: $scope
                });
            });
    };

    $scope.getAllArtist();
}]);
