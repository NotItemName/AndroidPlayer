app.filter("albumFilter", function () {
    return function (items, options) {
        var filtered = [];
        for (var i = 0; i < items.length; i++) {
            var item = items[i];

            if (nameCompare(item.name, options.album) &&
                yearCompare(item.year, options.yearFrom, options.yearTo) &&
                artistCompare(item.artist, options.artist) &&
                genreCompare(item.genres, options.genres)) {
                filtered.push(item);
            }
        }
        return filtered;
    };

    function nameCompare(name, filterName) {
        if (filterName === '') {
            return true;
        }
        return name.toLocaleLowerCase().search(filterName.toLocaleLowerCase()) != -1;

    }

    function yearCompare(year, from, to) {
        if (from === '') {
            from = 0;
        }
        if (to === '') {
            to = 3000;
        }
        return year >= from && year <= to;
    }

    function artistCompare(artist, filterArtist) {
        if (filterArtist === '') {
            return true;
        }
        return artist.search(filterArtist) != -1;
    }

    function genreCompare(genres, filterGenres) {
        if (filterGenres.length === 0) {
            return true;
        }
        for (var i in filterGenres) {
            var genre = filterGenres[i];
            if ($.inArray(genre, genres) != -1) {
                return true;
            }
        }
        return false;
    }
});
