'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('playerWeb', [
    'ngRoute', 'ngDialog', 'oi.multiselect', 'ngMaterial', 'ngMessages',
    'player.web.services.rest', 'player.web.controllers.tab', 'player.web.controllers.album',
    'player.web.controllers.artist', 'player.web.controllers.genre', 'player.web.controllers.song'
]);
app.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider.when('/songs', {
        templateUrl: 'view/songs.html',
        controller: 'SongController'
    }).when('/artists', {
        templateUrl: 'view/artists.html',
        controller: 'ArtistController'
    }).when('/genres', {
        templateUrl: 'view/genres.html',
        controller: 'GenreController'
    }).when('/albums', {
        templateUrl: 'view/albums.html',
        controller: 'AlbumController'
    }).otherwise({redirectTo: '/songs'});

    $httpProvider.defaults.useXDomain = true;
}]);

app.config(function($mdThemingProvider) {
    $mdThemingProvider.definePalette('amazingPaletteName', {
        '50': 'ffebee',
        '100': 'ffcdd2',
        '200': 'ef9a9a',
        '300': 'e57373',
        '400': 'ef5350',
        '500': 'f44336',
        '600': 'e53935',
        '700': 'd32f2f',
        '800': 'c62828',
        '900': 'b71c1c',
        'A100': 'ff8a80',
        'A200': 'ff5252',
        'A400': 'ff1744',
        'A700': 'd50000',
        'contrastDefaultColor': 'light',    // whether, by default, text (contrast)
                                            // on this palette should be dark or light
        'contrastDarkColors': ['50', '100', //hues which contrast should be 'dark' by default
            '200', '300', '400', 'A100'],
        'contrastLightColors': undefined    // could also specify this if default was 'dark'
    });
    $mdThemingProvider.theme('default')
        .primaryPalette('amazingPaletteName')
});

