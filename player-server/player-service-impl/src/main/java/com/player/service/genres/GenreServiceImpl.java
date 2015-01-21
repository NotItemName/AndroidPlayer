package com.player.service.genres;

import com.google.common.collect.Lists;
import com.player.entity.Genre;
import com.player.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre addGenre(Genre genre) {
        String name = genre.getName();
        if (name == null) {
            return null;
        }
        Genre readGenre = genreRepository.findByName(name);
        if (readGenre == null) {
            return genreRepository.save(genre);
        }

        return readGenre;
    }

    @Override
    public List<Genre> getAllGenres() {
        return Lists.newArrayList(genreRepository.findAll());
    }

    @Override
    public Genre getGenreById(Integer id) {
        return genreRepository.findOne(id);
    }
}
