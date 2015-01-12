package com.player.dao;

import com.player.entity.Genre;
import com.player.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mykola_Zalyayev
 */
@Component
public class GenreDao {

    @Autowired
    private GenreRepository repository;

    public Genre save(Genre entity) {
        String name = entity.getName();
        if (name == null) {
            return null;
        }
        Genre readGenre = repository.findByName(name);
        if (readGenre == null) {
            return repository.save(entity);
        }

        return readGenre;
    }
}
