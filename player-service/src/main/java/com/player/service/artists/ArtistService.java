package com.player.service.artists;

import com.player.model.artists.ArtistDto;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public interface ArtistService {
    ArtistDto addArtist(ArtistDto artistDto);

    List<ArtistDto> getAllArtists();

    ArtistDto getArtistById(Integer id);
}
