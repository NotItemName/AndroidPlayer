package com.player.service.genres;

import com.player.model.genres.GenreDto;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public interface GenreService {
    GenreDto addGenre(GenreDto genreDto);

    List<GenreDto> getAllGenres();

    GenreDto getGenreById(Integer id);
}
