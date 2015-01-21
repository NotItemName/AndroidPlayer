package com.player.service.songs;

import com.player.model.songs.SongDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Mykola_Zalyayev
 */
public interface SongService {

    SongDto addSong(InputStream song, String fileName) throws IOException;

    boolean checkSongExist(String fileName);

    String getFileName(Integer id);

    List<SongDto> getAllSongs();

    SongDto getSongById(Integer id);
}
