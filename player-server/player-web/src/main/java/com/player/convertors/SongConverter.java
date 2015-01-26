package com.player.convertors;

import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.entity.Genre;
import com.player.entity.Song;
import com.player.model.songs.SongDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mykola_Zalyayev
 */
public class SongConverter {

    public static Song convert(SongDto songDto) {
        Song song = new Song();
        song.setId(songDto.getId());
        song.setName(songDto.getName());
        song.setTrackNumber(songDto.getTrackNumber());

        Artist artist = new Artist();
        artist.setName(songDto.getArtistName());
        song.setArtist(artist);

        Album album = new Album();
        album.setName(songDto.getAlbumName());
        album.setYear(songDto.getYear());
        Set<Genre> genres = new HashSet<>();
        for (String genreStr : songDto.getGenres()) {
            Genre genre = new Genre();
            genre.setName(genreStr);
            genres.add(genre);
        }
        album.setGenres(genres);
        song.setArtist(artist);

        return song;
    }

    public static SongDto convert(Song song) {
        SongDto songDto = new SongDto();
        songDto.setId(song.getId());
        songDto.setName(song.getName());
        songDto.setTrackNumber(song.getTrackNumber());

        Artist artist = song.getArtist();
        if (artist != null) {
            songDto.setArtistName(song.getArtist().getName());
        }

        Album album = song.getAlbum();
        if (album != null) {
            songDto.setAlbumName(album.getName());
            songDto.setYear(album.getYear());
            List<String> genres = new ArrayList<>();
            for (Genre genre : album.getGenres()) {
                genres.add(genre.getName());
            }
            songDto.setGenre(genres);
        }
        return songDto;
    }

    public static List<SongDto> convertList(Iterable<Song> songs) {
        List<SongDto> songsDtos = new ArrayList<>();
        for (Song song : songs) {
            songsDtos.add(convert(song));
        }
        return songsDtos;
    }
}
