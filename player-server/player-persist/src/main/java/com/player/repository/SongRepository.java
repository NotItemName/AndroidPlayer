package com.player.repository;

import com.player.entity.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@Repository
public interface SongRepository extends CrudRepository<Song, Integer> {

    Song findByFileName(String fileName);

    Song findByNameAndAlbum_NameAndArtist_Name(String name, String album, String artist);

    List<Song> findByAlbum_Id(Integer id);
}
