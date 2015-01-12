package com.player.repository;

import com.player.entity.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mykola_Zalyayev
 */
@Repository
public interface SongRepository extends CrudRepository<Song, Integer> {

    public Song findByFileName(String fileName);

    Song findByNameAndAlbum_NameAndArtist_Name(String name, String album, String artist);
}
