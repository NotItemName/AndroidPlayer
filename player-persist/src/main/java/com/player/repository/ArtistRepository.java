package com.player.repository;

import com.player.dto.ArtistDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author Mykola_Zalyayev
 */
@Component
public interface ArtistRepository extends CrudRepository<ArtistDto, Integer> {

    ArtistDto findByName(String name);
}
