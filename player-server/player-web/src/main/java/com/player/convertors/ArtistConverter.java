package com.player.convertors;

import com.player.entity.Artist;
import com.player.model.artists.ArtistDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public class ArtistConverter {

    private ArtistConverter() {
    }

    public static Artist convert(ArtistDto artistDto) {
        Artist artist = new Artist();
        artist.setId(artistDto.getId());
        artist.setName(artistDto.getName());
        return artist;
    }

    public static ArtistDto convert(Artist artist) {
        ArtistDto artistDto = new ArtistDto();
        artistDto.setId(artist.getId());
        artistDto.setName(artist.getName());
        return artistDto;
    }

    public static List<ArtistDto> convertList(Iterable<Artist> artists) {
        List<ArtistDto> artistDtos = new ArrayList<>();
        for (Artist artist : artists) {
            artistDtos.add(convert(artist));
        }
        return artistDtos;
    }
}
