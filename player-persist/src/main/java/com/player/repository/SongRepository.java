package com.player.repository;

import com.player.dto.SongDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mykola_Zalyayev
 */
@Repository
public interface SongRepository extends CrudRepository<SongDto, Integer> {

    public SongDto findByFileName(String fileName);

    SongDto findByNameAndAlbumDto_NameAndArtistDto_Name(String name, String album, String artist);
}
