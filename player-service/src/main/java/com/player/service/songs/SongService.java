package com.player.service.songs;

import com.player.model.songs.SongDto;
import com.player.model.songs.Songs;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mykola_Zalyayev
 */
public interface SongService {

    SongDto addSong(InputStream song, String fileName) throws IOException;

    boolean checkSongExist(String fileName);

    String getFileName(Integer id);

    Songs getAllSongs();
}
