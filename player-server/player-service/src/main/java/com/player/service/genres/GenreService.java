package com.player.service.genres;

import com.player.entity.Genre;

import java.util.List;
import java.util.Set;

/**
 * @author Mykola_Zalyayev
 */
public interface GenreService {
    Genre addGenre(Genre genre);

    Set<Genre> addGenres(Set<Genre> genres);

    List<Genre> getAllGenres();

    Genre getGenreById(Integer id);

    void updateGenre(Genre genre);

    void deleteGenre(Integer id);
}
