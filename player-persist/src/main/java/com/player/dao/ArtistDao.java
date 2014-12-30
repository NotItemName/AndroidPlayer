package com.player.dao;

import com.player.dto.ArtistDto;
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

    public ArtistDto save(ArtistDto dto) {
        String name = dto.getName();
        if (name == null) {
            return null;
        }

        ArtistDto readArtist = repository.findByName(name);
        if (readArtist == null) {
            return repository.save(dto);
        }
        return readArtist;
    }
}
