package com.player.controller;

import com.player.model.songs.Song;
import com.player.service.songs.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Николай
 */
@Controller
public class SongController {

    @Value("${service.songs.directory}")
    public String fileDirectory;

    @Autowired
    private SongService songService;

    @RequestMapping(value = "/uploadSong", method = RequestMethod.POST)
    @ResponseBody
    public Song uploadSong(@RequestParam("song") MultipartFile song) throws IOException {
        InputStream stream = song.getInputStream();

        saveFileOnDisc(song, fileDirectory);

        return songService.addSong(stream);
    }


    private void saveFileOnDisc(MultipartFile song, String fileDirectory) throws IOException {

        File directory = new File(fileDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(fileDirectory, UUID.randomUUID().toString() + ".flac");
        song.transferTo(file);
    }

}
