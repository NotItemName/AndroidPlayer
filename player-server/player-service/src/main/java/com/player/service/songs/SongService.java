package com.player.service.songs;

import com.player.entity.Song;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public interface SongService {

    Song addSong(InputStream song, String fileName) throws IOException;

    boolean checkSongExist(String fileName);

    String getFileName(Integer id);

    List<Song> getAllSongs();

    Song getSongById(Integer id);
}
