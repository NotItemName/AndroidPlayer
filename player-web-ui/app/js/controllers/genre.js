/**
 * Created by Mykola on 21.01.2015.
 */

var app = angular.module('player.web.controllers.genre', []);

app.controller('GenreController', ['$scope', 'Rest', 'ngDialog', function ($scope, Rest, ngDialog) {
    var type = 'genre';
    $scope.editGenreId = 0;
    $scope.newGenre = {};
    $scope.add = false;
    $scope.genres = {};

    $scope.getAllGenres = function () {
        Rest.getAll(type).$promise.then(function (res) {
            $scope.genres = res.genres;
        })
    };

    $scope.cancelEdit = function () {
        $scope.genres = angular.copy($scope.temp);
        $scope.temp = {};
        $scope.editGenreId = 0;
    };

    $scope.isEdit = function (id) {
        return $scope.editGenreId === id;
    };

    $scope.editGenre = function (genre) {
        Rest.update(type, genre.id, genre);
        $scope.editGenreId = 0;
    };

    $scope.edit = function (id) {
        $scope.cancelAdd();
        $scope.temp = angular.copy($scope.genres);
        $scope.editGenreId = id;
    };

    $scope.isAdd = function (add) {
        if ($scope.editGenreId !== 0) {
            $scope.cancelEdit();
        }
        $scope.add = add;
    };

    $scope.cancelAdd = function () {
        $scope.newGenre = {};
        $scope.add = false;
    };

    $scope.addGenre = function (genre) {
        Rest.add(type, genre).$promise.then(
            function (value) {
                genre.id = value.id;
                $scope.genres.push(genre);
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

    $scope.deleteGenre = function (id) {
        Rest.remove(type, id).$promise.then(
            function (value) {
                for (var i in $scope.genres) {
                    if ($scope.genres[i].id === id) {
                        $scope.genres.splice(i, 1);
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

    $scope.getAllGenres();
}]);
