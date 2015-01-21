package com.player.controller;

import com.player.model.albums.AlbumDto;
import com.player.model.albums.Albums;
import com.player.service.albums.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mykola_Zalyayev
 */
@Controller
@RequestMapping(value = "/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public AlbumDto addAlbum(@RequestBody AlbumDto albumDto) {
        return albumService.addAlbum(albumDto);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Albums getAllAlbums() {
        Albums albums = new Albums();
        albums.setAlbumDtos(albumService.getAllAlbums());
        return albums;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public AlbumDto getAlbumById(@PathVariable Integer id) {
        return albumService.getAlbumById(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody AlbumDto albumDto) {
        albumService.updateAlbum(albumDto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id) {
        albumService.deleteAlbum(id);
    }
}
