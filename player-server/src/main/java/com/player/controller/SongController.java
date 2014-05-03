package com.player.controller;

import com.player.model.songs.Song;
import com.player.service.songs.SongService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.player.utils.LoggerUtils.debug;

/**
 * @author Николай
 */
@Controller
public class SongController {

    final static Logger LOGGER = Logger.getLogger(SongController.class);

    @Value("${service.songs.directory}")
    public String fileDirectory;

    @Autowired
    private SongService songService;

    @RequestMapping(value = "/uploadSong", method = RequestMethod.POST)
    @ResponseBody
    public Song uploadSong(@RequestParam("song") MultipartFile song) throws IOException {
        InputStream stream = song.getInputStream();
        Song songEntity = null;
        String fileName = null;
        if (checkFileIsSong(song.getOriginalFilename())) {
            fileName = saveFileOnDisc(song, fileDirectory);
            songEntity = songService.addSong(stream, fileName);
        }

        if (songEntity == null && fileName != null) {
            File file = new File(fileDirectory, fileName);
            file.delete();
        }
        return songEntity;
    }

    @PostConstruct
    @Scheduled(cron = "45 10 * * *")
    public void updateSongs() throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        File directory = new File(fileDirectory);
        if (directory.exists()) {
            File[] files = directory.listFiles();
            updateFiles(files);
        }
        watch.stop();
        debug(LOGGER, "Current update time", String.valueOf(watch.getLastTaskTimeMillis()));
    }

    private void updateFiles(File[] files) throws IOException {
        for (File file : files) {
            String fileName = file.getName();
            debug(LOGGER, "Update file", fileName);

            if (!checkFileIsSong(fileName)) {
                continue;
            }

            Boolean exists = songService.checkSongExist(fileName);
            if (!exists) {
                InputStream stream = new FileInputStream(file);
                songService.addSong(stream, fileName);
            }
        }
    }

    private String saveFileOnDisc(MultipartFile song, String fileDirectory) throws IOException {

        File directory = new File(fileDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + ".flac";
        File file = new File(fileDirectory, fileName);
        song.transferTo(file);
        return fileName;
    }

    private boolean checkFileIsSong(String fileName) {
        return fileName.endsWith(".flac");
    }

}
