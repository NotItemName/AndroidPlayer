package com.player.repository;

import com.player.dto.AlbumDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Николай
 */
@Repository
public interface AlbumRepository extends CrudRepository<AlbumDto, Integer> {

    AlbumDto findByNameAndArtistDto_Name(String name, String artistName);
}
