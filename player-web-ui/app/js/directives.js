app.directive('ngEnter', function () {
    return function (scope, element) {
        element.bind("keydown keypress", function (event) {
            if (event.which === 13) {
                event.preventDefault();
            }
        });
    };
});

app.directive('ngGridTileFooter', function () {
    return {
        restrict: 'E',
        transclude:true,
        scope: {
            object: '='
        },
        templateUrl: 'templates/grid-tile-footer.html'
    }
});