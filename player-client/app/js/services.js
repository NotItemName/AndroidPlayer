'use strict';

var restServices = angular.module('restServices', ['ngResource']);

restServices.factory('Song', ['$resource',
  function($resource){
    return $resource('http://localhost:8090/player-server/song/get/:songId', {}, {
      query: {method:'GET', params:{songId:''}, isArray:false}
    });
}]);

restServices.factory('Genre', ['$resource',
  function($resource){
    return $resource('http://localhost:8090/player-server/genre/get/:genreId', {}, {
      query: {method:'GET', params:{genreId:''}, isArray:false}
    });
}]);

restServices.factory('Album', ['$resource',
  function($resource){
    var getAll = function(){
      return readQuery.get({albumId:''});
    };

    var getById = function(id) {
      return readQuery.get({albumId:id});
    };

    var updateAlbum = function(id, album){
      updateQuery.update({albumId:id}, album);
    };

    var addAlbum = function(album){
      insertQuery.add(album);
    };

    var readQuery = $resource('http://localhost:8090/player-server/album/get/:albumId', {albumId:'@id'});
    var updateQuery = $resource('http://localhost:8090/player-server/album/update/:albumId',{albumId:'@id'},
      {'update': {method:'PUT'}});
    var insertQuery = $resource('http://localhost:8090/player-server/album/add',null,
      {'add': {method:'POST'}})

    return {
        getAll: getAll,
        getById: getById,
        updateAlbum: updateAlbum,
        addAlbum: addAlbum
    };
}]);

restServices.factory('Artist', ['$resource',
  function($resource){
    return $resource('http://localhost:8090/player-server/artist/get/:artistId', {}, {
      query: {method:'GET', params:{artistId:''}, isArray:false}
    });
}]);