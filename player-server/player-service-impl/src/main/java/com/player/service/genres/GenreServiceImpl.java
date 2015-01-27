package com.player.service.genres;

import com.google.common.collect.Lists;
import com.player.entity.Genre;
import com.player.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mykola_Zalyayev
 */
@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    @Transactional
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
    @Transactional
    public Set<Genre> addGenres(Set<Genre> genres) {
        Set<Genre> genreSet = new HashSet<>();
        for (Genre genre : genres) {
            genreSet.add(addGenre(genre));
        }
        return genreSet;
    }

    @Override
    @Transactional
    public List<Genre> getAllGenres() {
        return Lists.newArrayList(genreRepository.findAll());
    }

    @Override
    @Transactional
    public Genre getGenreById(Integer id) {
        return genreRepository.findOne(id);
    }

    @Override
    @Transactional
    public void updateGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void deleteGenre(Integer id) {
        genreRepository.delete(id);
    }

    @Override
    public Set<Genre> addAllGenre(Set<Genre> genres) {
        Set<Genre> genreSet = new HashSet<>();
        for (Genre genre : genres) {
            genreSet.add(addGenre(genre));
        }
        return genreSet;
    }
}
