package com.player.service.genres;

import com.player.entity.Genre;
import com.player.model.genres.GenreDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public class GenreConverter {

    public static Genre convert(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setId(genreDto.getId());
        genre.setName(genreDto.getName());
        return genre;
    }

    public static GenreDto convert(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }

    public static List<GenreDto> convertList(Iterable<Genre> genres) {
        List<GenreDto> genreDtos = new ArrayList<>();
        for (Genre genre : genres) {
            genreDtos.add(convert(genre));
        }
        return genreDtos;
    }
}
