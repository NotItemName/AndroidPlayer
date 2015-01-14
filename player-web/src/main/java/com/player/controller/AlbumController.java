package com.player.controller;

import com.player.model.albums.AlbumDto;
import com.player.model.albums.Albums;
import com.player.service.albums.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AlbumDto addAlbum(@RequestBody AlbumDto albumDto) {
        return albumService.addAlbum(albumDto);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Albums getAllAlbums() {
        Albums albums = new Albums();
        albums.setAlbumDtos(albumService.getAllAlbums());
        return albums;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlbumDto getAlbumById(@PathVariable Integer id) {
        return albumService.getAlbumById(id);
    }
}
