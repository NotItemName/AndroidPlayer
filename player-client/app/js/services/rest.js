/**
 * Created by Mykola on 21.01.2015.
 */

var restServices = angular.module('player.web.services.rest', ['ngResource']);

restServices.factory('Rest', ['$resource',
    function ($resource) {
        var path = 'http://localhost:8090/player-server/';

        var getAll = function (type) {
            return readQuery.get({type: type, id: ''});
        };

        var getById = function (type, id) {
            return readQuery.get({type: type, id: id});
        };

        var update = function (type, id, album) {
            updateQuery.update({type: type, id: id}, album);
        };

        var add = function (type, album) {
            return insertQuery.add({type: type}, album);
        };

        var remove = function (type, id) {
            return deleteQuery.d({type: type, id: id});
        };

        var readQuery = $resource(path + ':type/get/:id', {type: '@type', id: '@id'});
        var updateQuery = $resource(path + ':type/update/:id', {type: '@type', id: '@id'}, {'update': {method: 'PUT'}});
        var insertQuery = $resource(path + ':type/add', {type: '@type'}, {'add': {method: 'POST'}});
        var deleteQuery = $resource(path + ':type/delete/:id', {type: '@type', id: '@id'}, {'d': {method: 'DELETE'}});

        return {
            getAll: getAll,
            getById: getById,
            update: update,
            add: add,
            remove: remove
        };
    }]);
