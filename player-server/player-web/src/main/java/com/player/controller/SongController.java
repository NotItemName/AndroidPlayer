package com.player.controller;

import com.player.entity.Song;
import com.player.model.songs.SongDto;
import com.player.model.songs.Songs;
import com.player.service.songs.SongService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.player.convertors.SongConverter.convert;
import static com.player.convertors.SongConverter.convertList;

/**
 * @author Николай
 */
@Controller
@RequestMapping(value = "/song")
public class SongController {

    private static final Logger LOGGER = Logger.getLogger(SongController.class);

    //    @Value("${service.songs.directory}")
    private String fileDirectory;

    @Autowired
    private SongService songService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public SongDto uploadSong(@RequestParam("song") MultipartFile song) throws IOException {
        InputStream stream = song.getInputStream();
        SongDto songDtoEntity = null;
        String fileName = null;
        if (checkFileIsSong(song.getOriginalFilename())) {
            //fileName = saveFileOnDisc(song, fileDirectory);
            songDtoEntity = convert(songService.addSong(stream, fileName));
        }

        if (songDtoEntity == null && fileName != null) {
            File file = new File(fileDirectory, fileName);
            file.delete();
        }
        return songDtoEntity;
    }

    @RequestMapping(value = "/stream/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource downloadDocument(@PathVariable Integer id,
                                               HttpServletResponse response) throws IOException {

        String fileName = songService.getFileName(id);

        //set the default file name to be saved by user.
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "");
        return new FileSystemResource(new File(fileDirectory, fileName));

    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Songs getAllSongs() {
        Songs songs = new Songs();
        songs.setSongDtos(convertList(songService.getAllSongs()));
        return songs;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SongDto getSongById(@PathVariable Integer id) {
        Song song = songService.getSongById(id);
        return convert(song);
    }

    //    @PostConstruct
//    @Scheduled(cron = "45 10 * * *")
    public void updateSongs() throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        File directory = new File(fileDirectory);
        if (directory.exists()) {
            File[] files = directory.listFiles();
            updateFiles(files);
        }
        watch.stop();
        String executionTime = String.valueOf(watch.getLastTaskTimeMillis());
        LOGGER.debug("Current update time " + executionTime);
    }

    private void updateFiles(File[] files) throws IOException {
        for (File file : files) {
            String fileName = file.getName();
            LOGGER.debug("Update file" + fileName);

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
