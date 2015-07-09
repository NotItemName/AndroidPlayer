package com.player.convertors;

import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.entity.Genre;
import com.player.model.albums.AlbumDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mykola_Zalyayev
 */
public class AlbumConverter {

    private AlbumConverter() {
    }

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
        Set<Genre> genreSet = new HashSet<>();
        List<String> genres = albumDto.getGenres();
        if (genres == null) {
            return album;
        }
        for (String str : albumDto.getGenres()) {
            Genre genre = new Genre();
            genre.setName(str);
            genreSet.add(genre);
        }
        album.setGenres(genreSet);
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
        List<String> genres = new ArrayList<>();
        for (Genre genre : album.getGenres()) {
            genres.add(genre.getName());
        }
        albumDto.setGenres(genres);
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
