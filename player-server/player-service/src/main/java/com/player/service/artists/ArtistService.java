package com.player.service.artists;

import com.player.entity.Artist;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public interface ArtistService {
    Artist addArtist(Artist artist);

    List<Artist> getAllArtists();

    Artist getArtistById(Integer id);

    void updateArtist(Artist artist);

    void deleteArtist(Integer id);
}
