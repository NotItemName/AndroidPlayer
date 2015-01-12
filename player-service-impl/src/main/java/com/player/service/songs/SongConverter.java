package com.player.service.songs;

import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.entity.Genre;
import com.player.entity.Song;
import com.player.model.songs.SongDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public class SongConverter {

    public static Song convert(SongDto song) {
        Song songDto = new Song();
        songDto.setId(song.getId());
        songDto.setName(song.getName());
        songDto.setTrackNumber(song.getTrackNumber());

        Artist artist = new Artist();
        artist.setName(song.getArtistName());
        songDto.setArtist(artist);

        Album album = new Album();
        album.setName(song.getAlbumName());
        album.setYear(song.getYear());
        songDto.setArtist(artist);

        Genre genre = new Genre();
        genre.setName(song.getGenre());
        songDto.setGenre(genre);
        return songDto;
    }

    public static SongDto convert(Song songDto) {
        SongDto song = new SongDto();
        song.setId(songDto.getId());
        song.setName(songDto.getName());
        song.setTrackNumber(songDto.getTrackNumber());

        Artist artist = songDto.getArtist();
        if (artist != null) {
            song.setArtistName(songDto.getArtist().getName());
        }

        Album album = songDto.getAlbum();
        if (album != null) {
            song.setAlbumName(album.getName());
            song.setYear(album.getYear());
        }

        Genre genre = songDto.getGenre();
        if (genre != null) {
            song.setGenre(genre.getName());
        }
        return song;
    }

    public static List<SongDto> convertList(Iterable<Song> songDtos) {
        List<SongDto> songs = new ArrayList<>();
        for (Song song : songDtos) {
            songs.add(convert(song));
        }
        return songs;
    }
}
