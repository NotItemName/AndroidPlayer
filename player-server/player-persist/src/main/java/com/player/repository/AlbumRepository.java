package com.player.repository;

import com.player.entity.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Николай
 */
@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> {

    Album findByNameAndArtist_Name(String name, String artistName);
}
