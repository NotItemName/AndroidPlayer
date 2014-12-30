package com.player.dao;

import com.player.dto.GenreDto;
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

    public GenreDto save(GenreDto dto) {
        String name = dto.getName();
        if (name == null) {
            return null;
        }
        GenreDto readGenre = repository.findByName(name);
        if (readGenre == null) {
            return repository.save(dto);
        }

        return readGenre;
    }
}
