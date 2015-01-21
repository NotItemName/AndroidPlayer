package com.player.dao;

import com.player.entity.Artist;
import com.player.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mykola_Zalyayev
 */
@Component
public class ArtistDao {

    @Autowired
    private ArtistRepository repository;

    public Artist save(Artist entity) {
        String name = entity.getName();
        if (name == null) {
            return null;
        }

        Artist readArtist = repository.findByName(name);
        if (readArtist == null) {
            return repository.save(entity);
        }
        return readArtist;
    }

    public Iterable<Artist> findAll() {
        return repository.findAll();
    }

    public Artist findOne(Integer id) {
        return repository.findOne(id);
    }
}
