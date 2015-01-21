package com.player.service.genres;

import com.player.entity.Genre;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public interface GenreService {
    Genre addGenre(Genre genre);

    List<Genre> getAllGenres();

    Genre getGenreById(Integer id);
}
