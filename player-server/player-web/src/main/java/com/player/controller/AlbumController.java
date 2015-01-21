package com.player.controller;

import com.player.entity.Album;
import com.player.model.albums.AlbumDto;
import com.player.model.albums.Albums;
import com.player.service.albums.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.player.convertors.AlbumConverter.convert;
import static com.player.convertors.AlbumConverter.convertList;

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
        Album album = albumService.addAlbum(convert(albumDto));
        return convert(album);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Albums getAllAlbums() {
        Albums albums = new Albums();
        albums.setAlbumDtos(convertList(albumService.getAllAlbums()));
        return albums;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public AlbumDto getAlbumById(@PathVariable Integer id) {
        Album album = albumService.getAlbumById(id);
        return convert(album);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody AlbumDto albumDto) {
        albumService.updateAlbum(convert(albumDto));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id) {
        albumService.deleteAlbum(id);
    }
}
