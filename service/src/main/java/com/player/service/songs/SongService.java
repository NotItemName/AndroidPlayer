package com.player.service.songs;

import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author Николай
 */
@Service
public class SongService {

    @Value("${service.songs.directory}")
    public String fileDirectory;

    @Autowired
    private SongHelper helper;

    public void addSong(File song) throws IOException {
        Metadata audioMetadata = helper.getMetadataFromSong(song);
        File savedFile = helper.saveFileOnDisc(song, fileDirectory);

    }
}
