package com.player.repository;

import com.player.entity.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author Mykola_Zalyayev
 */
@Component
public interface ArtistRepository extends CrudRepository<Artist, Integer> {

    Artist findByName(String name);
}
