package com.player.service;

import com.player.model.Song;
import com.player.persist.dao.AlbumDao;
import com.player.persist.dao.ArtistDao;
import com.player.persist.dao.GenreDao;
import com.player.persist.dao.SongDao;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

/**
 * @author Николай
 */
@EBean
public class SongService {

    @Bean
    ArtistDao artistDao;

    @Bean
    AlbumDao albumDao;

    @Bean
    GenreDao genreDao;

    @Bean
    SongDao songDao;

    public void addSongs(List<Song> songs) {
        for (Song song : songs) {
            addSong(song);
        }
    }

    public void addSong(Song song) {
        Integer artistId = artistDao.addArtist(song.getArtistName());
        Integer albumId = albumDao.addAlbum(song.getAlbumName(), song.getYear(), artistId);
        Integer genreId = null;
        if (song.getGenre() != null) {
            genreId = genreDao.addGenre(song.getGenre());
        }
        songDao.addAlbum(song.getName(), song.getTrackNumber(), artistId, albumId, genreId);

    }

}
