package com.player.service.artists;

import com.google.common.collect.Lists;
import com.player.entity.Artist;
import com.player.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Artist addArtist(Artist artist) {
        String name = artist.getName();
        if (name == null) {
            return null;
        }

        Artist readArtist = artistRepository.findByName(name);
        if (readArtist == null) {
            return artistRepository.save(artist);
        }
        return readArtist;
    }

    @Override
    public List<Artist> getAllArtists() {
        return Lists.newArrayList(artistRepository.findAll());
    }

    @Override
    public Artist getArtistById(Integer id) {
        return artistRepository.findOne(id);
    }

    @Override
    public void updateArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(Integer id) {
        artistRepository.delete(id);
    }
}
