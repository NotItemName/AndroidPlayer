package com.player.convertors;

import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.model.albums.AlbumDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public class AlbumConverter {

    public static Album convert(AlbumDto albumDto) {
        Album album = new Album();
        album.setId(albumDto.getId());
        album.setName(albumDto.getName());
        album.setYear(albumDto.getYear());
        String artistName = albumDto.getArtist();
        if (artistName != null) {
            Artist artist = new Artist();
            artist.setName(artistName);
            album.setArtist(artist);
        }
        return album;
    }

    public static AlbumDto convert(Album album) {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setId(album.getId());
        albumDto.setName(album.getName());
        albumDto.setYear(album.getYear());
        Artist artist = album.getArtist();
        if (artist != null) {
            albumDto.setArtist(artist.getName());
        }
        return albumDto;
    }

    public static List<AlbumDto> convertList(Iterable<Album> albums) {
        List<AlbumDto> albumDtos = new ArrayList<>();
        for (Album album : albums) {
            albumDtos.add(convert(album));
        }
        return albumDtos;
    }
}
