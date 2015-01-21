package com.player.convertors;

import com.player.entity.Album;
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
        return album;
    }

    public static AlbumDto convert(Album album) {
        AlbumDto genreDto = new AlbumDto();
        genreDto.setId(album.getId());
        genreDto.setName(album.getName());
        genreDto.setYear(album.getYear());
        return genreDto;
    }

    public static List<AlbumDto> convertList(Iterable<Album> albums) {
        List<AlbumDto> albumDtos = new ArrayList<>();
        for (Album album : albums) {
            albumDtos.add(convert(album));
        }
        return albumDtos;
    }
}
