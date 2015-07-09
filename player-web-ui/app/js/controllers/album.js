/**
 * Created by Mykola on 21.01.2015.
 */

var app = angular.module('player.web.controllers.album', []);

app.controller('AlbumController', ['$scope', 'Rest', 'ngDialog', function ($scope, Rest, ngDialog) {
    var type = 'album';
    $scope.editAlbumId = 0;
    $scope.newAlbum = {genres:[]};
    $scope.add = false;
    $scope.albums = {};

    $scope.genres = [];
    $scope.genreSearchText = null;
    $scope.selectedGenre = null;
    $scope.newSelectedGenre = null;
    $scope.newGenreSearchText = null;
    $scope.artists = [];
    $scope.artistSearchText = null;

    $scope.filterOptions = {album: '', yearFrom: '', yearTo: '', artist: '', genres: []};

    $scope.findGenres = function () {
        Rest.getAll("genre").$promise.then(function (res) {
            var data = res.genres;
            for (var i in data) {
                $scope.genres.push(data[i].name);
            }
        });
    };

    $scope.autocompliteQuery = function (list, query) {
        var result = query ? list.filter(createFilterFor(query)) : [];
        if (result.length === 0) {
            result.push(query);
        }
        return result;
    };

    function createFilterFor(query) {
        var lowercaseQuery = angular.lowercase(query);

        return function filterFn(artist) {
            return (angular.lowercase(artist).indexOf(lowercaseQuery) === 0);
        };

    }

    $scope.findArtists = function () {
        Rest.getAll("artist").$promise.then(function (res) {
            var data = res.artists;
            for (var i in data) {
                $scope.artists.push(data[i].name);
            }
        });
    };

    $scope.getAllAlbums = function () {
        Rest.getAll(type).$promise.then(function (res) {
            $scope.albums = res.albums;
        });
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

    $scope.editRequest = function (album) {
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

    $scope.deleteRequest = function (id) {
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
    $scope.findGenres();
    $scope.findArtists();
}]);
