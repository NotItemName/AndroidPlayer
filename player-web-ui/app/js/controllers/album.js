/**
 * Created by Mykola on 21.01.2015.
 */

var app = angular.module('player.web.controllers.album', []);

app.controller('AlbumController', ['$scope', 'Rest', 'ngDialog', function ($scope, Rest, ngDialog) {
    var type = 'album';
    $scope.editAlbumId = 0;
    $scope.newAlbum = {};
    $scope.add = false;
    $scope.albums = {};

    $scope.getAllAlbums = function () {
        Rest.getAll(type).$promise.then(function (res) {
            $scope.albums = res.albums;
        })
    };

    $scope.isEdit = function (id) {
        return $scope.editAlbumId === id;
    };

    $scope.edit = function (id) {
        $scope.cancelAdd();
        $scope.temp = angular.copy($scope.albums);
        $scope.editAlbumId = id;
    };

    $scope.cancelEdit = function () {
        $scope.albums = angular.copy($scope.temp);
        $scope.temp = {};
        $scope.editAlbumId = 0;
    };

    $scope.editAlbum = function (album) {
        Rest.update(type, album.id, album);
        $scope.editAlbumId = 0;
    };

    $scope.isAdd = function (add) {
        if ($scope.editAlbumId !== 0) {
            $scope.cancelEdit();
        }
        $scope.add = add;
    };

    $scope.cancelAdd = function () {
        $scope.newAlbum = {};
        $scope.add = false;
    };

    $scope.addAlbum = function (album) {
        Rest.add(type, album).$promise.then(
            function (value) {
                album.id = value.id;
                $scope.albums.push(album);
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

    $scope.deleteAlbum = function (id) {
        Rest.remove(type, id).$promise.then(
            function (value) {
                for (var i in $scope.albums) {
                    if ($scope.albums[i].id === id) {
                        $scope.albums.splice(i, 1);
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

    $scope.getAllAlbums();
}]);
