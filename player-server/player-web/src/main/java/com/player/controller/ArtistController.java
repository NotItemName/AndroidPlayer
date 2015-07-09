package com.player.controller;

import static com.player.convertors.ArtistConverter.convert;
import static com.player.convertors.ArtistConverter.convertList;

import com.player.entity.Artist;
import com.player.model.artists.ArtistDto;
import com.player.model.artists.Artists;
import com.player.service.artists.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mykola_Zalyayev
 */
@Controller
@RequestMapping(value = "/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public ArtistDto addArtist(@RequestBody ArtistDto artistDto) {
        Artist artist = artistService.addArtist(convert(artistDto));
        return convert(artist);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Artists getAllArtists() {
        Artists artists = new Artists();
        artists.setArtistDtos(convertList(artistService.getAllArtists()));
        return artists;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ArtistDto getArtistById(@PathVariable Integer id) {
        Artist artist = artistService.getArtistById(id);
        return convert(artist);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody ArtistDto genreDto) {
        artistService.updateArtist(convert(genreDto));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id) {
        artistService.deleteArtist(id);
    }
}
