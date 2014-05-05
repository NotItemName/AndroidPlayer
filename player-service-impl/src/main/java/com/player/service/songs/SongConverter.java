package com.player.service.songs;

import com.player.dto.AlbumDto;
import com.player.dto.ArtistDto;
import com.player.dto.GenreDto;
import com.player.dto.SongDto;
import com.player.model.songs.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public class SongConverter {

    public static SongDto convert(Song song) {
        SongDto songDto = new SongDto();
        songDto.setId(song.getId());
        songDto.setName(song.getName());
        songDto.setTrackNumber(song.getTrackNumber());

        ArtistDto artistDto = new ArtistDto();
        artistDto.setName(song.getArtistName());
        songDto.setArtistDto(artistDto);

        AlbumDto albumDto = new AlbumDto();
        albumDto.setName(song.getAlbumName());
        albumDto.setYear(song.getYear());
        songDto.setArtistDto(artistDto);

        GenreDto genreDto = new GenreDto();
        genreDto.setName(song.getGenre());
        songDto.setGenreDto(genreDto);
        return songDto;
    }

    public static Song convert(SongDto songDto) {
        Song song = new Song();
        song.setId(songDto.getId());
        song.setName(songDto.getName());
        song.setTrackNumber(songDto.getTrackNumber());

        ArtistDto artistDto = songDto.getArtistDto();
        if (artistDto != null) {
            song.setArtistName(songDto.getArtistDto().getName());
        }

        AlbumDto albumDto = songDto.getAlbumDto();
        if (albumDto != null) {
            song.setAlbumName(albumDto.getName());
            song.setYear(albumDto.getYear());
        }

        GenreDto genreDto = songDto.getGenreDto();
        if (genreDto != null) {
            song.setGenre(genreDto.getName());
        }
        return song;
    }

    public static List<Song> convertList(List<SongDto> songDtos) {
        List<Song> songs = new ArrayList<>();
        for (SongDto songDto : songDtos) {
            songs.add(convert(songDto));
        }
        return songs;
    }
}
