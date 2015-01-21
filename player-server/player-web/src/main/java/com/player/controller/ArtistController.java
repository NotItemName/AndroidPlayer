package com.player.controller;

import com.player.entity.Artist;
import com.player.model.artists.ArtistDto;
import com.player.model.artists.Artists;
import com.player.service.artists.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.player.convertors.ArtistConverter.convert;
import static com.player.convertors.ArtistConverter.convertList;

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
}
