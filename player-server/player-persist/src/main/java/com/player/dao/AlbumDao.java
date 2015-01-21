package com.player.dao;

import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mykola_Zalyayev
 */
@Component
public class AlbumDao {

    @Autowired
    private AlbumRepository repository;

    public Album save(Album entity) {
        String name = entity.getName();
        if (name == null) {
            return null;
        }
        Artist artist = entity.getArtist();
        String artistName = artist == null ? null : artist.getName();

        Album readAlbum = repository.findByNameAndArtist_Name(entity.getName(), artistName);
        if (readAlbum == null) {
            return repository.save(entity);
        }
        return readAlbum;
    }

    public Iterable<Album> findAll() {
        return repository.findAll();
    }

    public Album findOne(Integer id) {
        return repository.findOne(id);
    }

    public void update(Album album) {
        repository.save(album);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
