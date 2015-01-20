
var app = angular.module('playerWebControllers', []);

app.controller('TabController', ['$scope', function($scope){
    $scope.tab = 1;

    $scope.isSet = function(checkTab) {
        return $scope.tab === checkTab;
    };

    $scope.setTab = function(setTab) {
        $scope.tab = setTab;
    };
}]);

app.controller('SongController', ['$scope', 'Song', function($scope, Song) {
    $scope.songs = {}
    Song.query().$promise.then(function(res){
        $scope.songs = res.songs;
    })
}]);

app.controller('GenreController', ['$scope','Genre' , function($scope, Genre) {
    $scope.genres = {}
    Genre.query().$promise.then(function(res){
        $scope.genres = res.genres;
    })
}]);

app.controller('ArtistController', ['$scope','Artist' , function($scope, Artist) {
    $scope.artists = {}
    Artist.query().$promise.then(function(res){
        $scope.artists = res.artists;
    })
}]);

app.controller('AlbumController', ['$scope','Album' , function($scope, Album) {
    $scope.editAlbumId = 0;
    $scope.newAlbum = {};
    $scope.add = false;
    $scope.albums = {};

    $scope.getAllAlbums = function(){
        Album.getAll().$promise.then(function(res){
            $scope.albums = res.albums;
        })
    }

    $scope.isEdit = function(id){
        return $scope.editAlbumId === id;
    }

    $scope.edit = function(id, album){
        $scope.cancelAdd();
        $scope.temp = angular.copy($scope.albums);
        $scope.editAlbumId = id;
    }

    $scope.cancelEdit = function(){
        $scope.albums = angular.copy($scope.temp);
        $scope.temp = {};
        $scope.editAlbumId = 0;
    }

    $scope.editAlbum = function(album){
        Album.updateAlbum(album.id, album);
        $scope.editAlbumId = 0;
    }

    $scope.isAdd = function(add){
        if($scope.editAlbumId !== 0){
            $scope.cancelEdit();
        }
        $scope.add = add;
    }

    $scope.cancelAdd = function(){
        $scope.newAlbum = {};
        $scope.add = false;
    }

    $scope.addAlbum = function(album){
        Album.addAlbum(album);
        $scope.albums.push(album)
        $scope.cancelAdd();
    }

    $scope.deleteAlbum = function(){
    }

    $scope.getAllAlbums();
}]);