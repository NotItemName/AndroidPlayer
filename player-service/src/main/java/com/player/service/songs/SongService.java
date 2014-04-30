package com.player.service.songs;

import com.player.model.songs.Song;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mykola_Zalyayev
 */
public interface SongService {

    public Song addSong(InputStream song) throws IOException;
}
