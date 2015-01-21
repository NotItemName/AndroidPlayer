package com.player.dao;

import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.entity.Song;
import com.player.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mykola_Zalyayev
 */
@Component
public class SongDao {

    @Autowired
    private SongRepository repository;


    public Song save(Song entity) {
        String name = entity.getName();
        Album albumDto = entity.getAlbum();
        String album = albumDto == null ? null : albumDto.getName();
        Artist artistDto = entity.getArtist();
        String artist = artistDto == null ? null : artistDto.getName();

        if (name == null) {
            return null;
        }
        Song readSong = repository.findByNameAndAlbum_NameAndArtist_Name(name, album, artist);
        if (readSong == null) {
            return repository.save(entity);
        }

        return readSong;
    }

    public Song findByFileName(String fileName) {
        return repository.findByFileName(fileName);
    }

    public Song findOne(Integer id) {
        return repository.findOne(id);
    }

    public Iterable<Song> findAll() {
        return repository.findAll();
    }
}
